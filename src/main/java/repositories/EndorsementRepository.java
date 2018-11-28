
package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Endorsement;

@Repository
public interface EndorsementRepository extends JpaRepository<Endorsement, Integer> {

	@Query("select e from Endorsement e where e.reference.id = ?1")
	public List<Endorsement> findByReferencedActor(int actorId);

	@Query("select e from Endorsement e where e.author.id = ?1")
	public List<Endorsement> findByAuthor(int actorId);
}
