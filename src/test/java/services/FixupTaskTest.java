
package services;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.FixupTask;

@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class FixupTaskTest extends AbstractTest {

	@Autowired
	private FixupTaskService	fts;

	@Autowired
	private CategoryService		cats;


	@Test
	public void testFindApplicable() {
		List<FixupTask> all = this.fts.findApplicable();
		Assert.isTrue(all.size() > 0);
	}

	@Test
	public void testFindInRange() {
		List<FixupTask> range = this.fts.findByPriceRange(30f, 50f);
		Assert.isTrue(range.size() > 0);
	}

	@Test
	public void testCreate() {
		super.authenticate("customer1");

		FixupTask f = this.fts.create();
		f.setCategory(this.cats.findRoot());
		f.setDescription("test");
		f.setAddress("test address");
		f.setMaximumPrice(40f);
		FixupTask saved = this.fts.initialize(f);

		List<FixupTask> all = this.fts.findApplicable();
		Assert.isTrue(all.contains(saved));
	}
}
