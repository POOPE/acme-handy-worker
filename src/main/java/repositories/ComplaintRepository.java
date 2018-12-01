
package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Complaint;
import domain.FixupTask;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Integer> {

	@Query("select c from Complaint c where c.reference.id = ?1")
	public List<Complaint> findByFixupTask(int fixupTaskId);

	@Query("select c from Complaint c where c.ticker = ?1")
	public Complaint findByTicker(String ticker);

	@Query("select a.fixupTask from FixupApplication a where a.author.id = ?1 and a.fixupTask.locked = TRUE")
	public List<FixupTask> findTasksByHandyWorker(int id);
}
