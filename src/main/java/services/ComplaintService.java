
package services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ComplaintRepository;
import domain.Complaint;
import domain.Customer;
import domain.FixupTask;
import domain.HandyWorker;

@Service
@Transactional
public class ComplaintService {

	@Autowired
	private ComplaintRepository		complaintRepo;

	@Autowired
	private CustomerService			customerService;

	@Autowired
	private FixupApplicationService	fixupApplicationService;

	@Autowired
	private FixupTaskService		fixupTaskService;


	public Complaint create(FixupTask fixupTask) {
		Assert.isTrue(fixupTask.getAuthor().equals(this.customerService.findPrincipal()), "Error on create: Owner inconsistency");
		Complaint res = new Complaint();
		res.setReference(fixupTask);
		res.setAttachments(new ArrayList<String>());
		return res;
	}

	public Complaint publish(Complaint complaint) {
		Assert.isTrue(complaint.getReference().getAuthor().equals(this.customerService.findPrincipal()), "Error on create: Owner inconsistency");
		complaint.setPublishDate(new Date());
		return this.save(complaint);
	}

	public Complaint save(Complaint complaint) {
		return this.complaintRepo.save(complaint);
	}

	public List<Complaint> findByHandyWorker(HandyWorker handyWorker) {
		List<Complaint> res = new ArrayList<>();
		List<FixupTask> tasks = this.complaintRepo.findTasksByHandyWorker(handyWorker.getId());
		for (FixupTask task : tasks) {
			List<Complaint> complaints = this.complaintRepo.findByFixupTask(task.getId());
			if (!complaints.isEmpty()) {
				res.addAll(complaints);
			}
		}
		return res;
	}

	public List<Complaint> findByCustomer(Customer customer) {
		List<Complaint> res = new ArrayList<>();
		List<FixupTask> tasks = this.fixupTaskService.findByAuthor(customer);
		for (FixupTask task : tasks) {
			List<Complaint> complaints = this.complaintRepo.findByFixupTask(task.getId());
			if (!complaints.isEmpty()) {
				res.addAll(complaints);
			}
		}
		return res;
	}
}
