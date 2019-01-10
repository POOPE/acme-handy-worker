
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

@Entity
@Access(AccessType.PROPERTY)
public class HandyWorker extends Actor {

	public String	make;
	public Float	score;


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
