
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Message extends DomainEntity {

	//relations
	public Actor					sender;
	public Collection<Actor>		recipients;
	public Collection<MessageBox>	container;
	//attributes
	public String					subject;
	public Date						deliveryDate;
	public String					body;
	public String					priority;


	@ManyToOne(optional = false)
	public Actor getSender() {
		return this.sender;
	}

	public void setSender(final Actor sender) {
		this.sender = sender;
	}

	// admin can send message to multiple recipients
	@ManyToMany
	public Collection<Actor> getRecipients() {
		return this.recipients;
	}

	public void setRecipients(final Collection<Actor> recipients) {
		this.recipients = recipients;
	}

	@NotNull
	@ManyToMany
	public Collection<MessageBox> getContainer() {
		return this.container;
	}

	public void setContainer(final Collection<MessageBox> container) {
		this.container = container;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(final String subject) {
		this.subject = subject;
	}

	@Past
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDeliveryDate() {
		return this.deliveryDate;
	}

	public void setDeliveryDate(final Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getBody() {
		return this.body;
	}

	public void setBody(final String body) {
		this.body = body;
	}

	@NotBlank
	public String getPriority() {
		return this.priority;
	}

	public void setPriority(final String priority) {
		this.priority = priority;
	}
}
