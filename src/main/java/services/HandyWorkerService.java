
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.HandyWorker;
import repositories.HandyWorkerRepository;

@Service
@Transactional
public class HandyWorkerService {
	// Managed repository -----------------------------------------------------

	@Autowired
	private HandyWorkerRepository handyWorkerRepository;

	// Supporting services ----------------------------------------------------


	// Constructors -----------------------------------------------------------

	public HandyWorkerService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	// Other business methods -------------------------------------------------

	public Collection<HandyWorker> getHandyWorkerById(int handyWorkerId) {
		Collection<HandyWorker> result;

		result = this.handyWorkerRepository.findByUserAccountId(handyWorkerId);
		Assert.notNull(result);
		Assert.isTrue(handyWorkerId == 0 || !result.isEmpty());
		Assert.isTrue(handyWorkerId != 0 || result.isEmpty());

		return result;
	}

}
