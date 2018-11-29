
package services;

import java.util.Collection;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.SiteConfiguration;

@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class SiteConfigurationTest {

	@Autowired
	private SiteConfigurationService	siteConfigurationService;


	@Test
	public void testFind() {
		SiteConfiguration siteConfiguration = this.siteConfigurationService.find();
		Assert.notNull(siteConfiguration);
	}

	@Test
	public void testAlter() {
		SiteConfiguration siteConfig = this.siteConfigurationService.find();
		Collection<String> bad = new HashSet<>();
		bad.add("eh");
		siteConfig.setBadWords(bad);
		SiteConfiguration saved = this.siteConfigurationService.update(siteConfig);

		Assert.isTrue(saved.getBadWords().contains("eh"));
	}

}
