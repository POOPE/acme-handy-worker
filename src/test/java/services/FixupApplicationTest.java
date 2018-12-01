
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

import utilities.AbstractTest;
import domain.FixupApplication;
import domain.FixupTask;

@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class FixupApplicationTest extends AbstractTest {

	@Autowired
	private FixupApplicationService	fixupApplicationService;

	@Autowired
	private FixupTaskService		fixupTaskService;


	@Test
	public void testReject() {
		super.authenticate("customer1");

		List<FixupTask> tasks = this.fixupTaskService.findByPrincipal();
		List<FixupApplication> applications = new ArrayList<>();
		FixupTask t = new FixupTask();
		for (FixupTask task : tasks) {
			applications = this.fixupApplicationService.findByTask(task);
			t = task;
			if (!applications.isEmpty()) {
				break;
			}
		}
		FixupApplication application = applications.get(0);

		Assert.isTrue(applications.size() > 0, "No applications found");

		FixupApplication saved = this.fixupApplicationService.reject(application);
		List<FixupApplication> allRejected = this.fixupApplicationService.findByStatusForTask("REJECTED", t);

		Assert.isTrue(allRejected.contains(saved), "Did not reject");
	}

}
