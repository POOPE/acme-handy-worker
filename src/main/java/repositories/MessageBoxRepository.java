
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.MessageBox;

@Repository
public interface MessageBoxRepository extends JpaRepository<MessageBox, Integer> {

	@Query("select a from MessageBox a where a.owner.id = ?1")
	Collection<MessageBox> findByActor(int actorId);

	@Query("select a from MessageBox a where a.owner.id = ?1 and a.category = ?2")
	Collection<MessageBox> findByCategory(int actorId, String category);

	@Query("select a from MessageBox a where a.parent.id = ?1")
	Collection<MessageBox> findByParent(int parentId);
}
