
package jsonTests;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import security.Authority;
import security.UserAccount;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import domain.Actor;
import domain.Message;
import domain.MessageBox;

public class JsonSerializeTest {

	public static void main(final String[] args) {
		// TODO Auto-generated method stub

		final UserAccount user1 = new UserAccount();
		final Collection<Authority> userAuthorities = new HashSet<Authority>();
		final Authority auth = new Authority();
		auth.setAuthority("CUSTOMER");
		userAuthorities.add(auth);
		user1.setAuthorities(userAuthorities);
		user1.setUsername("antozalez");
		user1.setPassword("Not-A-Hashed-Password");

		final Actor customer1 = new Actor();
		customer1.setName("Antonio");
		customer1.setSurname("González");
		customer1.setEmail("antoniogonzalez@test.com");
		customer1.setPhoneNumber("+34 636363636");
		customer1.setUser(user1);

		final MessageBox messageBox1 = new MessageBox();
		messageBox1.setCategory("INBOX");
		messageBox1.setName("INBOX");
		messageBox1.setOwner(customer1);

		final Message message1 = new Message();
		message1.setDeliveryDate(new Date());
		message1.setMessage("Buenas. Tengo una bici rota.");
		message1.setSender(customer1);

		final Collection<MessageBox> messageContainers = new HashSet<MessageBox>();
		messageContainers.add(messageBox1);

		message1.setContainer(messageContainers);

		String jsonResult = "";
		final ObjectMapper mapper = new ObjectMapper();
		try {
			jsonResult = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(message1);
		} catch (final JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(jsonResult);
	}
}
