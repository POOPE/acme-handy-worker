
package services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.ReportRepository;
import domain.Complaint;
import domain.Referee;
import domain.Report;

@Service
@Transactional
public class ReportService {

	@Autowired
	private ReportRepository	reportRepo;

	@Autowired
	private RefereeService		refereeService;


	public Report findById(int id) {
		return this.reportRepo.findOne(id);
	}

	public List<Report> findAll() {
		return this.reportRepo.findAll();
	}

	public Report create() {
		Referee referee = this.refereeService.findPrincipal();
		Report res = new Report();
		res.setAuthor(referee);
		res.setAttachments(new ArrayList<String>());

		return res;
	}

	public Report save(Report r) {
		return this.reportRepo.save(r);
	}

	public Report publish(Report report) {
		report.setPublishDate(new Date());
		report.setLocked(false);
		report.setComments(new ArrayList<String>());

		return this.save(report);
	}

	public Report saveFinalMode(Report report) {
		report.setLocked(true);
		return this.save(report);
	}

	//also must be locked
	public List<Report> findByComplaint(Complaint complaint) {
		return this.reportRepo.findByComplaint(complaint.getId());
	}
}
