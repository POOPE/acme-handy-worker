
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Curriculum;
import repositories.CurriculumRepository;

@Service
@Transactional
public class CurriculumService {
	// Managed repository -----------------------------------------------------

	@Autowired
	private CurriculumRepository curriculumRepository;

	// Supporting services ----------------------------------------------------


	// Constructors -----------------------------------------------------------

	public CurriculumService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	// Other business methods -------------------------------------------------

	public Collection<Curriculum> getCurriculums(int handyWorkerId) {
		Collection<Curriculum> result;

		result = this.curriculumRepository.findByHandyWorkerId(handyWorkerId);
		Assert.notNull(result);
		Assert.isTrue(handyWorkerId == 0 || !result.isEmpty());
		Assert.isTrue(handyWorkerId != 0 || result.isEmpty());

		return result;
	}

}
