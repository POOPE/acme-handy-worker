
package services;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ActorRepository;
import security.Authority;
import security.LoginService;
import domain.Actor;

@Service
@Transactional
public class ActorService {

	@Autowired
	private ActorRepository		actorRepository;

	@Autowired
	private MessageBoxService	messageBoxService;


	//CRUD ---------------------------------------------------------------

	public Actor create() {
		Actor actor = new Actor();
		actor = this.initializeActor(actor);

		return actor;
	}

	public Actor save(Actor actor) {
		return this.actorRepository.save(actor);
	}

	public List<Actor> findAll() {
		return this.actorRepository.findAll();
	}

	public Actor find(Actor actor) {
		return this.actorRepository.findOne(actor.getId());
	}

	// when doing an update on an existing actor, use this method.
	// Make the assertion more complex while developing the cases needed:
	// For example, an admin updating an actor in order to ban him, needs to check that the user has ADMIN authority
	public Actor updateActor(Actor actor) {
		Assert.isTrue(this.findPrincipal().getId() == actor.getId());
		return this.save(actor);
	}

	//OTHER ----------------------------------------------------------------

	public Collection<Actor> findAllSuspicious() {
		return this.actorRepository.findAllSuspicious();
	}

	public Actor findByEmail(String email) {
		return this.actorRepository.findByEmail(email).iterator().next();
	}

	public Actor findPrincipal() {
		return this.actorRepository.findByUser(LoginService.getPrincipal().getId());
	}

	public String getPrincipalAuthority() {
		return ((Authority) this.findPrincipal().getUser().getAuthorities().toArray()[0]).getAuthority();
	}

	public Actor initializeActor(Actor actor) {
		actor.setPhoto("https://www.qualiscare.com/wp-content/uploads/2017/08/default-user-300x300.png");
		actor.setFlagged(false);

		// initialize message boxes

		return actor;
	}

}
