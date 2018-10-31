
package domain;

import org.hibernate.validator.constraints.NotBlank;

public class CreditCardMake extends DomainEntity {

	public String	name;

	@NotBlank
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

}
