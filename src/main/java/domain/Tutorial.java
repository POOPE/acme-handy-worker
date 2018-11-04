
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Tutorial extends DomainEntity {

	//relations
	public Actor				author;
	public Collection<Section>	sections;
	//attributes
	public String				title;
	public String				description;
	public Collection<String>	photos;


	@NotNull
	public Actor getAuthor() {
		return this.author;
	}
	public void setAuthor(final Actor author) {
		this.author = author;
	}

	@NotBlank
	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}
	@URL
	public Collection<String> getPhotos() {
		return this.photos;
	}
	public void setPhotos(final Collection<String> photos) {
		this.photos = photos;
	}

	@OneToMany(cascade = CascadeType.ALL)
	public Collection<Section> getSections() {
		return this.sections;
	}

	public void setSections(final Collection<Section> sections) {
		this.sections = sections;
	}

}
