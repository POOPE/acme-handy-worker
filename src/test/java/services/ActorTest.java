
package services;

import java.util.Collection;
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

@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ActorTest extends AbstractTest {

	@Autowired
	private ActorService	actorService;


	@Test
	public void testCreate() {
		Actor actor, saved;
		List<Actor> actors;

		actor = this.actorService.create();
		actor.setEmail("testmail@acme.com");
		actor.setName("test");
		actor.setSurname("bot");
		saved = this.actorService.initialize(actor);

		actors = this.actorService.findAll();
		Assert.isTrue(actors.contains(saved));
	}

	@Test
	public void testFindAll() {
		//testing domain service generic services
		List<Actor> actors = this.actorService.findAll();
		Assert.isTrue(actors.size() > 0);
	}

	@Test
	public void testFindAllSuspicious() {
		Collection<Actor> actors = this.actorService.findAllSuspicious();
		Assert.isTrue(actors.size() > 0);
	}

	@Test
	public void testFindByEmail() {
		Actor actor = this.actorService.findByEmail("customer1@gmail.com");
		Assert.isTrue(actor.getEmail().equals("customer1@gmail.com"));
	}

}
