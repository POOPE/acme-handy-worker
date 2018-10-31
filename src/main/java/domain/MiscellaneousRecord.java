
package domain;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

public class MiscellaneousRecord extends Record {

	public String	title;
	public String	attachmentUrl;


	@NotBlank
	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}
	@URL
	public String getAttachmentUrl() {
		return this.attachmentUrl;
	}

	public void setAttachmentUrl(final String attachmentUrl) {
		this.attachmentUrl = attachmentUrl;
	}

}
