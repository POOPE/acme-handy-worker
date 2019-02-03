
package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Lorem;

@Repository
public interface LoremRepository extends JpaRepository<Lorem, Integer> {

	@Query("select a from Lorem a where a.lock = TRUE")
	List<Lorem> findAllFinal();

	@Query("select a from Lorem a where a.author.id = ?1")
	List<Lorem> findByAuthor(int id);

	@Query("select a from Lorem a where a.fixuptask.id = ?1 and a.lock= TRUE")
	List<Lorem> findByFixupTask(int id);

	@Query("select count(a)*1.0/ (select count(b) *1.0 from Lorem b ) from Lorem a where a.lock=TRUE ")
	public Float publishedLoremRatio();

	@Query("select count(a)*1.0/ (select count(b) *1.0 from Lorem b ) from Lorem a where a.lock=FALSE ")
	public Float draftLoremRatio();
}
