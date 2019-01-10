
package services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.Category;
import repositories.CategoryRepository;

@Service
@Transactional
public class CategoryService {

	@Autowired
	CategoryRepository categoryRepository;


	public List<Category> findAll() {
		return this.categoryRepository.findAll();
	}

	public Category findById(Integer id) {
		return this.categoryRepository.findOne(id);
	}

	public Category findByName(String name) {
		return this.categoryRepository.findByName(name);
	}

	public Category create() {
		Category c = new Category();
		c.setParent(this.categoryRepository.findRoot());
		return c;
	}

	public Category save(Category category) {
		return this.categoryRepository.save(category);
	}

	public Category findRoot() {
		return this.categoryRepository.findRoot();
	}

	public void delete(Category category) {
		this.categoryRepository.delete(category);
	}

}
