
package services;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
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


	public List<Message> findByMessageBox(MessageBox messageBox) {
		return this.messageRepository.findByMessageBox(messageBox.getId());
	}

	public Message create() {
		Actor actor = this.actorService.findPrincipal();
		MessageBox mb = this.messageBoxService.findByCategory("OUTBOX");
		Collection<MessageBox> container = new HashSet<>();
		container.add(mb);

		Message m = new Message();
		m.setContainer(container);
		m.setSender(actor);

		return this.messageRepository.save(m);
	}

	public Message send(Message message) {
		//check for author is the one sending the message
		//access constraint
		Actor actor = this.actorService.findPrincipal();
		Assert.isTrue(message.getSender().equals(actor), "Error on send: Owner inconsistency");

		message.setDeliveryDate(new Date());
		//for each recipient, update the message containers to include their INBOX
		for (Actor recipient : message.getRecipients()) {
			MessageBox inbox = this.messageBoxService.findByCategory(recipient, "INBOX");
			Collection<MessageBox> newRelation = message.getContainer();
			newRelation.add(inbox);
			message.setContainer(newRelation);

			this.save(message);
		}
		return message;
	}

	public Message remove(Message message, MessageBox messageBox) {
		//check user owns messageBox
		//access constraint
		Actor actor = this.actorService.findPrincipal();
		Assert.isTrue(messageBox.getOwner().equals(actor), "Error on remove: Owner inconsistency");

		Collection<MessageBox> newRelation = message.getContainer();
		newRelation.remove(messageBox);
		message.setContainer(newRelation);

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

		Collection<MessageBox> newRelation = message.getContainer();
		newRelation.remove(from);
		newRelation.add(to);
		message.setContainer(newRelation);

		return this.save(message);
	}
}
