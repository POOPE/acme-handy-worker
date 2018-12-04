
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.Actor;
import domain.Endorsement;
import domain.HandyWorker;
import utilities.AbstractTest;

@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class EndorsementTest extends AbstractTest {

	@Autowired
	private EndorsementService	endService;

	@Autowired
	private ActorService		actorService;

	@Autowired
	private AdminService		adminService;

	@Autowired
	private HandyWorkerService	handyWorkerService;


	@Test
	public void testCompute() {
		super.authenticate("customer1");

		Endorsement e = this.endService.create();
		List<String> comments = new ArrayList<>();
		comments.add("chapuza");
		comments.add("chapuza");
		comments.add("chapuza");
		e.setComments(comments);
		Actor referenced = this.actorService.findByEmail("worker1@gmail.com");
		Assert.isTrue(referenced != null, "Could not find worker");
		e.setReference(referenced);

		Endorsement saved = this.endService.publish(e);

		List<Endorsement> all = this.endService.findAll();
		Assert.isTrue(all.contains(saved), "Endorsement not properly saved");
		super.authenticate(null);

		super.authenticate("admin1");
		this.adminService.UpdateScores();
		Collection<HandyWorker> k = this.handyWorkerService.findAll();

		HandyWorker j = this.handyWorkerService.findOne(1079);
		float h = this.handyWorkerService.findOne(1079).getScore();
		System.out.println(h);
	}
}
