
package domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Warranty extends DomainEntity {

	//relations
	public Collection<Law>		laws;
	//attributes
	public String				title;
	public ArrayList<String>	terms;


	@NotBlank
	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public ArrayList<String> getTerms() {
		return this.terms;
	}

	public void setTerms(final ArrayList<String> terms) {
		this.terms = terms;
	}

	@ManyToMany
	public Collection<Law> getLaws() {
		return this.laws;
	}

	public void setLaws(final Collection<Law> laws) {
		this.laws = laws;
	}

}
