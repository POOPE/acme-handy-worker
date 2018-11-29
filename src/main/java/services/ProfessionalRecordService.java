
package services;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.ProfessionalRecord;
import repositories.ProfessionalRecordRepository;

@Service
@Transactional
public class ProfessionalRecordService {

	@Autowired
	private ProfessionalRecordRepository professionalRecordRepository;


	//CRUD ---------------------------------------------------------------

	public ProfessionalRecord create() {
		ProfessionalRecord professionalRecord = new ProfessionalRecord();
		Collection<String> comments = new HashSet<>();
		professionalRecord.setComments(comments);
		this.professionalRecordRepository.save(professionalRecord);

		return professionalRecord;
	}

	public ProfessionalRecord save(ProfessionalRecord professionalRecord) {
		return this.professionalRecordRepository.save(professionalRecord);
	}

	public ProfessionalRecord find(int professionalRecordId) {
		ProfessionalRecord res = this.professionalRecordRepository.findOne(professionalRecordId);
		return res;
	}

	//OTHER ----------------------------------------------------------------

}
