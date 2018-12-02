
package domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Tutorial extends DomainEntity {

	//relations
	public Actor				author;
	public List<Section>		sections;
	//attributes
	public String				title;
	public String				description;
	public List<String>			photos;
	public Date					lastUpdate;
	public List<Sponsorship>	sponsorship;


	@ManyToMany
	public List<Sponsorship> getSponsorship() {
		return this.sponsorship;
	}

	public void setSponsorship(List<Sponsorship> sponsorship) {
		this.sponsorship = sponsorship;
	}

	public Date getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	@ManyToOne(optional = false)
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

	@ElementCollection
	public List<String> getPhotos() {
		return this.photos;
	}
	public void setPhotos(List<String> photos) {
		this.photos = photos;
	}

	@OneToMany(cascade = CascadeType.ALL)
	public List<Section> getSections() {
		return this.sections;
	}

	public void setSections(final List<Section> sections) {
		this.sections = sections;
	}

}
