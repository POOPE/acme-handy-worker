
package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {

	@Query("select r from Report r where r.complaint.id = ?1 and r.locked = TRUE")
	public List<Report> findByComplaint(int complaintId);

	@Query("select r from Report r where r.author.id = ?1")
	public List<Report> findByAuthor(int authorId);

}
