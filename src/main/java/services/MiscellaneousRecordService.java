
package services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.MiscellaneousRecordRepository;
import domain.MiscellaneousRecord;

@Service
@Transactional
public class MiscellaneousRecordService {

	@Autowired
	private MiscellaneousRecordRepository	miscellaneousRecordRepository;

	@Autowired
	private HandyWorkerService				handyWorkerService;


	//CRUD ---------------------------------------------------------------

	public MiscellaneousRecord create() {
		MiscellaneousRecord miscellaneousRecord = new MiscellaneousRecord();
		miscellaneousRecord.setComments(new ArrayList<String>());

		return miscellaneousRecord;
	}

	public MiscellaneousRecord save(MiscellaneousRecord miscellaneousRecord) {
		Assert.isTrue(this.handyWorkerService.findPrincipal() != null);
		return this.miscellaneousRecordRepository.save(miscellaneousRecord);
	}

	public MiscellaneousRecord find(int miscellaneousRecordId) {
		MiscellaneousRecord res = this.miscellaneousRecordRepository.findOne(miscellaneousRecordId);
		return res;
	}

	//OTHER ----------------------------------------------------------------

}
