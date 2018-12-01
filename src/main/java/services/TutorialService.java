package services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.TutorialRepository;
import domain.Section;
import domain.Tutorial;

@Service
@Transactional
public class TutorialService {
	@Autowired
	private TutorialRepository tutorialRepository;

	public List<Tutorial> findByTitle(String title) {
		return this.tutorialRepository.findByTitle(title);
	}

	public Tutorial findById(int id) {
		return this.tutorialRepository.findById(id);
	}

	// CRUD
	public Tutorial create() {
		Tutorial tutorial = new Tutorial();
		tutorial.setPhotos(new ArrayList<String>());
		tutorial.setSections(new ArrayList<Section>());
		return tutorial;
	}

	public Tutorial save(Tutorial tutorial) {
		return this.tutorialRepository.save(tutorial);
	}

	public List<Tutorial> findAll() {
		return this.tutorialRepository.findAll();
	}

}
