
package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.FixupTask;

@Repository
public interface FixupTaskRepository extends JpaRepository<FixupTask, Integer> {

	@Query("select a from FixupTask a where a.maximumPrice >= ?1 and a.maximumPrice <= ?2 and a.locked = FALSE")
	public List<FixupTask> findInRange(Float min, Float max);

	@Query("select a from FixupTask a where a.category.id = ?1")
	public List<FixupTask> findByCategory(int categoryId);

	@Query("select a from FixupTask a where a.locked = FALSE")
	public List<FixupTask> findApplicable();
}
