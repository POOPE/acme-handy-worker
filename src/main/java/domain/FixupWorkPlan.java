
package domain;

import java.util.ArrayList;

import javax.persistence.Entity;

@Entity
public class FixupWorkPlan extends DomainEntity {

	public ArrayList<WorkPlanPhase>	phases;


	public ArrayList<WorkPlanPhase> getPhases() {
		return this.phases;
	}

	public void setPhases(final ArrayList<WorkPlanPhase> phases) {
		this.phases = phases;
	}

}
