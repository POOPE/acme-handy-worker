
package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Actor;
import domain.Sponsorship;
import repositories.SponsorshipRepository;

@Service
@Transactional
public class SponsorshipService {

	@Autowired
	private SponsorService			sponsorService;

	@Autowired
	private SponsorshipRepository	sponsorshipRepo;


	public Sponsorship create() {
		Sponsorship res = new Sponsorship();
		this.initialize(res);

		return res;
	}

	public List<Sponsorship> findAll() {
		return this.sponsorshipRepo.findAll();
	}

	public Sponsorship findOne(Integer actorId) {
		return this.sponsorshipRepo.findOne(actorId);
	}

	public List<Sponsorship> findByPrinciapl() {
		return this.sponsorshipRepo.findByActor(this.sponsorService.findPrincipal().getId());
	}

	public List<Sponsorship> findByAuthor(Actor actor) {
		return this.sponsorshipRepo.findByActor(actor.getId());
	}

	public Sponsorship initialize(Sponsorship s) {
		s.setAuthor(this.sponsorService.findPrincipal());

		return this.save(s);
	}

	public Sponsorship save(Sponsorship s) {
		return this.sponsorshipRepo.save(s);
	}
}
