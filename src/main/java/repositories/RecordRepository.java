
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Record;

@Repository
public interface RecordRepository extends JpaRepository<Record, Integer> {

	@Query("select c.records from Curriculum c where c.id = ?1")
	Collection<Record> findByCurriculumId(int curriculumId);

}
