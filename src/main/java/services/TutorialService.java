
package services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.TutorialRepository;
import domain.Section;
import domain.Sponsorship;
import domain.Tutorial;

@Service
@Transactional
public class TutorialService {

	@Autowired
	private TutorialRepository	tutorialRepository;

	@Autowired
	private HandyWorkerService	handyWorkerService;

	@Autowired
	private SponsorService		sponsorService;


	public List<Tutorial> findByTitle(String title) {
		return this.tutorialRepository.findByTitle(title);
	}

	public Tutorial findById(int id) {
		return this.tutorialRepository.findById(id);
	}

	// CRUD
	public Tutorial create() {
		//findPrincipal checks authority
		Assert.isTrue(this.handyWorkerService.findPrincipal() != null, "Error on create: must be a handy worker");

		Tutorial tutorial = new Tutorial();
		tutorial.setPhotos(new ArrayList<String>());
		tutorial.setSections(new ArrayList<Section>());
		return tutorial;
	}

	public Tutorial initialize(Tutorial tutorial) {
		tutorial.setAuthor(this.handyWorkerService.findPrincipal());
		return this.save(tutorial);
	}

	public Tutorial save(Tutorial tutorial) {
		Assert.isTrue(tutorial.getAuthor().equals(this.handyWorkerService.findPrincipal()), "Error on save: owner inconsistency");
		tutorial.setLastUpdate(new Date());
		return this.tutorialRepository.save(tutorial);
	}

	public List<Tutorial> findAll() {
		return this.tutorialRepository.findAll();
	}

	public Tutorial sponsor(Tutorial tutorial, Sponsorship sponsorship) {
		Assert.isTrue(this.sponsorService.findPrincipal() != null, "Error on sponsor: must be a sponsor");
		List<Sponsorship> sponsorList = tutorial.getSponsorship();
		sponsorList.add(sponsorship);
		tutorial.setSponsorship(sponsorList);
		return this.save(tutorial);
	}

	public Tutorial addSection(Tutorial tutorial, Section section) {
		Assert.isTrue(tutorial.getAuthor().equals(this.handyWorkerService.findPrincipal()), "Error on save: owner inconsistency");
		List<Section> sections = tutorial.getSections();
		sections.add(section);
		return this.save(tutorial);
	}
}
