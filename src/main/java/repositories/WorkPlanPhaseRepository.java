
package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.WorkPlanPhase;

@Repository
public interface WorkPlanPhaseRepository extends JpaRepository<WorkPlanPhase, Integer> {

	@Query("select a.phases from FixupTask a where a.id = ?1")
	public List<WorkPlanPhase> findByFixupTask(int fixupTaskId);
}
