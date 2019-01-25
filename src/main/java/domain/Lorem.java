
package domain;

import java.util.Date;

import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

public class Lorem {

	private FixupTask	fixupTask;
	private Customer	author;
	private String		ticker;
	private Date		publishDate;
	private String		body;
	private String		imgURL;
	private boolean		lock;


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

	@NotNull
	@Past
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getPublishDate() {
		return this.publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	@NotBlank
	public String getBody() {
		return this.body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getImgURL() {
		return this.imgURL;
	}

	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}

	public boolean isLock() {
		return this.lock;
	}

	public void setLock(boolean lock) {
		this.lock = lock;
	}

}
