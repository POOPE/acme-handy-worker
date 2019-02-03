
package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Warranty;

@Repository
public interface WarrantyRepository extends JpaRepository<Warranty, Integer> {

	@Query("select f from Warranty f where f.locked = TRUE")
	public List<Warranty> findAllFinal();

}
