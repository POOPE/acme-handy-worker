
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.Sponsorship;
import utilities.AbstractTest;

@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class SponsorshipTest extends AbstractTest {

	@Autowired
	private SponsorshipService sponsorshipService;


	@Test
	public void testFindAll() {
		//testing domain service generic services
		Collection<Sponsorship> sponsors = this.sponsorshipService.findAll();
		Assert.isTrue(sponsors.size() > 0);
		System.out.println(sponsors.size());
	}

}
