
package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

	@Query("select c from Category c where c.title = ?1")
	public Category findByName(String name);

	@Query("select c from Category c where c.parent.id = ?1")
	public List<Category> findByParent(int parentId);

	@Query("select c from Category c where c.title = 'CATEGORY'")
	public Category findRoot();
}
