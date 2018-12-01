package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.CreditCardMake;

@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class CreditCardMakeTest {

	@Autowired
	private CreditCardMakeService creditCardMakeService;

	@Test
	public void testFindByName() {
		CreditCardMake creditCardMake = this.creditCardMakeService
				.findCreditCardMake("VISA").get(0);
		Assert.isTrue(creditCardMake.getName().equals("VISA"));
	}
}
