
package domain;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

public class Curriculum extends DomainEntity {

	public String			ticker;
	public String			fullName;
	public String			email;
	public String			phoneNumber;
	public String			photo;

	public HandyWorker		handyWork;
	public SocialProfile	socialProfile;


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

	@NotNull
	public HandyWorker getHandyWork() {
		return this.handyWork;
	}

	public void setHandyWork(final HandyWorker handyWork) {
		this.handyWork = handyWork;
	}

	public SocialProfile getSocialProfile() {
		return this.socialProfile;
	}

	public void setSocialProfile(final SocialProfile socialProfile) {
		this.socialProfile = socialProfile;
	}
}
