package services;

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

	@Test
	public void testFindByTitle() {
		Tutorial tutorial = this.tutorialService.findByTitle(
				"tutorial para reparar bicis").get(0);
		Assert.isTrue(tutorial.getTitle().equals("tutorial para reparar bicis"));
	}

	@Test
	public void TestFindById() {
		Tutorial tutorial = this.tutorialService.findById(1131);
		Assert.isTrue(tutorial.getId() == 1131);
	}

}
