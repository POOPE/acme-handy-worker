
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.MessageBoxRepository;
import security.LoginService;
import security.UserAccount;
import domain.Actor;
import domain.Message;
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

	@Autowired
	private MessageService			messageService;


	//gives a list of children given a messageBox
	public Collection<MessageBox> findParentChain(MessageBox parent) {
		List<MessageBox> chain = new ArrayList<>();
		while (parent != null) {
			chain.add(parent);
			parent = this.messageBoxRepository.findByParent(parent.getId()).iterator().next();
		}
		return chain;
	}

	public void deleteAll(MessageBox messageBox) {
		//check if messageBox is a system box
		//data constraint
		Assert.isTrue(messageBox.getCategory().equals("USERBOX"), "System boxes cannot be deleted");
		//check owner is the one deleting
		//access constraint
		UserAccount userAccount = LoginService.getPrincipal();
		Assert.isTrue(messageBox.getOwner().equals(userAccount), "Owner inconsistency");

		//for each messageBox in the chain (messageBox + all of its children) delete all message and the messagebox
		Collection<MessageBox> chain = this.findParentChain(messageBox);
		for (MessageBox container : chain) {
			Collection<Message> messages = this.messageService.findByMessageBox(container);
			for (Message message : messages) {
				this.messageService.save(this.messageService.remove(message, container));
			}
			this.delete(container);
		}
	}

	public void delete(MessageBox messageBox) {
		this.messageBoxRepository.delete(messageBox.getId());
	}

	public Collection<MessageBox> findAllByActor(int actorId) {
		return this.messageBoxRepository.findByActor(actorId);
	}

	public MessageBox findByCategory(String category) {
		return this.messageBoxRepository.findByCategory(this.actorService.findByUser(LoginService.getPrincipal()).getId(), category).iterator().next();
	}

	public MessageBox findByCategory(Actor actor, String category) {
		return this.messageBoxRepository.findByCategory(actor.getId(), category).iterator().next();
	}

	public MessageBox save(MessageBox messageBox) {
		return this.messageBoxRepository.save(messageBox);
	}

}
