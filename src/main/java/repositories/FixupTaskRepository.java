
package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.FixupTask;

@Repository
public interface FixupTaskRepository extends JpaRepository<FixupTask, Integer> {

	@Query("select f from FixupTask f where f.maximumPrice >= ?1 and f.maximumPrice <= ?2 and f.locked = FALSE")
	public List<FixupTask> findInRange(Float min, Float max);

	@Query("select f from FixupTask f where f.category.id = ?1")
	public List<FixupTask> findByCategory(int categoryId);

	@Query("select f from FixupTask f where f.locked = FALSE")
	public List<FixupTask> findApplicable();
}
