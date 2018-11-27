
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Actor;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer> {

	@Query("select a from Actor a where a.flagged = true")
	Collection<Actor> findAllSuspicious();

	@Query("select a from Actor a where a.email = ?1")
	Collection<Actor> findByEmail(String email);

	@Query("select a from Actor a where a.user.id = ?1")
	Actor findByUser(int userId);
}
