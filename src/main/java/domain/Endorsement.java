
package domain;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Endorsement extends DomainEntity {

	//relations
	public Actor				author;
	public Actor				reference;
	//attributes
	public Date					publishDate;
	public ArrayList<String>	comments;


	@ManyToOne(optional = false)
	public Actor getAuthor() {
		return this.author;
	}

	public void setAuthor(final Actor author) {
		this.author = author;
	}

	@OneToMany
	public Actor getReference() {
		return this.reference;
	}

	public void setReference(final Actor reference) {
		this.reference = reference;
	}

	@NotNull
	@Past
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getPublishDate() {
		return this.publishDate;
	}

	public void setPublishDate(final Date publishDate) {
		this.publishDate = publishDate;
	}

	public ArrayList<String> getComments() {
		return this.comments;
	}

	public void setComments(final ArrayList<String> comments) {
		this.comments = comments;
	}

}
