
package domain;

public class Sponsor extends DomainEntity {

	public Actor	actor;


	public Actor getActor() {
		return this.actor;
	}

	public void setActor(final Actor actor) {
		this.actor = actor;
	}
}
