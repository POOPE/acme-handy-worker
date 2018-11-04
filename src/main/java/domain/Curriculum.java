
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Curriculum extends DomainEntity {

	//attributes
	public String			ticker;
	public String			fullName;
	public String			email;
	public String			phoneNumber;
	public String			photo;
	//relations
	public SocialProfile	socialProfile;
	public Record			record;
	public HandyWorker		owner;


	@OneToOne(optional = false)
	public HandyWorker getOwner() {
		return this.owner;
	}

	public void setOwner(final HandyWorker owner) {
		this.owner = owner;
	}

	// Do constraint of pattern 'yymmdd-xxxxxx'
	@NotBlank
	public String getTicker() {
		return this.ticker;
	}

	public void setTicker(final String ticker) {
		this.ticker = ticker;
	}

	@NotBlank
	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(final String fullName) {
		this.fullName = fullName;
	}

	@Email
	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(final String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@URL
	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(final String photo) {
		this.photo = photo;
	}

	public SocialProfile getSocialProfile() {
		return this.socialProfile;
	}

	public void setSocialProfile(final SocialProfile socialProfile) {
		this.socialProfile = socialProfile;
	}

	@OneToMany(cascade = CascadeType.ALL)
	public Record getRecord() {
		return this.record;
	}

	public void setRecord(final Record record) {
		this.record = record;
	}

}
