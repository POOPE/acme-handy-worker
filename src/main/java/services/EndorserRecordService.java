
package services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.EndorserRecordRepository;
import domain.EndorserRecord;

@Service
@Transactional
public class EndorserRecordService {

	@Autowired
	private EndorserRecordRepository	endorserRecordRepository;

	@Autowired
	private HandyWorkerService			handyWorkerService;


	//CRUD ---------------------------------------------------------------

	public EndorserRecord create() {
		EndorserRecord endorserRecord = new EndorserRecord();
		List<String> comments = new ArrayList<>();
		endorserRecord.setComments(comments);

		return endorserRecord;
	}

	public EndorserRecord save(EndorserRecord endorserRecord) {
		Assert.isTrue(this.handyWorkerService.findPrincipal() != null);
		return this.endorserRecordRepository.save(endorserRecord);
	}

	public EndorserRecord findById(int endorserRecordId) {
		EndorserRecord res = this.endorserRecordRepository.findOne(endorserRecordId);
		return res;
	}

	//OTHER ----------------------------------------------------------------

}
