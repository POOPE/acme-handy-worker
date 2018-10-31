
package domain;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

public class ProfessionalRecord extends Record {

	public String	companyName;
	public Date		startDate;
	public Date		endDate;
	public String	role;
	public String	attachmentUrl;


	@NotBlank
	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(final String companyName) {
		this.companyName = companyName;
	}

	@NotNull
	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}

	@NotBlank
	public String getRole() {
		return this.role;
	}

	public void setRole(final String role) {
		this.role = role;
	}

	@URL
	public String getAttachmentUrl() {
		return this.attachmentUrl;
	}

	public void setAttachmentUrl(final String attachmentUrl) {
		this.attachmentUrl = attachmentUrl;
	}

}
