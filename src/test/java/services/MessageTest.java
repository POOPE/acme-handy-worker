
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
import domain.Actor;
import domain.MessageBox;

@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class MessageTest extends AbstractTest {

	@Autowired
	private MessageBoxService	mbs;

	@Autowired
	private MessageService		ms;

	@Autowired
	private ActorService		as;


	@Test
	public void testCreateDefault() {
		List<MessageBox> sys, all;

		super.authenticate("admin1");
		Actor actor = this.as.findPrincipal();
		sys = this.mbs.createDefaultBoxes(actor);
		all = this.mbs.findAllByActor(actor);

		Assert.isTrue(all.containsAll(sys));
	}

}
