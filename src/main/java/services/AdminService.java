
package services;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.AdminRepository;
import security.Authority;
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
		return this.adminRepository.save(admin);
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
			int good = this.commentsContainsWords(endorsement.getComments(), goodWords);
			int bad = this.commentsContainsWords(endorsement.getComments(), badWords);
			int totalCount = good + bad;

			float grossScore = good - bad;
			float posNeg = grossScore > 0 ? 1 : -1;

			float normalizedUnsingedScore = totalCount / grossScore;
			float finalScore = normalizedUnsingedScore * posNeg;

			Actor actor = endorsement.getReference();
			if (this.actorService.getAuthority(actor).equals("CUSTOMER")) {
				Customer c = this.customerService.findOne(actor.getId());
				c.setScore(finalScore);
				this.customerService.save(c);
			} else if (this.actorService.getAuthority(actor).equals("HANDYWORKER")) {
				HandyWorker h = this.handyWorkerService.findOne(actor.getId());
				h.setScore(finalScore);
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
		Assert.isTrue(this.actorService.getPrincipalAuthority().contains(Authority.ADMIN), "The user logged is not an admin.");
		return (Admin) this.actorService.findPrincipal();
	}

	public Admin initializeAdmin(Admin admin) {
		admin.setUser(this.userAccountService.addAuthority(admin.getUser(), "ADMIN"));
		return admin;
	}
}
