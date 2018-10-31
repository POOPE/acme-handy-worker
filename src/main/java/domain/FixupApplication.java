
package domain;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

public class FixupApplication extends DomainEntity {

	//relations
	public FixupTask			fixupTask;
	public Actor				author;
	//attributes
	public Date					publishDate;
	public Float				offeredRate;
	public String				status;
	public ArrayList<String>	comments;


	@NotNull
	@Past
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getFilingDate() {
		return this.publishDate;
	}

	public void setFilingDate(final Date filingDate) {
		this.publishDate = filingDate;
	}

	@Digits(integer = 10, fraction = 2)
	public Float getOfferedRate() {
		return this.offeredRate;
	}

	public void setOfferedRate(final Float offeredRate) {
		this.offeredRate = offeredRate;
	}

	@NotBlank
	public String getStatus() {
		return this.status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}
	
	
	public ArrayList<String> getComments() {
		return this.comments;
	}

	public void setComments(final ArrayList<String> comments) {
		this.comments = comments;
	}
}
