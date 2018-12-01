
package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Report extends DomainEntity {

	//relations
	public Referee		author;
	public Complaint	complaint;
	//attribute
	public Date			publishDate;
	public String		description;
	public boolean		locked;
	public List<String>	attachments;
	public List<String>	comments;


	@ManyToOne(optional = false)
	public Referee getAuthor() {
		return this.author;
	}

	public void setAuthor(final Referee author) {
		this.author = author;
	}

	@ManyToOne(optional = false)
	public Complaint getComplaint() {
		return this.complaint;
	}

	public void setComplaint(final Complaint complaint) {
		this.complaint = complaint;
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

	@ElementCollection
	public List<String> getAttachments() {
		return this.attachments;
	}

	public void setAttachments(ArrayList<String> attachments) {
		this.attachments = attachments;
	}

	@ElementCollection
	public List<String> getComments() {
		return this.comments;
	}

	public void setComments(ArrayList<String> comments) {
		this.comments = comments;
	}

}
