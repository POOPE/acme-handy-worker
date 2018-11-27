
package services;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.ActorRepository;
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

		actor.setPhoto("https://www.qualiscare.com/wp-content/uploads/2017/08/default-user-300x300.png");
		actor.setFlagged(false);

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

}
