
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


	public MessageBox create() {
		MessageBox messageBox = new MessageBox();
		messageBox.setOwner(this.actorService.findPrincipal());
		messageBox.setCategory("USERBOX");
		return messageBox;
	}

	public MessageBox save(MessageBox messageBox) {

		Assert.isTrue(messageBox.getCategory().equals("USERBOX"), "Error on update: Cannot modify system box");

		return this.messageBoxRepository.save(messageBox);
	}

	public List<MessageBox> createDefaultBoxes(Actor actor) {
		List<MessageBox> res = new ArrayList<>();
		res.add(this.systemCreate("Inbox", actor));
		res.add(this.systemCreate("Outbox", actor));
		res.add(this.systemCreate("Spambox", actor));
		res.add(this.systemCreate("Trashbox", actor));
		return res;
	}

	private MessageBox systemCreate(String name, Actor owner) {
		MessageBox res = new MessageBox();
		res.setCategory(name.toUpperCase());
		res.setName(name);
		res.setOwner(owner);
		return this.messageBoxRepository.save(res);
	}

	//gives a list of children given a messageBox
	private Collection<MessageBox> findParentChain(MessageBox parent) {
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
		Assert.isTrue(messageBox.getCategory().equals("USERBOX"), "Error on delete: System boxes cannot be deleted");
		//check owner is the one deleting
		//access constraint
		Actor actor = this.actorService.findPrincipal();
		Assert.isTrue(messageBox.getOwner().equals(actor), "Error on delete: Owner inconsistency");

		//for each messageBox in the chain (messageBox + all of its children) delete all message and the messagebox
		Collection<MessageBox> chain = this.findParentChain(messageBox);
		for (MessageBox container : chain) {
			Assert.isTrue(container.getOwner().equals(actor), "Error on delete: Owner inconsistency");
			Collection<Message> messages = this.messageService.findByMessageBox(container);
			for (Message message : messages) {
				this.messageService.save(this.messageService.remove(message, container));
			}
			this.delete(container);
		}
	}

	//can only be accessed from deleteall method
	private void delete(MessageBox messageBox) {
		this.messageBoxRepository.delete(messageBox.getId());
	}

	public List<MessageBox> findAllByActor(Actor actor) {
		return this.messageBoxRepository.findByActor(actor.getId());
	}

	public List<MessageBox> findAllLogged() {
		Actor actor = this.actorService.findPrincipal();
		List<MessageBox> res = this.messageBoxRepository.findByActor(actor.getId());
		return res;
	}

	public MessageBox findByCategory(String category) {
		return this.messageBoxRepository.findByCategory(this.actorService.findPrincipal().getId(), category).iterator().next();
	}

	public MessageBox findByCategory(Actor actor, String category) {
		return this.messageBoxRepository.findByCategory(actor.getId(), category).get(0);
	}

}
