
package services;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.FixupApplicationRepository;
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
		return this.fixupApplicationRepository.findByStatusForTask(status, task.getId());
	}

	public FixupApplication create(FixupTask fixupTask) {
		HandyWorker handyWorker = this.handyWorkerService.findPrincipal();

		FixupApplication res = new FixupApplication();
		res.setAuthor(handyWorker);
		res.setFixupTask(fixupTask);
		return res;
	}

	public FixupApplication initialize(FixupApplication fixupApplication) {
		fixupApplication.setPublishDate(new Date());
		fixupApplication.setStatus("PENDING");

		return this.fixupApplicationRepository.save(fixupApplication);
	}
}
