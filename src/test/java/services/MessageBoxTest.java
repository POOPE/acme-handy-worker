
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
public class MessageBoxTest extends AbstractTest {

	@Autowired
	private MessageBoxService	mbs;

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

	@Test
	public void testCreateBox() {
		MessageBox box, saved;
		List<MessageBox> all;

		super.authenticate("admin1");

		box = this.mbs.create();
		box.setName("testbox");
		saved = this.mbs.save(box);

		all = this.mbs.findByPrincipal();

		Assert.isTrue(all.contains(saved));
		super.authenticate(null);
	}

	@Test
	public void testCreateBoxwParent() {
		MessageBox box, saved, parent;
		List<MessageBox> all;

		super.authenticate("admin1");

		parent = this.mbs.findByPrincipal().get(0);
		box = this.mbs.create();
		box.setName("testbox");
		box.setParent(parent);
		saved = this.mbs.save(box);

		all = this.mbs.findByPrincipal();

		Assert.isTrue(all.contains(saved));
		Assert.isTrue(saved.getParent().equals(parent));
		super.authenticate(null);
	}

	@Test
	public void testDeleteAll() {
		MessageBox box1, box2, saved1, saved2, parent;
		List<MessageBox> all;

		super.authenticate("admin1");

		parent = this.mbs.findByPrincipal().get(0);
		box1 = this.mbs.create();
		box1.setName("testbox1");
		box1.setParent(parent);
		saved1 = this.mbs.save(box1);

		box2 = this.mbs.create();
		box2.setName("testbox2");
		box2.setParent(saved1);
		saved2 = this.mbs.save(box2);

		this.mbs.deleteAll(saved1);

		all = this.mbs.findByPrincipal();

		Assert.isTrue(!all.contains(saved1));
		Assert.isTrue(!all.contains(saved2));
		super.authenticate(null);
	}

	@Test
	public void testMoveMessageBox() {
		MessageBox box1, box2, box3, saved1, saved2, saved3, parent;
		List<MessageBox> all;

		super.authenticate("admin1");

		parent = this.mbs.findByPrincipal().get(0);
		box1 = this.mbs.create();
		box1.setName("testbox1");
		box1.setParent(parent);
		saved1 = this.mbs.save(box1);

		box2 = this.mbs.create();
		box2.setName("testbox2");
		box2.setParent(saved1);
		saved2 = this.mbs.save(box2);

		box3 = this.mbs.create();
		box3.setName("testbox2");
		saved3 = this.mbs.save(box3);

		this.mbs.move(saved3, saved2);

		all = this.mbs.findByPrincipal();

		Assert.isTrue(all.contains(saved3));
		Assert.isTrue(all.contains(saved2));
		Assert.isTrue(saved3.getParent().equals(saved2));
		super.authenticate(null);
	}

}
