
package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DomainService {

	@Autowired
	private JpaRepository<?, Integer>	repo;


	public Object findById(int id) {
		return this.repo.findOne(id);
	}

	public List<?> findAll() {
		return this.repo.findAll();
	}
}
