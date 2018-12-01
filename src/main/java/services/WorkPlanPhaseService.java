
package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.WorkPlanPhaseRepository;
import domain.FixupTask;
import domain.WorkPlanPhase;

@Service
@Transactional
public class WorkPlanPhaseService {

	@Autowired
	private WorkPlanPhaseRepository	phaseRepo;


	public WorkPlanPhase create() {
		WorkPlanPhase res = new WorkPlanPhase();
		return res;
	}

	public WorkPlanPhase save(WorkPlanPhase phase) {
		return this.save(phase);
	}

	public WorkPlanPhase findById(int phaseId) {
		return this.phaseRepo.findOne(phaseId);
	}

	public List<WorkPlanPhase> findByFixupTask(FixupTask fixupTask) {
		return this.phaseRepo.findByFixupTask(fixupTask.getId());
	}
}
