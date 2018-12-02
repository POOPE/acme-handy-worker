
package services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ProfessionalRecordRepository;
import domain.ProfessionalRecord;

@Service
@Transactional
public class ProfessionalRecordService {

	@Autowired
	private ProfessionalRecordRepository	professionalRecordRepository;

	@Autowired
	private HandyWorkerService				handyWorkerService;


	//CRUD ---------------------------------------------------------------

	public ProfessionalRecord create() {
		ProfessionalRecord professionalRecord = new ProfessionalRecord();
		List<String> comments = new ArrayList<>();
		professionalRecord.setComments(comments);

		return professionalRecord;
	}

	public ProfessionalRecord save(ProfessionalRecord professionalRecord) {
		Assert.isTrue(this.handyWorkerService.findPrincipal() != null);

		return this.professionalRecordRepository.save(professionalRecord);
	}

	public ProfessionalRecord findById(int professionalRecordId) {
		ProfessionalRecord res = this.professionalRecordRepository.findOne(professionalRecordId);
		return res;
	}

	//OTHER ----------------------------------------------------------------

}
