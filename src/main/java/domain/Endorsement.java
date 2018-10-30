
package domain;

import java.util.ArrayList;
import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

public class Endorsement extends DomainEntity {

	//relations
	public Actor				author;
	public Actor				reference;
	//attributes
	public Date					publishDate;
	public ArrayList<String>	comments;


	@NotNull
	@Valid
	public Actor getAuthor() {
		return this.author;
	}

	public void setAuthor(final Actor author) {
		this.author = author;
	}

	@NotNull
	@Valid
	public Actor getReference() {
		return this.reference;
	}

	public void setReference(final Actor reference) {
		this.reference = reference;
	}

	@NotNull
	@Past
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
