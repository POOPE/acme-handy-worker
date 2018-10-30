
package domain;

import javax.persistence.Entity;

@Entity
public class Customer extends DomainEntity {

	public Actor	actor;
	public Float	score;


	public Actor getActor() {
		return this.actor;
	}

	public void setActor(final Actor actor) {
		this.actor = actor;
	}

	public Float getScore() {
		return this.score;
	}

	public void setScore(final Float score) {
		this.score = score;
	}

}
