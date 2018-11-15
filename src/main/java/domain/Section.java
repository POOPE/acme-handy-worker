
package domain;

import java.util.ArrayList;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Section extends DomainEntity {

	//attributes
	public String				title;
	public String				description;
	public ArrayList<String>	photos;


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

	public ArrayList<String> getPhotos() {
		return this.photos;
	}

	public void setPhotos(final ArrayList<String> photos) {
		this.photos = photos;
	}

}
