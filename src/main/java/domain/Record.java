
package domain;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Record extends DomainEntity {

	public List<String>	comments;


	@NotNull
	@ElementCollection
	public List<String> getComments() {
		return this.comments;
	}

	public void setComments(List<String> comments) {
		this.comments = comments;
	}

}
