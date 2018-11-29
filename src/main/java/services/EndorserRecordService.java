
package services;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.EndorserRecord;
import repositories.EndorserRecordRepository;

@Service
@Transactional
public class EndorserRecordService {

	@Autowired
	private EndorserRecordRepository endorserRecordRepository;


	//CRUD ---------------------------------------------------------------

	public EndorserRecord create() {
		EndorserRecord endorserRecord = new EndorserRecord();
		Collection<String> comments = new HashSet<>();
		endorserRecord.setComments(comments);
		this.endorserRecordRepository.save(endorserRecord);
		return endorserRecord;
	}

	public EndorserRecord save(EndorserRecord educationRecord) {
		return this.endorserRecordRepository.save(educationRecord);
	}

	public EndorserRecord find(int endorserRecordId) {
		EndorserRecord res = this.endorserRecordRepository.findOne(endorserRecordId);
		return res;
	}

	//OTHER ----------------------------------------------------------------

}
