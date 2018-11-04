
package domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Report extends DomainEntity {

	//relations
	public Actor				author;
	public Complaint			reference;
	//attribute
	public Date					publishDate;
	public String				description;
	public boolean				locked;
	public Collection<String>	attachments;
	public ArrayList<String>	comments;


	@NotNull
	public Actor getAuthor() {
		return this.author;
	}

	public void setAuthor(final Actor author) {
		this.author = author;
	}

	@NotNull
	public Complaint getReference() {
		return this.reference;
	}

	public void setReference(final Complaint reference) {
		this.reference = reference;
	}

	@Past
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
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

	public boolean isLocked() {
		return this.locked;
	}

	public void setLocked(final boolean locked) {
		this.locked = locked;
	}

	public Collection<String> getAttachments() {
		return this.attachments;
	}

	public void setAttachments(final Collection<String> attachments) {
		this.attachments = attachments;
	}

	public ArrayList<String> getComments() {
		return this.comments;
	}

	public void setComments(final ArrayList<String> comments) {
		this.comments = comments;
	}

}
