
package services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.FixupTaskRepository;
import domain.Category;
import domain.Customer;
import domain.FixupTask;

@Service
@Transactional
public class FixupTaskService {

	@Autowired
	FixupTaskRepository	ftr;

	@Autowired
	CustomerService		cs;


	public FixupTask create() {
		FixupTask res = new FixupTask();
		res.setLocked(false);
		return res;
	}

	public FixupTask initialize(FixupTask fixupTask) {
		Customer customer = this.cs.findPrincipal();
		fixupTask.setPublishDate(new Date());
		fixupTask.setAuthor(customer);
		fixupTask.setTicker(this.createTicker());

		return this.ftr.save(fixupTask);
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

	public List<FixupTask> findAll() {
		return this.ftr.findAll();
	}

	public List<FixupTask> findApplicable() {
		return this.ftr.findApplicable();
	}

	public FixupTask findById(int id) {
		return this.ftr.findOne(id);
	}

	public List<FixupTask> findByPriceRange(Float min, Float max) {
		List<FixupTask> res = new ArrayList<>();
		res = this.ftr.findInRange(min, max);
		return res;
	}

	public List<FixupTask> findByCategory(Category category) {
		List<FixupTask> res = new ArrayList<>();
		return res;
	}
}
