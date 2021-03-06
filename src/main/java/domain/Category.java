
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Category extends DomainEntity {

	//relations
	public Category	parent;
	//attributes
	public String	title;


	@ManyToOne(optional = true)
	public Category getParent() {
		return this.parent;
	}

	public void setParent(final Category parent) {
		this.parent = parent;
	}

	@NotBlank
	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

}
