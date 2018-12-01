
package services;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.EducationRecord;
import repositories.EducationRecordRepository;

@Service
@Transactional
public class EducationRecordService {

	@Autowired
	private EducationRecordRepository educationRecordRepository;


	//CRUD ---------------------------------------------------------------

	public EducationRecord create() {
		EducationRecord educationRecord = new EducationRecord();
		Collection<String> comments = new HashSet<>();
		educationRecord.setComments(comments);
		this.educationRecordRepository.save(educationRecord);
		return educationRecord;
	}

	public EducationRecord save(EducationRecord educationRecord) {
		return this.educationRecordRepository.save(educationRecord);
	}

	public EducationRecord find(int educationRecordId) {
		EducationRecord res = this.educationRecordRepository.findOne(educationRecordId);
		return res;
	}

	//OTHER ----------------------------------------------------------------

}
