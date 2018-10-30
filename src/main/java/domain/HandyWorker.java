
package domain;

import javax.persistence.Entity;

@Entity
public class HandyWorker extends DomainEntity {

	public Actor	actor;
	public String	make;
	public Float	score;


	public Actor getActor() {
		return this.actor;
	}

	public void setActor(final Actor actor) {
		this.actor = actor;
	}

	public String getMake() {
		return this.make;
	}

	public void setMake(final String make) {
		this.make = make;
	}

	public Float getScore() {
		return this.score;
	}

	public void setScore(final Float score) {
		this.score = score;
	}

}
