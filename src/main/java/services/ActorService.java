
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
import security.UserAccount;
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
		actor = this.initialize(actor);
		return actor;
	}

	public Actor suspicious(Actor actor) {
		actor.setFlagged(true);
		return this.save(actor);
	}

	public Actor save(Actor actor) {
		if (actor.getId() == 0) {
			Actor res = this.actorRepository.save(actor);
			res = this.postInitialize(res);
			return res;

		} else {
			return this.actorRepository.save(actor);
		}
	}

	public List<Actor> findAll() {
		return this.actorRepository.findAll();
	}

	public Actor findById(int id) {
		return this.actorRepository.findOne(id);
	}

	// when doing an update on an existing actor, use this method.
	// Make the assertion more complex while developing the cases needed:
	// For example, an admin updating an actor in order to ban him, needs to check that the user has ADMIN authority
	public Actor updateActor(Actor actor) {
		Assert.isTrue(this.findPrincipal().getId() == actor.getId());
		return this.save(actor);
	}

	//OTHER ----------------------------------------------------------------

	public Actor findByUsername(String username) {
		Actor res = null;
		try {
			UserAccount user = this.userAccountService.findByUsername(username);
			res = this.findByUser(user);
		} catch (Exception e) {

		}
		return res;
	}

	public Actor findByUser(UserAccount user) {
		return this.actorRepository.findByUser(user.getId());
	}

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

	public Collection<String> getAuthority(Actor actor) {
		Collection<Authority> auth = actor.getUser().getAuthorities();
		Collection<String> res = new HashSet<>();
		for (Authority a : auth) {
			res.add(a.getAuthority());
		}
		return res;
	}

	public void assertPrincipalAuthority(String auth) {
		Assert.isTrue(this.getPrincipalAuthority().contains(auth), "The user logged does not have authority to do this action.");

	}

	public Actor initialize(Actor actor) {
		actor.setPhoto("https://www.qualiscare.com/wp-content/uploads/2017/08/default-user-300x300.png");
		actor.setFlagged(false);
		actor.setUser(this.userAccountService.createUserAccount());
		Actor saved = this.actorRepository.save(actor);
		this.messageBoxService.createDefaultBoxes(saved);

		return saved;
	}

	public Actor postInitialize(Actor actor) {
		this.messageBoxService.createDefaultBoxes(actor);

		return actor;
	}

}
