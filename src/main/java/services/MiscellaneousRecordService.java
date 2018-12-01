
package services;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.MiscellaneousRecord;
import repositories.MiscellaneousRecordRepository;

@Service
@Transactional
public class MiscellaneousRecordService {

	@Autowired
	private MiscellaneousRecordRepository miscellaneousRecordRepository;


	//CRUD ---------------------------------------------------------------

	public MiscellaneousRecord create() {
		MiscellaneousRecord miscellaneousRecord = new MiscellaneousRecord();
		Collection<String> comments = new HashSet<>();
		miscellaneousRecord.setComments(comments);
		this.miscellaneousRecordRepository.save(miscellaneousRecord);
		return miscellaneousRecord;
	}

	public MiscellaneousRecord save(MiscellaneousRecord miscellaneousRecord) {
		return this.miscellaneousRecordRepository.save(miscellaneousRecord);
	}

	public MiscellaneousRecord find(int miscellaneousRecordId) {
		MiscellaneousRecord res = this.miscellaneousRecordRepository.findOne(miscellaneousRecordId);
		return res;
	}

	//OTHER ----------------------------------------------------------------

}
