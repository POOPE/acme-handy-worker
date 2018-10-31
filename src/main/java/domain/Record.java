
package domain;

import java.util.Collection;

public class Record extends DomainEntity {

	public Collection<String>	comments;


	public Collection<String> getComments() {
		return this.comments;
	}

	public void setComments(final Collection<String> comments) {
		this.comments = comments;
	}

}
