
package services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.LawRepository;
import domain.Law;

@Service
@Transactional
public class LawService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private LawRepository	lawRepository;


	public Law create() {
		Law l = new Law();
		return l;
	}

	public Law save(Law law) {
		return this.lawRepository.save(law);
	}

	public Law findById(int id) {
		return this.lawRepository.findOne(id);
	}

	public List<Law> findAll() {
		return this.lawRepository.findAll();
	}
}
