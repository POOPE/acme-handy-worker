
package services;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.FixupApplicationRepository;
import domain.Customer;
import domain.FixupApplication;
import domain.FixupTask;
import domain.HandyWorker;

@Service
@Transactional
public class FixupApplicationService {

	@Autowired
	private FixupApplicationRepository	fixupApplicationRepository;
	@Autowired
	private HandyWorkerService			handyWorkerService;
	@Autowired
	private CustomerService				customerService;
	@Autowired
	private FixupTaskService			fixupTaskService;


	public List<FixupApplication> findByAuthor(HandyWorker handyWorker) {
		return this.fixupApplicationRepository.findByAuthor(handyWorker.getId());
	}

	public FixupApplication findById(int fixupTaskId) {
		return this.fixupApplicationRepository.findOne(fixupTaskId);
	}

	public List<FixupApplication> findAll() {
		return this.fixupApplicationRepository.findAll();
	}

	public List<FixupApplication> findByTask(FixupTask task) {
		return this.fixupApplicationRepository.findByTask(task.getId());
	}

	public List<FixupApplication> findByStatusForTask(String status, FixupTask task) {
		return this.fixupApplicationRepository.findByStatusForTask(status.toUpperCase().trim(), task.getId());
	}

	public FixupApplication create(FixupTask fixupTask) {
		HandyWorker handyWorker = this.handyWorkerService.findPrincipal();
		//check if locked
		Assert.isTrue(!fixupTask.isLocked(), "Error on create: FixupTask already locked");

		FixupApplication res = new FixupApplication();
		res.setAuthor(handyWorker);
		res.setFixupTask(fixupTask);
		res.setPublishDate(new Date());
		res.setStatus("TEMP");
		return res;
	}

	public FixupApplication publish(FixupApplication fixupApplication) {
		fixupApplication.setPublishDate(new Date());
		fixupApplication.setStatus("PENDING");

		return this.fixupApplicationRepository.save(fixupApplication);
	}

	public FixupApplication reject(FixupApplication application) {
		Customer customer = this.customerService.findPrincipal();
		FixupTask fixupTask = application.fixupTask;

		Assert.isTrue(fixupTask.author.equals(customer), "Error on reject: Ownership inconsistency");

		application.setStatus("REJECTED");
		return this.save(application);
	}

	public FixupApplication accept(FixupApplication application) {
		Customer customer = this.customerService.findPrincipal();
		FixupTask fixupTask = application.fixupTask;

		Assert.isTrue(fixupTask.author.equals(customer), "Error on reject: Ownership inconsistency");
		fixupTask.setLocked(true);
		this.fixupTaskService.save(fixupTask);
		application.setStatus("ACCEPTED");
		return this.save(application);
	}

	public FixupApplication save(FixupApplication fixupApplication) {
		if (fixupApplication.getId() != 0) {
			return this.fixupApplicationRepository.save(fixupApplication);
		} else {
			return this.publish(fixupApplication);
		}
	}

	public void delete(FixupApplication fixupApplication) {

		this.fixupApplicationRepository.delete(fixupApplication);
	}
}
