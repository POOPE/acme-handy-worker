
package domain;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class EndorserRecord extends Record {

	public String			endorserName;
	public String			email;
	public String			phone;

	public SocialProfile	socialProfile;


	@NotBlank
	public String getEndorserName() {
		return this.endorserName;
	}

	public void setEndorserName(final String endorserName) {
		this.endorserName = endorserName;
	}

	@Email
	@NotBlank
	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	@NotBlank
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(final String phone) {
		this.phone = phone;
	}

	public SocialProfile getSocialProfile() {
		return this.socialProfile;
	}

	public void setSocialProfile(final SocialProfile socialProfile) {
		this.socialProfile = socialProfile;
	}

}
