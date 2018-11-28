
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.WorkPlanPhase;

@Repository
public interface WorkPlanPhaseRepository extends JpaRepository<WorkPlanPhase, Integer> {

}
