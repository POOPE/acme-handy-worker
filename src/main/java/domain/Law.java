
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Law extends DomainEntity {

	public String	title;
	public String	relevantText;


	@NotBlank
	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	@NotBlank
	public String getRelevantText() {
		return this.relevantText;
	}

	public void setRelevantText(final String relevantText) {
		this.relevantText = relevantText;
	}

}
