
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Note {

	//relations
	public Report				reference;
	public Actor				author;
	//attributes
	public Date					publishDate;
	public String				description;
	public Collection<String>	comments;


	@ManyToOne(optional = false)
	public Report getReference() {
		return this.reference;
	}

	public void setReference(final Report reference) {
		this.reference = reference;
	}

	@NotNull
	public Actor getAuthor() {
		return this.author;
	}

	public void setAuthor(final Actor author) {
		this.author = author;
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

	public Collection<String> getComments() {
		return this.comments;
	}

	public void setComments(final Collection<String> comments) {
		this.comments = comments;
	}

}
