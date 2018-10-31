
package domain;

import java.util.ArrayList;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

public class Section extends DomainEntity {

	//attributes
	public String				title;
	public String				description;
	public ArrayList<String>	photos;

	// relation

	public Tutorial				tutorial;


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
	public ArrayList<String> getPhotos() {
		return this.photos;
	}

	public void setPhotos(final ArrayList<String> photos) {
		this.photos = photos;
	}

	@NotNull
	public Tutorial getTutorial() {
		return this.tutorial;
	}

	public void setTutorial(final Tutorial tutorial) {
		this.tutorial = tutorial;
	}

}
