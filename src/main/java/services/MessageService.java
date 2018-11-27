
package services;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.MessageRepository;
import security.LoginService;
import security.UserAccount;
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

	public Message send(Message message) {
		//check for author is the one sending the message
		//access constraint
		Actor actor = this.actorService.findPrincipal();
		Assert.isTrue(message.getSender().equals(actor), "Error on send: Owner inconsistency");
		Assert.isTrue(message.getId() == 0, "Error on send: Message already exists");

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
		UserAccount userAccount = LoginService.getPrincipal();
		Assert.isTrue(from.getOwner().equals(userAccount), "Error on move: Owner inconsistency");
		Assert.isTrue(to.getOwner().equals(userAccount), "Error on move: Owner inconsistency");

		Collection<MessageBox> newRelation = message.getContainer();
		newRelation.remove(from);
		newRelation.add(to);
		message.setContainer(newRelation);

		return this.save(message);
	}
}
