
package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.FinderRepository;
import domain.Finder;
import domain.FixupTask;
import domain.HandyWorker;
import domain.SiteConfiguration;

@Service
@Transactional
public class FinderService {

	@Autowired
	private FinderRepository			finderRepository;

	@Autowired
	private SiteConfigurationService	siteConfigurationService;

	@Autowired
	private ActorService				actorService;

	@Autowired
	private HandyWorkerService			handyWorkerService;


	//CRUD ---------------------------------------------------------------

	public Finder create() {
		Finder finder = new Finder();
		finder.setFixUpTasks(new ArrayList<FixupTask>());
		return finder;
	}

	public Finder save(Finder finder) {
		return this.finderRepository.save(finder);
	}

	public Finder find(int finderId) {
		Finder res = this.finderRepository.findOne(finderId);
		res = this.checkFinderReset(res);
		return res;
	}

	//OTHER ----------------------------------------------------------------

	public Finder doSearch(Finder finder) {
		List<FixupTask> fixupTasks = this.finderRepository.doSearch(finder.getKeyWord(), finder.getCategory(), finder.getWarranty(), finder.getMinPrice(), finder.getMaxPrice(), finder.getMinDate(), finder.getMaxDate());
		finder.setFixUpTasks(fixupTasks);
		finder.setCreationDate(new Date());
		finder = this.save(finder);
		return finder;
	}
	public Finder assignFinder(HandyWorker owner) {
		Finder res = this.create();
		res.setHandyWorker(owner);
		res = this.save(res);
		return res;
	}

	public Finder findPrincipal() {
		this.actorService.assertPrincipalAuthority("HANDYWORKER");
		HandyWorker principal = this.handyWorkerService.findPrincipal();
		Finder res = this.finderRepository.findByOwnerId(principal.getId());
		res = this.checkFinderReset(res);
		return res;
	}

	public Finder checkFinderReset(Finder finder) {
		SiteConfiguration config = this.siteConfigurationService.find();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(finder.getCreationDate());
		calendar.add(Calendar.SECOND, config.getFinderTimeLimit());
		if (calendar.getTime().after(new Date())) {
			finder = this.resetFinder(finder);
		}
		return finder;
	}
	public Finder resetFinder(Finder finder) {
		finder.setCategory(null);
		finder.setFixUpTasks(new ArrayList<FixupTask>());
		finder.setKeyWord(null);
		finder.setMaxDate(null);
		finder.setMaxPrice(null);
		finder.setMinDate(null);
		finder.setMinPrice(null);
		finder.setWarranty(null);
		return finder;
	}
}
