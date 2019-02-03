
package services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.LoremRepository;
import domain.Customer;
import domain.FixupTask;
import domain.Lorem;

@Service
@Transactional
public class LoremService {

	@Autowired
	private FixupTaskService	fixupTaskService;

	@Autowired
	private CustomerService		customerService;

	@Autowired
	private LoremRepository		loremRepository;


	public Lorem findById(int id) {
		return this.loremRepository.findOne(id);
	}

	public Lorem create(int fixupTaskId) {
		Lorem res = new Lorem();
		Customer author = this.customerService.findPrincipal();
		FixupTask fixupTask = this.fixupTaskService.findById(fixupTaskId);
		String ticker = "TEMP";
		Date date = new Date();

		res.setPublishDate(date);
		res.setAuthor(author);
		res.setFixupTask(fixupTask);
		res.setTicker(ticker);
		res.setLock(false);
		return res;
	}

	public Lorem initialize(Lorem lorem) {
		lorem.setPublishDate(new Date());
		lorem.setTicker(this.createTicker());
		lorem.setLock(false);
		return this.save(lorem);
	}

	public Lorem save(Lorem lorem) {
		return this.loremRepository.save(lorem);
	}

	public Lorem lock(Lorem lorem) {
		lorem.setLock(true);
		lorem.setPublishDate(new Date());
		return this.save(lorem);
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

	public List<Lorem> findAll() {
		return this.loremRepository.findAll();
	}

	public List<Lorem> findByAuthor() {
		return this.loremRepository.findByAuthor(this.customerService.findPrincipal().getId());
	}

	public List<Lorem> findByFixupTask(FixupTask fixupTask) {
		return this.loremRepository.findByFixupTask(fixupTask.getId());
	}

	public List<Lorem> findAllFinal() {
		return this.loremRepository.findAllFinal();
	}

	public void delete(Lorem lorem) {
		Customer customer = this.customerService.findPrincipal();
		Assert.isTrue(lorem.getAuthor().equals(customer));

		this.loremRepository.delete(lorem);
	}

	//dashboard

	public Float publishedLoremRatio() {
		return this.loremRepository.publishedLoremRatio();
	}

	public Float draftLoremRatio() {
		return this.loremRepository.draftLoremRatio();
	}

}
