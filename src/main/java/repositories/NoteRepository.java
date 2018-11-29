
package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Admin;
import domain.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer> {
	
	@Query("select n from Note n where n.report.id = ?1")
	public List<Note> findByReport(int reportId);
}
