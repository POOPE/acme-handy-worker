
package domain;

import javax.persistence.Entity;

@Entity
public class Customer extends Actor {

	public Float	score;


	public Float getScore() {
		return this.score;
	}

	public void setScore(final Float score) {
		this.score = score;
	}

}
