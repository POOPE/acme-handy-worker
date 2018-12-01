
package repositories;

import java.util.Date;
import java.util.HashSet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Finder;
import domain.FixupTask;

@Repository
public interface FinderRepository extends JpaRepository<Finder, Integer> {

	@Query("select a from Finder a where a.handyWorker.id = ?1")
	public Finder findByOwnerId(Integer ownerId);

	@Query("select a from FixupTask a where (a.ticker like %?1% OR a.description like %?1% OR a.address like %?1%) AND a.category.title = ?2 AND a.warranty.title like %?3% AND a.maximumPrice >= ?4 AND a.maximumPrice <= ?5 AND a.startDate >= ?6 AND a.endDate <=?7 ")
	public HashSet<FixupTask> doSearch(String keyWord, String category, String warranty, Double minPrice, Double maxPrice, Date minDate, Date maxDate);
}
