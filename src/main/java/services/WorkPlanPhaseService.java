
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
	@Autowired
	private FixupTaskService		fixupTaskService;


	public void delete(WorkPlanPhase phase) {
		Integer pos = phase.getPosition();
		FixupTask task = phase.getFixupTask();
		this.phaseRepo.delete(phase.getId());
		List<WorkPlanPhase> phases = this.findByFixupTask(task);
		for (WorkPlanPhase p : phases) {
			Integer ppos = p.getPosition();
			if (ppos > pos) {
				p.setPosition(ppos - 1);
				this.save(p);
			}
		}
	}

	public WorkPlanPhase create(FixupTask fixupTask) {
		WorkPlanPhase res = new WorkPlanPhase();
		res.setFixupTask(fixupTask);
		res.setPosition(this.findByFixupTask(fixupTask).size() + 1);
		return res;
	}

	public WorkPlanPhase save(WorkPlanPhase phase) {
		return this.phaseRepo.save(phase);
	}

	public WorkPlanPhase findById(int phaseId) {
		return this.phaseRepo.findOne(phaseId);
	}

	public List<WorkPlanPhase> findByFixupTask(FixupTask fixupTask) {
		return this.phaseRepo.findByFixupTask(fixupTask.getId());
	}
}
