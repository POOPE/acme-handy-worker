
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import security.Authority;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Integer> {

	@Query("select a from Authority a where a.authority = ?1")
	public Authority findByName(String name);

}
