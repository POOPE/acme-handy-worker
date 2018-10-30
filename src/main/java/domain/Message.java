
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Message extends DomainEntity {

	//relations
	public Actor					sender;
	public Collection<Actor>		recipients;
	public Collection<MessageBox>	container;
	//attributes
	public String					subject;
	public Date						deliveryDate;
	public String					message;
	public MessagePriority			priority;
	public Boolean					read;


	public String getSubject() {
		return this.subject;
	}

	public void setSubject(final String subject) {
		this.subject = subject;
	}

	@NotBlank
	public Collection<Actor> getRecipients() {
		return this.recipients;
	}

	public void setRecipients(final Collection<Actor> recipients) {
		this.recipients = recipients;
	}

	@NotBlank
	public Date getDeliveryDate() {
		return this.deliveryDate;
	}

	public void setDeliveryDate(final Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

	public MessagePriority getPriority() {
		return this.priority;
	}

	public void setPriority(final MessagePriority priority) {
		this.priority = priority;
	}

	public Boolean getRead() {
		return this.read;
	}

	public void setRead(final Boolean read) {
		this.read = read;
	}

	public Actor getSender() {
		return this.sender;
	}

	public void setSender(final Actor sender) {
		this.sender = sender;
	}

	public Collection<MessageBox> getContainer() {
		return this.container;
	}

	public void setContainer(final Collection<MessageBox> container) {
		this.container = container;
	}

}
