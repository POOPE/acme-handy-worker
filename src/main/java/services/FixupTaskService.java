
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
import org.springframework.util.Assert;

import repositories.FixupTaskRepository;
import domain.Category;
import domain.Customer;
import domain.FixupApplication;
import domain.FixupTask;

@Service
@Transactional
public class FixupTaskService {

	@Autowired
	FixupTaskRepository		ftr;

	@Autowired
	CustomerService			cs;

	@Autowired
	FixupApplicationService	fixupApplicationService;


	public FixupTask create() {
		Customer customer = this.cs.findPrincipal();
		FixupTask res = new FixupTask();
		res.setLocked(false);
		res.setTicker("TEMP");
		res.setPublishDate(new Date());

		res.setAuthor(customer);
		return res;
	}

	public FixupTask initialize(FixupTask fixupTask) {
		fixupTask.setPublishDate(new Date());
		fixupTask.setTicker(this.createTicker());

		return this.save(fixupTask);
	}

	public FixupTask save(FixupTask f) {
		if (f.getId() != 0) {
			return this.ftr.save(f);
		} else {
			return this.initialize(f);
		}
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

	public List<FixupTask> findByCustomer(int id) {
		return this.ftr.findByCustomer(id);
	}

	public List<FixupTask> findByPrincipal() {
		return this.ftr.findByCustomer(this.cs.findPrincipal().getId());
	}

	public void delete(FixupTask fixupTask) {
		Customer customer = this.cs.findPrincipal();
		Assert.isTrue(fixupTask.author.equals(customer));

		//delete all associated applications
		List<FixupApplication> applications = this.fixupApplicationService.findByTask(fixupTask);
		for (FixupApplication application : applications) {
			this.fixupApplicationService.delete(application);
		}
		this.ftr.delete(fixupTask);
	}

	public List<FixupTask> findByAuthor(Customer customer) {
		return this.ftr.findByCustomer(customer.getId());
	}

}
