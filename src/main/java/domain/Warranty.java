
package domain;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Warranty extends DomainEntity {

	//relations
	public List<Law>	laws;
	//attributes
	public String		title;
	public List<String>	terms;
	public Boolean		locked;


	public Boolean getLocked() {
		return this.locked;
	}

	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

	@NotBlank
	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	@Column
	@ElementCollection(targetClass = String.class)
	public List<String> getTerms() {
		return this.terms;
	}

	public void setTerms(List<String> terms) {
		this.terms = terms;
	}

	@ManyToMany
	public List<Law> getLaws() {
		return this.laws;
	}

	public void setLaws(final List<Law> laws) {
		this.laws = laws;
	}

}
