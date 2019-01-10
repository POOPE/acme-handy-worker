
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.AdminRepository;
import domain.Actor;
import domain.Admin;
import domain.Customer;
import domain.Endorsement;
import domain.HandyWorker;
import domain.SiteConfiguration;

@Service
@Transactional
public class AdminService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private AdminRepository				adminRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private ActorService				actorService;

	@Autowired
	private CustomerService				customerService;

	@Autowired
	private HandyWorkerService			handyWorkerService;

	@Autowired
	private UserAccountService			userAccountService;

	@Autowired
	private EndorsementService			endorsementService;

	@Autowired
	private SiteConfigurationService	siteConfigService;

	@Autowired
	private FixupTaskService			fixupTaskService;


	// Constructors -----------------------------------------------------------

	public AdminService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Admin createAdmin() {
		Admin admin = new Admin();
		admin = (Admin) this.actorService.initialize(admin);
		admin = this.initializeAdmin(admin);
		return admin;
	}

	public Admin save(Admin admin) {
		if (admin.getId() == 0) {
			Md5PasswordEncoder encoder = new Md5PasswordEncoder();
			admin.getUser().setPassword(encoder.encodePassword(admin.getUser().getPassword(), null));
			Admin res = this.adminRepository.save(admin);
			res = (Admin) this.actorService.postInitialize(res);
			return res;

		} else {
			Admin current = this.findOne(admin.getId());
			if (current.getUser().getPassword() != admin.getUser().getPassword()) {
				Md5PasswordEncoder encoder = new Md5PasswordEncoder();
				admin.getUser().setPassword(encoder.encodePassword(admin.getUser().getPassword(), null));
			}
			return this.adminRepository.save(admin);
		}
	}

	public Admin findOne(int adminId) {
		Assert.isTrue(adminId > 0);
		return this.adminRepository.findOne(adminId);
	}
	// Other business methods -------------------------------------------------

	public void UpdateScores() {
		List<Endorsement> endorsements = this.endorsementService.findAll();
		SiteConfiguration siteConfig = this.siteConfigService.find();
		Collection<String> goodWords = siteConfig.getGoodWords();
		Collection<String> badWords = siteConfig.getBadWords();

		for (Endorsement endorsement : endorsements) {
			float good = this.commentsContainsWords(endorsement.getComments(), goodWords);
			float bad = this.commentsContainsWords(endorsement.getComments(), badWords);
			float totalCount = good + bad;

			float grossScore = good - bad;
			float posNeg = grossScore > 0 ? 1 : -1;

			float normalizedUnsingedScore = grossScore / totalCount;
			float finalScore = normalizedUnsingedScore * posNeg;

			Actor actor = endorsement.getReference();
			if (this.actorService.getAuthority(actor).equals("CUSTOMER")) {
				Customer c = this.customerService.findOne(actor.getId());
				c.setScore((c.getScore() + finalScore) / 2);
				this.customerService.save(c);
			} else if (this.actorService.getAuthority(actor).equals("HANDYWORKER")) {
				HandyWorker h = this.handyWorkerService.findOne(actor.getId());
				h.setScore((h.getScore() + finalScore) / 2);
				this.handyWorkerService.save(h);
			}
		}
	}
	private int commentsContainsWords(Collection<String> comments, Collection<String> words) {
		int res = 0;
		for (String comment : comments) {
			for (String word : words) {
				if (comment.contains(word)) {
					res++;
				}
			}
		}

		return res;
	}

	public Admin findPrincipal() {
		this.actorService.assertPrincipalAuthority("ADMIN");
		return (Admin) this.actorService.findPrincipal();
	}

	public Admin initializeAdmin(Admin admin) {
		admin.setUser(this.userAccountService.addAuthority(admin.getUser(), "ADMIN"));
		return admin;
	}

	// dashboard services

	public List<Double> fixupTasksPerCustomerStats() {
		List<Double> res = new ArrayList<Double>();
		List<Long> perUser = this.adminRepository.fixupTasksPerCustomer();
		Integer sum = 0;
		Integer min = null;
		Integer max = null;
		for (Integer i = 0; i < perUser.size(); i++) {
			sum += perUser.get(i).intValue();
			if (min == null || perUser.get(i).intValue() < min) {
				min = perUser.get(i).intValue();
			}
			if (max == null || perUser.get(i).intValue() > max) {
				max = perUser.get(i).intValue();
			}
		}
		Double average = (sum.doubleValue() / Integer.valueOf(perUser.size()).doubleValue());

		Double deviation = 0.0;
		for (Integer i = 0; i < perUser.size(); i++) {
			deviation += deviation + Math.pow(perUser.get(i).doubleValue() - average, 2);
		}

		res.add(average);
		res.add(min.doubleValue());
		res.add(max.doubleValue());
		res.add(deviation);
		return res;
	}

	public List<Double> applicationsPerFixupTaskStats() {
		List<Double> res = new ArrayList<Double>();
		List<Long> perTask = this.adminRepository.applicationsPerFixupTask();
		Integer sum = 0;
		Integer min = null;
		Integer max = null;
		for (Integer i = 0; i < perTask.size(); i++) {
			sum += perTask.get(i).intValue();
			if (min == null || perTask.get(i) < min) {
				min = perTask.get(i).intValue();
			}
			if (max == null || perTask.get(i) > max) {
				max = perTask.get(i).intValue();
			}
		}
		Double average = (sum.doubleValue() / Integer.valueOf(perTask.size()).doubleValue());

		Double deviation = 0.0;
		for (Integer i = 0; i < perTask.size(); i++) {
			deviation += deviation + Math.pow(perTask.get(i).doubleValue() - average, 2);
		}

		res.add(average);
		res.add(min.doubleValue());
		res.add(max.doubleValue());
		res.add(deviation);
		return res;
	}

	public List<Double> maximumPricePerFixupTaskStats() {
		List<Double> res = new ArrayList<Double>();
		res.add(this.adminRepository.avgMaximumPricePerFixupTaskStats());
		res.add(this.adminRepository.minMaximumPricePerFixupTaskStats());
		res.add(this.adminRepository.maxMaximumPricePerFixupTaskStats());
		res.add(this.adminRepository.devMaximumPricePerFixupTaskStats());
		return res;
	}
	public Double pendingApplicationsRatioStats() {
		return this.adminRepository.pendingApplicationsRatio();
	}

	public Double acceptedApplicationsRatioStats() {
		return this.adminRepository.acceptedApplicationsRatio();
	}

	public Double rejectedApplicationsRatioStats() {
		return this.adminRepository.rejectedApplicationsRatio();
	}

	public Double pendingLapsedApplicationRatioStats() {
		return this.adminRepository.pendingLapsedApplicationsRatio();
	}

	public List<Customer> customersWithMostApplicationsStats() {
		List<Integer> customersTuple = this.adminRepository.getCustomerMostFixupTasks();
		Double customerAverageIncreased = (this.fixupTasksPerCustomerStats().get(0) * 1.1);
		List<Customer> res = new ArrayList<Customer>();
		for (Integer i = 0; i < customersTuple.size(); i++) {
			Integer numTasks = this.fixupTaskService.findByCustomer(customersTuple.get(i)).size();
			if (numTasks > customerAverageIncreased) {
				res.add(this.customerService.findOne(customersTuple.get(i)));
			} else {
				i = customersTuple.size();
			}
		}
		return res;
	}
}
