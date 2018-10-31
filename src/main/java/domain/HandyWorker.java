
package domain;

import org.hibernate.validator.constraints.NotBlank;

public class HandyWorker extends Actor {

	public String	make;
	public Float	score;


	@NotBlank
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
