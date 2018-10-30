
package domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;

@Entity
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

	@NotBlank
	public ArrayList<String> getTerms() {
		return this.terms;
	}

	public void setTerms(final ArrayList<String> terms) {
		this.terms = terms;
	}

	public Collection<Law> getLaws() {
		return this.laws;
	}

	public void setLaws(final Collection<Law> laws) {
		this.laws = laws;
	}

}
