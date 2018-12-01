
package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

	@Query("select count(a.id) from FixupTask a group by a.author")
	public List<Long> fixupTasksPerCustomer();

	@Query("select count(a.id) from FixupApplication a group by a.fixupTask")
	public List<Long> applicationsPerFixupTask();

	@Query("select avg(a.maximumPrice) from FixupTask a ")
	public Double avgMaximumPricePerFixupTaskStats();

	@Query("select min(a.maximumPrice) from FixupTask a ")
	public Double minMaximumPricePerFixupTaskStats();

	@Query("select max(a.maximumPrice) from FixupTask a ")
	public Double maxMaximumPricePerFixupTaskStats();

	@Query("select sqrt(((sum(a.maximumPrice * a.maximumPrice)/count(a.maximumPrice))-avg(a.maximumPrice)) *avg(a.maximumPrice)) from FixupTask a ")
	public Double devMaximumPricePerFixupTaskStats();

	@Query("select count(a)*1.0/ (select count(b) *1.0 from FixupApplication b ) from FixupApplication a where a.status='PENDING' ")
	public Double pendingApplicationsRatio();

	@Query("select count(a)*1.0/ (select count(b) *1.0 from FixupApplication b ) from FixupApplication a where a.status='ACCEPTED' ")
	public Double acceptedApplicationsRatio();

	@Query("select count(a)*1.0/ (select count(b) *1.0 from FixupApplication b ) from FixupApplication a where a.status='REJECTED' ")
	public Double rejectedApplicationsRatio();

	@Query("select count (a)*1.0/ (select count(b) *1.0 from FixupApplication b ) from FixupApplication a where a.status='PENDING' AND a.fixupTask.endDate < current_timestamp() ")
	public Double pendingLapsedApplicationsRatio();

	@Query("select c.author.id from FixupTask c group by c.author order by count(c.id) desc ")
	public List<Integer> getCustomerMostFixupTasks();

}
