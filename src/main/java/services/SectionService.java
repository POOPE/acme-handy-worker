
package services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SectionRepository;
import domain.Section;
import domain.Tutorial;

@Service
@Transactional
public class SectionService {

	@Autowired
	private SectionRepository	sectionRepo;

	@Autowired
	private TutorialService		tutorialService;

	@Autowired
	private HandyWorkerService	handyWorkerService;


	public Section findById(int id) {
		return this.sectionRepo.findOne(id);
	}

	public List<Section> findByTutorial(Tutorial tutorial) {
		//TODO: order list by section.position
		return this.sectionRepo.findByTutotial(tutorial.getId());
	}

	public Section create() {
		Assert.isTrue(this.handyWorkerService.findPrincipal() != null, "Error on create: must be a handy worker");
		Section s = new Section();
		s.setPhotos(new ArrayList<String>());
		return s;
	}

	public Section save(Section section) {
		return this.save(section);
	}

}
