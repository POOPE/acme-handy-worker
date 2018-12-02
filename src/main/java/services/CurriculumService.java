
package services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CurriculumRepository;
import domain.Curriculum;
import domain.HandyWorker;
import domain.Record;

@Service
@Transactional
public class CurriculumService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private CurriculumRepository	curriculumRepository;

	// Supporting services ----------------------------------------------------
	@Autowired
	private HandyWorkerService		handyWorkerService;


	// Constructors -----------------------------------------------------------

	public CurriculumService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	public Curriculum findById(int curriculumId) {
		return this.curriculumRepository.findOne(curriculumId);
	}

	public List<Curriculum> findAll() {
		return this.curriculumRepository.findAll();
	}

	private String createTicker() {
		String res, dict;
		dict = "QWERTYUIOPASDFGHJKLZXCVBNM";
		DateFormat df = new SimpleDateFormat("YYYYMMdd");
		Date d = new Date();
		res = df.format(d);
		res = res + "-";
		for (int i = 0; i < 6; i++) {
			int randomNum = ThreadLocalRandom.current().nextInt(0, dict.length());
			res = res + dict.charAt(randomNum);
		}

		return res;
	}

	public Curriculum initialize(Curriculum curriculum) {
		Assert.isTrue(curriculum.getOwner().equals(this.handyWorkerService.findPrincipal()), "Error on save: Owner inconsistency");
		curriculum.setTicker(this.createTicker());
		return this.save(curriculum);
	}

	public Curriculum create() {
		HandyWorker handyWorker = this.handyWorkerService.findPrincipal();

		Curriculum res = new Curriculum();
		res.setOwner(handyWorker);
		res.setRecords(new HashSet<Record>());
		return res;
	}

	public Curriculum addRecord(Curriculum curriculum, Record record) {
		Assert.isTrue(curriculum.getOwner().equals(this.handyWorkerService.findPrincipal()), "Error adding a record: Owner inconsistency");
		Collection<Record> records = curriculum.getRecords();
		records.add(record);
		return this.save(curriculum);
	}

	public Curriculum save(Curriculum curriculum) {
		return this.curriculumRepository.save(curriculum);
	}

	// Other business methods -------------------------------------------------

	public Collection<Curriculum> findByHandyWorker(int handyWorkerId) {
		Collection<Curriculum> result;

		result = this.curriculumRepository.findByHandyWorkerId(handyWorkerId);

		return result;
	}

}
