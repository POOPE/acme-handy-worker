
package services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.EducationRecordRepository;
import domain.EducationRecord;

@Service
@Transactional
public class EducationRecordService {

	@Autowired
	private EducationRecordRepository	educationRecordRepository;

	@Autowired
	private HandyWorkerService			handyWorkerService;


	//CRUD ---------------------------------------------------------------

	public EducationRecord create() {
		EducationRecord educationRecord = new EducationRecord();
		List<String> comments = new ArrayList<>();
		educationRecord.setComments(comments);

		return educationRecord;
	}

	public EducationRecord save(EducationRecord educationRecord) {
		Assert.isTrue(this.handyWorkerService.findPrincipal() != null);
		return this.educationRecordRepository.save(educationRecord);
	}

	public EducationRecord findById(int educationRecordId) {
		EducationRecord res = this.educationRecordRepository.findOne(educationRecordId);
		return res;
	}

	//OTHER ----------------------------------------------------------------

}
