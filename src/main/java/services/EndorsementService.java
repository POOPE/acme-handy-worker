
package services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.EndorsementRepository;
import domain.Actor;
import domain.Endorsement;

@Service
@Transactional
public class EndorsementService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private EndorsementRepository	endorsementRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private ActorService			actorService;


	// Constructors -----------------------------------------------------------

	public EndorsementService() {
		super();
	}

	public Endorsement save(Endorsement endorsement) {
		return this.endorsementRepository.save(endorsement);
	}

	public List<Endorsement> findAll() {
		return this.endorsementRepository.findAll();
	}

	public Endorsement create() {
		Endorsement endorsement = new Endorsement();
		endorsement.setComments(new ArrayList<String>());
		return endorsement;
	}

	public Endorsement publish(Endorsement endorsement) {
		endorsement.setPublishDate(new Date());
		endorsement.setAuthor(this.actorService.findPrincipal());

		return this.endorsementRepository.save(endorsement);
	}

	public Endorsement findById(int endorsementId) {
		Assert.isTrue(endorsementId > 0);
		return this.endorsementRepository.findOne(endorsementId);
	}

	public List<Endorsement> findByPrincipal() {
		Actor actor = this.actorService.findPrincipal();
		return this.endorsementRepository.findByReferencedActor(actor.getId());
	}

	public List<Endorsement> findWrittenByPrincipal() {
		Actor actor = this.actorService.findPrincipal();
		return this.endorsementRepository.findByAuthor(actor.getId());
	}

	public List<Endorsement> findByActor(Actor actor) {
		return this.endorsementRepository.findByReferencedActor(actor.getId());
	}

}
