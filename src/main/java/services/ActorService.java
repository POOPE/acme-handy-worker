
package services;

import java.util.Collection;
import java.util.HashSet;
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

	@Autowired
	private UserAccountService	userAccountService;


	//CRUD ---------------------------------------------------------------

	public Actor create() {
		Actor actor = new Actor();

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

	public Collection<String> getPrincipalAuthority() {
		Collection<Authority> auth = this.findPrincipal().getUser().getAuthorities();
		Collection<String> res = new HashSet<>();
		for (Authority a : auth) {
			res.add(a.getAuthority());
		}
		return res;
	}

	public Actor initialize(Actor actor) {
		actor.setPhoto("https://www.qualiscare.com/wp-content/uploads/2017/08/default-user-300x300.png");
		actor.setFlagged(false);
		actor.setUser(this.userAccountService.createUserAccount());
		Actor saved = this.actorRepository.save(actor);
		this.messageBoxService.createDefaultBoxes(saved);

		return saved;
	}

}
