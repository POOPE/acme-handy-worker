
package domain;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

public class CreditCard extends DomainEntity {

	//relation
	public CreditCardMake	creditCardMake;
	//attributes
	public String			holder;
	public int				expirationMonth;
	public int				expirationYear;
	public int				number;
	public int				CCV;


	@NotNull
	public CreditCardMake getCreditCardMake() {
		return this.creditCardMake;
	}

	public void setBank(final CreditCardMake creditCardMake) {
		this.creditCardMake = creditCardMake;
	}

	@NotBlank
	public String getHolder() {
		return this.holder;
	}

	public void setHolder(final String holder) {
		this.holder = holder;
	}
	@Range(min=1, max = 12)
	public int getExpirationMonth() {
		return this.expirationMonth;
	}

	public void setExpirationMonth(final int expirationMonth) {
		this.expirationMonth = expirationMonth;
	}
	
	@Range(min=2, max=2)
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
	@Range(min=100,max=999)
	public int getCCV() {
		return this.CCV;
	}

	public void setCCV(final int cCV) {
		this.CCV = cCV;
	}

}
