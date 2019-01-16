
package services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.MessageRepository;
import domain.Actor;
import domain.Message;
import domain.MessageBox;

@Service
@Transactional
public class MessageService {

	@Autowired
	private MessageRepository	messageRepository;

	@Autowired
	MessageBoxService			messageBoxService;

	@Autowired
	ActorService				actorService;

	@Autowired
	SiteConfigurationService	siteService;


	public List<Message> findByMessageBox(MessageBox messageBox) {
		return this.messageRepository.findByMessageBox(messageBox.getId());
	}

	public Message findById(int id) {
		return this.messageRepository.findOne(id);
	}

	public Message create() {
		Actor actor = this.actorService.findPrincipal();
		MessageBox mb = this.messageBoxService.findByCategory("OUTBOX");
		List<MessageBox> container = new ArrayList<>();
		List<Actor> recipients = new ArrayList<>();
		container.add(mb);

		Message m = new Message();
		m.setRecipients(recipients);
		m.setContainer(container);
		m.setSender(actor);

		return m;
	}

	public Message send(Message message, String alias) {
		//check for author is the one sending the message
		//access constraint
		Actor actor = this.actorService.findPrincipal();
		Assert.isTrue(message.getSender().equals(actor), "Error on send: Owner inconsistency");
		message.setSenderAlias(alias);
		message.setDeliveryDate(new Date());
		String destinedCategory = "INBOX";
		for (String spamword : this.siteService.find().getSpamWords()) {
			if (message.getBody().contains(spamword)) {
				destinedCategory = "SPAMBOX";
				this.actorService.suspicious(actor);
				break;
			}
		}
		//for each recipient, update the message containers to include their INBOX
		for (Actor recipient : message.getRecipients()) {
			MessageBox inbox = this.messageBoxService.findByCategory(recipient, destinedCategory);
			List<MessageBox> newRelation = message.getContainer();
			if (newRelation == null) {
				newRelation = new ArrayList<MessageBox>();
			}
			newRelation.add(inbox);
			message.setContainer(newRelation);
		}
		return this.save(message);
	}

	public Message send(Message message) {
		//check for author is the one sending the message
		//access constraint
		Actor actor = this.actorService.findPrincipal();
		Assert.isTrue(message.getSender().equals(actor), "Error on send: Owner inconsistency");
		if (message.getSenderAlias() == null) {
			message.setSenderAlias(actor.getName() + " " + actor.getSurname());
		}

		message.setDeliveryDate(new Date());
		String destinedCategory = "INBOX";
		for (String spamword : this.siteService.find().getSpamWords()) {
			if (message.getBody().contains(spamword)) {
				this.actorService.suspicious(actor);
				destinedCategory = "SPAMBOX";
				break;
			}
		}
		//for each recipient, update the message containers to include their INBOX
		for (Actor recipient : message.getRecipients()) {
			MessageBox inbox = this.messageBoxService.findByCategory(recipient, destinedCategory);
			List<MessageBox> newRelation = message.getContainer();
			if (newRelation == null) {
				newRelation = new ArrayList<MessageBox>();
			}
			newRelation.add(inbox);
			message.setContainer(newRelation);
		}
		return this.save(message);
	}

	public Message remove(Message message, MessageBox messageBox) {
		//check user owns messageBox
		//access constraint
		Actor actor = this.actorService.findPrincipal();
		Assert.isTrue(messageBox.getOwner().equals(actor), "Error on remove: Owner inconsistency");

		List<MessageBox> newRelation = message.getContainer();
		newRelation.remove(messageBox);
		if (messageBox.getCategory() == "TRASHBOX") {
			message.setContainer(newRelation);
		} else {
			MessageBox trashBox = this.messageBoxService.findByCategory(actor, "TRASHBOX");
			newRelation.add(trashBox);
			message.setContainer(newRelation);
		}

		return this.save(message);
	}

	Message save(Message message) {
		return this.messageRepository.save(message);
	}

	public Message move(Message message, MessageBox from, MessageBox to) {
		//check if user owns both messageBoxes
		//access constraint
		Actor actor = this.actorService.findPrincipal();
		Assert.isTrue(from.getOwner().equals(actor), "Error on move: Owner inconsistency");
		Assert.isTrue(to.getOwner().equals(actor), "Error on move: Owner inconsistency");

		List<MessageBox> newRelation = message.getContainer();
		newRelation.remove(from);
		newRelation.add(to);
		message.setContainer(newRelation);

		return this.save(message);
	}
}
