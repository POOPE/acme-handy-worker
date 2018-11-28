package services;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.Tutorial;

@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TutorialTest {
	@Autowired
	private TutorialService tutorialService;
	@Autowired
	private HandyWorkerService handyWorkerService;

	@Test
	public void testFindByTitle() {
		Tutorial tutorial = this.tutorialService.findByTitle(
				"tutorial para reparar bicis").get(0);
		Assert.isTrue(tutorial.getTitle().equals("tutorial para reparar bicis"));
	}

	@Test
	public void TestFindById() {
		Tutorial tutorial = this.tutorialService.findById(1150);
		Assert.isTrue(tutorial.getId() == 1150);
	}

	@Test
	public void TestCreate() {
		Tutorial tutorial, aux;
		List<Tutorial> tutorials;

		tutorial = this.tutorialService.create();
		tutorial.setAuthor(this.handyWorkerService.findAll().get(0));
		tutorial.setTitle("tutorial para reparar bicis");
		tutorial.setDescription("tutorial para identificar y resolver problemas con bicis");
		aux = this.tutorialService.save(tutorial);
		tutorials = this.tutorialService.findAll();
		Assert.isTrue(tutorials.contains(aux));
	}

	@Test
	public void testFindAll() {
		// testing domain service generic services
		List<Tutorial> tutorials = this.tutorialService.findAll();
		Assert.isTrue(tutorials.size() > 0);
	}
}
