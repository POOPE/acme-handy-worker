
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.Curriculum;
import utilities.AbstractTest;

@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class CurriculumTest extends AbstractTest {

	@Autowired
	private CurriculumService curriculumService;


	@Test
	public void testCreate() {
		//testing domain service generic services

		super.authenticate("handyworker1");
		Curriculum curriculum = this.curriculumService.create();
		//Assert.isTrue(handyWorker != null);
		curriculum.setFullName("Alberto José");
		Curriculum saved = this.curriculumService.save(curriculum);
		Assert.isTrue(saved.getId() != 0);

	}

}
