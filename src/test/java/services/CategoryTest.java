
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
import domain.Category;

@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class CategoryTest extends AbstractTest {

	@Autowired
	private CategoryService	cs;


	@Test
	public void testCreate() {
		Category c = this.cs.create();
		c.setTitle("TEST");

		Category saved = this.cs.save(c);

		List<Category> all = this.cs.findAll();
		Category cat = this.cs.findByName("TEST");

		Assert.isTrue(all.contains(cat));
	}
}
