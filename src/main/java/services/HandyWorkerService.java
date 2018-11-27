
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
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


	// Constructors -----------------------------------------------------------

	public HandyWorkerService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public HandyWorker createHandyWorker() {
		HandyWorker handyWorker = new HandyWorker();
		handyWorker = (HandyWorker) this.actorService.initializeActor(handyWorker);
		return handyWorker;
	}

	public HandyWorker save(HandyWorker handyWorker) {
		return this.handyWorkerRepository.save(handyWorker);
	}

	public HandyWorker findOne(int handyWorkerId) {
		Assert.isTrue(handyWorkerId > 0);
		return this.handyWorkerRepository.findOne(handyWorkerId);
	}

	// Other business methods -------------------------------------------------

	public HandyWorker findPrincipal() {
		Assert.isTrue(this.actorService.getPrincipalAuthority() == "HANDYWORKER", "The user logged is not a handy-worker.");
		return (HandyWorker) this.actorService.findPrincipal();
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
