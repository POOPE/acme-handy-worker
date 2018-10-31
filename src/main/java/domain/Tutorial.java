
package domain;

import java.util.ArrayList;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

public class Tutorial extends DomainEntity {

	//relations
	public Actor				author;
	public ArrayList<Section>	sections;
	//attributes
	public String				title;
	public String				description;
	public ArrayList<String>	photos;
	
	
	public Actor getAuthor() {
		return author;
	}
	public void setAuthor(Actor author) {
		this.author = author;
	}
	public ArrayList<Section> getSections() {
		return sections;
	}
	public void setSections(ArrayList<Section> sections) {
		this.sections = sections;
	}
	@NotBlank
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@URL
	public ArrayList<String> getPhotos() {
		return photos;
	}
	public void setPhotos(ArrayList<String> photos) {
		this.photos = photos;
	}
	
	

}
