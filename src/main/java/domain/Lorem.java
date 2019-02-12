
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Lorem extends DomainEntity {

	private FixupTask	fixupTask;
	private Customer	author;
	private String		ticker;
	private String		body;
	private String		imgURL;
	private Date		publishDate;
	private Boolean		locked;


	@ManyToOne(optional = false)
	public FixupTask getFixupTask() {
		return this.fixupTask;
	}

	public void setFixupTask(FixupTask fixupTask) {
		this.fixupTask = fixupTask;
	}

	@ManyToOne(optional = false)
	public Customer getAuthor() {
		return this.author;
	}

	public void setAuthor(Customer author) {
		this.author = author;
	}

	@NotBlank
	public String getTicker() {
		return this.ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	@NotBlank
	@Size(min = 1, max = 250)
	public String getBody() {
		return this.body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@NotBlank
	@URL
	public String getImgURL() {
		return this.imgURL;
	}

	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}

	@Past
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getPublishDate() {
		return this.publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	@NotNull
	public Boolean getLocked() {
		return this.locked;
	}

	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

}
