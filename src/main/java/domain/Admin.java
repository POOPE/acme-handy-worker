
package domain;

import javax.persistence.Entity;

@Entity
public class Admin extends DomainEntity {

	public Actor	actor;


	public Actor getActor() {
		return this.actor;
	}

	public void setActor(final Actor actor) {
		this.actor = actor;
	}

}
