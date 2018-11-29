
package services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.AdminRepository;
import domain.Admin;

@Service
@Transactional
public class AdminService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private AdminRepository		adminRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private ActorService		actorService;

	@Autowired
	private UserAccountService	userAccountService;


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
			Admin res = this.adminRepository.save(admin);
			res = (Admin) this.actorService.postInitialize(res);
			return res;

		} else {
			return this.adminRepository.save(admin);
		}
	}

	public Admin findOne(int adminId) {
		Assert.isTrue(adminId > 0);
		return this.adminRepository.findOne(adminId);
	}
	// Other business methods -------------------------------------------------

	public Admin findPrincipal() {
		this.actorService.assertPrincipalAuthority("ADMIN");
		return (Admin) this.actorService.findPrincipal();
	}

	public Admin initializeAdmin(Admin admin) {
		admin.setUser(this.userAccountService.addAuthority(admin.getUser(), "ADMIN"));
		return admin;
	}
}
