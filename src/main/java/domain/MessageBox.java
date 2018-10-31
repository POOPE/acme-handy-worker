
package domain;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class MessageBox extends DomainEntity {

	public Actor				owner;
	public String				name;
	public ArrayList<Message>	messages;
	public String				category;


	@NotNull
	public Actor getOwner() {
		return this.owner;
	}

	public void setOwner(final Actor owner) {
		this.owner = owner;
	}

	@NotBlank
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public ArrayList<Message> getMessages() {
		return this.messages;
	}

	public void setMessages(final ArrayList<Message> messages) {
		this.messages = messages;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(final String category) {
		this.category = category;
	}

}
