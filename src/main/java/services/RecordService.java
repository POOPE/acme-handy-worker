
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Record;
import repositories.RecordRepository;

@Service
@Transactional
public class RecordService {
	// Managed repository -----------------------------------------------------

	@Autowired
	private RecordRepository recordRepository;

	// Supporting services ----------------------------------------------------


	// Constructors -----------------------------------------------------------

	public RecordService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	// Other business methods -------------------------------------------------

	public Collection<Record> getRecords(int curriculumId) {
		Collection<Record> result;

		result = this.recordRepository.findByCurriculumId(curriculumId);
		Assert.notNull(result);
		Assert.isTrue(curriculumId == 0 || !result.isEmpty());
		Assert.isTrue(curriculumId != 0 || result.isEmpty());

		return result;
	}

}
