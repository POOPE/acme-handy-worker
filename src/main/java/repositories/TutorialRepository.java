package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Tutorial;

@Repository
public interface TutorialRepository extends JpaRepository<Tutorial, Integer> {

	@Query("select a from Tutorial a where a.title = ?1")
	List<Tutorial> findByTitle(String title);

	@Query("select a from Tutorial a where a.id = ?1")
	Tutorial findById(int id);
}
