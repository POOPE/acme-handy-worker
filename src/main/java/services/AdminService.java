
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
	private AdminRepository	adminRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private ActorService	actorService;


	// Constructors -----------------------------------------------------------

	public AdminService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Admin createAdmin() {
		Admin admin = new Admin();
		admin = (Admin) this.actorService.initializeActor(admin);
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
		Assert.isTrue(this.actorService.getPrincipalAuthority() == "ADMIN", "The user logged is not an admin.");
		return (Admin) this.actorService.findPrincipal();
	}
}
