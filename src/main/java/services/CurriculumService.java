
package services;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Curriculum;
import domain.HandyWorker;
import domain.Record;
import repositories.CurriculumRepository;

@Service
@Transactional
public class CurriculumService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private CurriculumRepository	curriculumRepository;

	// Supporting services ----------------------------------------------------
	@Autowired
	private HandyWorkerService		handyWorkerService;


	// Constructors -----------------------------------------------------------

	public CurriculumService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	public Curriculum findById(int curriculumId) {
		return this.curriculumRepository.findOne(curriculumId);
	}

	public List<Curriculum> findAll() {
		return this.curriculumRepository.findAll();
	}

	public Curriculum create() {
		HandyWorker handyWorker = this.handyWorkerService.findPrincipal();
		//check if locked

		Curriculum res = new Curriculum();
		res.setOwner(handyWorker);
		res.setTicker("324234234");
		res.setFullName("Alberto Arias");
		res.setEmail("roberto@gmail.com");
		Collection<Record> wiki = new HashSet<>();
		res.setRecords(wiki);
		res.setPhoto("https://www.youtube.com");
		Assert.isTrue(res != null, "Error on create: FixupTask already locked");
		this.curriculumRepository.save(res);
		return res;

	}

	public Curriculum save(Curriculum curriculum) {
		return this.curriculumRepository.save(curriculum);
	}

	// Other business methods -------------------------------------------------

	public Collection<Curriculum> findByHandyWorker(int handyWorkerId) {
		Collection<Curriculum> result;

		result = this.curriculumRepository.findByHandyWorkerId(handyWorkerId);

		return result;
	}

}
