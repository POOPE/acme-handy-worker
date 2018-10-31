
package jsonTests;

import java.io.IOException;

import utilities.internal.SchemaPrinter;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import domain.Message;

public class JsonDeserializeTest {

	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		final String inputJson = "{\"id\" : 0,\"version\" : 0,\"sender\" : {\"id\" : 0,\"version\" : 0,\"user\" : {\"id\" : 0,\"version\" : 0,\"username\" : \"antozalez\",\"password\" : \"Not-A-Hashed-Password\", \"authorities\" : [ {\"authority\" : \"CUSTOMER\"} ]}, \"name\" : \"Antonio\", \"middleName\" : null, \"surname\" : \"González\",\"photo\" : null,\"email\" : \"antoniogonzalez@test.com\", \"phoneNumber\" : \"+34 636363636\",\"address\" : null, \"flagged\" : false},\"recipients\" : null,\"container\" : [ {\"id\" : 0, \"version\" : 0, \"owner\" : { \"id\" : 0, \"version\" : 0, \"user\" : { \"id\" : 0, \"version\" : 0,\"username\" : \"antozalez\", \"password\" : \"Not-A-Hashed-Password\", \"authorities\" : [ {\"authority\" : \"CUSTOMER\"} ]},\"name\" : \"Antonio\",\"middleName\" : null,\"surname\" : \"González\", \"photo\" : null,\"email\" : \"antoniogonzalez@test.com\",\"phoneNumber\" : \"+34 636363636\",\"address\" : null,\"flagged\" : false},\"name\" : \"INBOX\",\"messages\" : null,\"category\" : \"INBOX\"} ],\"subject\" : null,\"deliveryDate\" : 1540964345199,\"message\" : \"Buenas. Tengo una bici rota.\",\"priority\" : null,\"read\" : null}";
		Message message1 = null;

		try {
			message1 = new ObjectMapper().readValue(inputJson, Message.class);
		} catch (final JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (final JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		SchemaPrinter.print(message1);
	}
}
