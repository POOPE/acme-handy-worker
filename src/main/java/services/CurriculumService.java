
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.CurriculumRepository;
import domain.Curriculum;

@Service
@Transactional
public class CurriculumService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private CurriculumRepository	curriculumRepository;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public CurriculumService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	// Other business methods -------------------------------------------------

	public Collection<Curriculum> findByHandyWorker(int handyWorkerId) {
		Collection<Curriculum> result;

		result = this.curriculumRepository.findByHandyWorkerId(handyWorkerId);

		return result;
	}

}
