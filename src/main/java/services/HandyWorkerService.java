
package services;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.HandyWorkerRepository;
import domain.HandyWorker;

@Service
@Transactional
public class HandyWorkerService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private HandyWorkerRepository	handyWorkerRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private ActorService			actorService;

	@Autowired
	private UserAccountService		userAccountService;


	// Constructors -----------------------------------------------------------

	public HandyWorkerService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public HandyWorker createHandyWorker() {
		HandyWorker handyWorker = new HandyWorker();
		handyWorker = (HandyWorker) this.actorService.initialize(handyWorker);
		handyWorker = this.initializeHandyWorker(handyWorker);
		return handyWorker;
	}

	public HandyWorker save(HandyWorker handyWorker) {
		// if it's saved for the first time (created), assign a proper make given his name
		if (handyWorker.getId() == 0) {
			Md5PasswordEncoder encoder = new Md5PasswordEncoder();
			handyWorker.getUser().setPassword(encoder.encodePassword(handyWorker.getUser().getPassword(), null));
			if (handyWorker.getMake() == null || handyWorker.getMake().equals("")) {
				handyWorker.setMake(handyWorker.getName() + " " + handyWorker.getMiddleName() + " " + handyWorker.getSurname());
			}

			HandyWorker res = this.handyWorkerRepository.save(handyWorker);
			res = (HandyWorker) this.actorService.postInitialize(res);
			return res;
		} else {
			HandyWorker current = this.findOne(handyWorker.getId());
			if (current.getUser().getPassword() != handyWorker.getUser().getPassword()) {
				Md5PasswordEncoder encoder = new Md5PasswordEncoder();
				handyWorker.getUser().setPassword(encoder.encodePassword(handyWorker.getUser().getPassword(), null));
			}
			return this.handyWorkerRepository.save(handyWorker);
		}
	}
	public HandyWorker findOne(int handyWorkerId) {
		Assert.isTrue(handyWorkerId > 0);
		return this.handyWorkerRepository.findOne(handyWorkerId);
	}

	public List<HandyWorker> findAll() {
		return this.handyWorkerRepository.findAll();
	}

	// Other business methods -------------------------------------------------

	public HandyWorker findPrincipal() {
		this.actorService.assertPrincipalAuthority("HANDYWORKER");
		return (HandyWorker) this.actorService.findPrincipal();
	}
	public HandyWorker initializeHandyWorker(HandyWorker handyWorker) {
		handyWorker.setUser(this.userAccountService.addAuthority(handyWorker.getUser(), "HANDYWORKER"));
		return handyWorker;
	}

	public Collection<HandyWorker> getHandyWorkerById(int handyWorkerId) {
		Collection<HandyWorker> result;

		result = this.handyWorkerRepository.findByUserAccountId(handyWorkerId);
		Assert.notNull(result);
		Assert.isTrue(handyWorkerId == 0 || !result.isEmpty());
		Assert.isTrue(handyWorkerId != 0 || result.isEmpty());

		return result;
	}

}
