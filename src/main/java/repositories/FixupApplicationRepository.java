
package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.FixupApplication;

@Repository
public interface FixupApplicationRepository extends JpaRepository<FixupApplication, Integer> {

	@Query("select f from FixupApplication f where f.fixupTask.id = ?1")
	public List<FixupApplication> findByTask(int fixupTaskId);

	@Query("select f from FixupApplication f where f.status = ?1 and f.fixupTask.id=?2")
	public List<FixupApplication> findByStatusForTask(String status, int fixupTaskId);

	@Query("select f from FixupApplication f where f.author.id = ?1")
	public List<FixupApplication> findByAuthor(int handyWorkerId);
}
