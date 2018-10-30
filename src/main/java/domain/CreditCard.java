
package domain;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class CreditCard extends DomainEntity {

	//relation
	public CreditCardMake	bank;
	//attributes
	public String			holder;
	public int				expirationMonth;
	public int				expirationYear;
	public int				number;
	public int				CCV;


	@NotNull
	@Valid
	public CreditCardMake getBank() {
		return this.bank;
	}

	public void setBank(final CreditCardMake bank) {
		this.bank = bank;
	}

	@NotBlank
	public String getHolder() {
		return this.holder;
	}

	public void setHolder(final String holder) {
		this.holder = holder;
	}

	public int getExpirationMonth() {
		return this.expirationMonth;
	}

	public void setExpirationMonth(final int expirationMonth) {
		this.expirationMonth = expirationMonth;
	}

	public int getExpirationYear() {
		return this.expirationYear;
	}

	public void setExpirationYear(final int expirationYear) {
		this.expirationYear = expirationYear;
	}

	public int getNumber() {
		return this.number;
	}

	public void setNumber(final int number) {
		this.number = number;
	}

	public int getCCV() {
		return this.CCV;
	}

	public void setCCV(final int cCV) {
		this.CCV = cCV;
	}

}
