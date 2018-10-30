
package domain;

import java.util.Collection;
import java.util.Date;

public class Note {

	//relations
	public Report				reference;
	public Actor				author;
	//attributes
	public Date					publishDate;
	public String				description;
	public Collection<String>	comments;


	public Report getReference() {
		return this.reference;
	}

	public void setReference(final Report reference) {
		this.reference = reference;
	}

	public Actor getAuthor() {
		return this.author;
	}

	public void setAuthor(final Actor author) {
		this.author = author;
	}

	public Date getPublishDate() {
		return this.publishDate;
	}

	public void setPublishDate(final Date publishDate) {
		this.publishDate = publishDate;
	}

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
