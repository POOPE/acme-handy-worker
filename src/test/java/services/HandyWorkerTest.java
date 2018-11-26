
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.HandyWorker;
import utilities.AbstractTest;

@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class HandyWorkerTest extends AbstractTest {

	@Autowired
	private HandyWorkerService handyWorkerService;


	@Test
	public void testFindAll() {
		//testing domain service generic services
		Collection<HandyWorker> handyWorkers = this.handyWorkerService.getHandyWorkerById(1068);
		Assert.isTrue(handyWorkers.size() > 0);
		System.out.println(handyWorkers.size());
	}

}
