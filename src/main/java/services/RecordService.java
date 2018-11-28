
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.RecordRepository;
import domain.Record;

@Service
@Transactional
public class RecordService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private RecordRepository	recordRepository;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public RecordService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	// Other business methods -------------------------------------------------

	public Collection<Record> findByCurriculum(int curriculumId) {
		Collection<Record> result;

		result = this.recordRepository.findByCurriculumId(curriculumId);

		return result;
	}

}
