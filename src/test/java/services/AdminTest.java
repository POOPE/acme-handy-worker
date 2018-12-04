
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;

@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class AdminTest extends AbstractTest {

	@Autowired
	private AdminService adminService;


	@Test
	public void testFind() {
		super.authenticate("admin1");
		System.out.println('1');
		System.out.println(this.adminService.fixupTasksPerCustomerStats());
		System.out.println('2');
		System.out.println(this.adminService.applicationsPerFixupTaskStats());
		System.out.println('3');
		System.out.println(this.adminService.maximumPricePerFixupTaskStats());
		System.out.println('4');
		System.out.println(this.adminService.pendingApplicationsRatioStats());
		System.out.println('5');
		System.out.println(this.adminService.acceptedApplicationsRatioStats());
		System.out.println('6');
		System.out.println(this.adminService.rejectedApplicationsRatioStats());
		System.out.println('7');
		System.out.println(this.adminService.pendingLapsedApplicationRatioStats());
		System.out.println('8');
		System.out.println(this.adminService.customersWithMostApplicationsStats());
	}

}
