
package services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.AdminRepository;
import security.Authority;
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
		admin = (Admin) this.actorService.initializeActor(admin);
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

	public Admin findPrincipal() {
		Assert.isTrue(this.actorService.getPrincipalAuthority().contains(Authority.ADMIN), "The user logged is not an admin.");
		return (Admin) this.actorService.findPrincipal();
	}

	public Admin initializeAdmin(Admin admin) {
		admin.setUser(this.userAccountService.addAuthority(admin.getUser(), "ADMIN"));
		return admin;
	}
}
