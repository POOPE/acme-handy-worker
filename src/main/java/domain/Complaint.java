
package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Complaint extends DomainEntity {

	//relations
	public FixupTask	reference;
	//attributes
	public String		ticker;
	public Date			publishDate;
	public String		description;
	public List<String>	attachments;


	@ManyToOne(optional = false)
	public FixupTask getReference() {
		return this.reference;
	}

	public void setReference(final FixupTask reference) {
		this.reference = reference;
	}

	@NotBlank
	public String getTicker() {
		return this.ticker;
	}

	public void setTicker(final String ticker) {
		this.ticker = ticker;
	}

	@NotNull
	@Past
	public Date getPublishDate() {
		return this.publishDate;
	}

	public void setPublishDate(final Date publishDate) {
		this.publishDate = publishDate;
	}

	@NotBlank
	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	@ElementCollection
	public List<String> getAttachments() {
		return this.attachments;
	}

	public void setAttachments(ArrayList<String> attachments) {
		this.attachments = attachments;
	}

}
