
package services;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.ActorRepository;
import security.UserAccount;
import domain.Actor;

@Service
@Transactional
public class ActorService {

	@Autowired
	private ActorRepository	actorRepository;


	public List<Actor> findAll() {
		return this.actorRepository.findAll();
	}

	public Actor findById(int id) {
		return this.actorRepository.findOne(id);
	}

	public Collection<Actor> findAllSuspicious() {
		return this.actorRepository.findAllSuspicious();
	}

	public Actor findByEmail(String email) {
		return this.actorRepository.findByEmail(email).iterator().next();
	}

	public Actor findByUser(UserAccount user) {
		return this.actorRepository.findByUser(user.getId()).iterator().next();
	}

}
