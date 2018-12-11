
package services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Sponsor;
import repositories.SponsorRepository;
import security.Authority;

@Service
@Transactional
public class SponsorService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private SponsorRepository	sponsorRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private ActorService		actorService;

	@Autowired
	private UserAccountService	userAccountService;


	// Constructors -----------------------------------------------------------

	public SponsorService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Sponsor createSponsor() {
		Sponsor sponsor = new Sponsor();
		sponsor = (Sponsor) this.actorService.initialize(sponsor);
		sponsor = this.initializeSponsor(sponsor);
		return sponsor;
	}

	public Sponsor save(Sponsor sponsor) {
		if (sponsor.getId() == 0) {
			Sponsor res = this.sponsorRepository.save(sponsor);
			res = (Sponsor) this.actorService.postInitialize(res);
			return res;

		} else {
			return this.sponsorRepository.save(sponsor);
		}
	}

	public List<Sponsor> findAll() {

		return this.sponsorRepository.findAll();
	}

	public Sponsor findOne(int sponsorId) {
		Assert.isTrue(sponsorId > 0);
		return this.sponsorRepository.findOne(sponsorId);
	}
	// Other business methods -------------------------------------------------

	public Sponsor findPrincipal() {
		Assert.isTrue(this.actorService.getPrincipalAuthority().contains(Authority.SPONSOR), "The user logged is not a sponsor.");
		return (Sponsor) this.actorService.findPrincipal();
	}

	public Sponsor initializeSponsor(Sponsor sponsor) {
		sponsor.setUser(this.userAccountService.addAuthority(sponsor.getUser(), "SPONSOR"));
		return sponsor;
	}
}
