
package services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.Actor;
import domain.Message;
import domain.MessageBox;
import utilities.AbstractTest;

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
	public void testSend() {
		Message m;
		List<Message> all;

		super.authenticate("admin1");
		Actor actor = this.as.findPrincipal();
		m = this.ms.create();
		Actor recipient = this.as.findByEmail("admin2@gmail.com");
		List<Actor> recipients = new ArrayList<>();
		recipients.add(recipient);
		m.setRecipients(recipients);

		Message sent = this.ms.send(m);

		Assert.isTrue(sent.getContainer().contains(this.mbs.findByCategory(recipient, "INBOX")));
		Assert.isTrue(sent.getContainer().contains(this.mbs.findByCategory(actor, "OUTBOX")));
	}

	@Test
	public void testMove() {
		Message m;
		List<Message> all;

		super.authenticate("admin1");

		Actor actor = this.as.findPrincipal();
		m = this.ms.create();
		Actor recipient = this.as.findByEmail("admin2@gmail.com");
		List<Actor> recipients = new ArrayList<>();
		recipients.add(recipient);
		m.setRecipients(recipients);

		Message sent = this.ms.send(m);

		MessageBox from = this.mbs.findByCategory("OUTBOX");
		MessageBox to = this.mbs.findByCategory("TRASHBOX");

		this.ms.move(sent, from, to);

		Assert.isTrue(sent.getContainer().contains(this.mbs.findByCategory("TRASHBOX")));
	}

	@Test
	public void testDelete() {
		Message m;
		List<Message> all;

		super.authenticate("admin1");

		m = this.ms.create();
		Actor recipient = this.as.findByEmail("admin2@gmail.com");
		List<Actor> recipients = new ArrayList<>();
		recipients.add(recipient);
		m.setRecipients(recipients);

		Message sent = this.ms.send(m);

		MessageBox box = this.mbs.findByCategory("OUTBOX");
		MessageBox inb = this.mbs.findByCategory(recipient, "INBOX");

		this.ms.remove(sent, box);

		Assert.isTrue(!sent.getContainer().contains(box));
		Assert.isTrue(sent.getContainer().contains(inb));
	}
}
