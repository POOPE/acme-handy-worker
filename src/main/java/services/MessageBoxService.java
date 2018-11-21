
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.MessageBoxRepository;
import security.LoginService;
import domain.MessageBox;

@Service
@Transactional
public class MessageBoxService {

	@Autowired
	private MessageBoxRepository	messageBoxRepository;

	@Autowired
	private LoginService			loginService;

	@Autowired
	private ActorService			actorService;


	public Collection<MessageBox> findAllByActor(int actorId) {
		return this.messageBoxRepository.findByActor(actorId);
	}

	public MessageBox findByCategory(String category) {
		return this.messageBoxRepository.findByCategory(this.actorService.findByUser(LoginService.getPrincipal()).getId(), category).iterator().next();
	}

}
