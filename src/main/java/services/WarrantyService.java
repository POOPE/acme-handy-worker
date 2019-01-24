
package services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.WarrantyRepository;
import domain.Law;
import domain.Warranty;

@Service
@Transactional
public class WarrantyService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private WarrantyRepository	warrantyRepository;


	public Warranty create() {
		Warranty w = new Warranty();
		w.setLaws(new ArrayList<Law>());
		return w;
	}

	public Warranty save(Warranty w) {
		return this.warrantyRepository.save(w);
	}

	public Warranty findById(int id) {
		return this.warrantyRepository.findOne(id);
	}

	public List<Warranty> findAll() {
		return this.warrantyRepository.findAll();
	}

	public void delete(Warranty w) {

		this.warrantyRepository.delete(w.getId());
	}

	public Warranty initialize(Warranty warranty) {
		warranty.setLocked(false);
		return this.save(warranty);
	}

	public List<Warranty> findAllFinal() {
		return this.warrantyRepository.findAllFinal();
	}

}
