package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Digits;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

@Entity
@Access(AccessType.PROPERTY)
public class CreditCard extends DomainEntity {

	//relation
	public Actor			owner;
	public CreditCardMake	creditCardMake;
	//attributes
	public String			holder;
	public int				expirationMonth;
	public int				expirationYear;
	public String			number;
	public int				CCV;


	@OneToOne
	public Actor getOwner() {
		return this.owner;
	}

	public void setOwner(Actor owner) {
		this.owner = owner;
	}

	@ManyToOne(optional = true)
	public CreditCardMake getCreditCardMake() {
		return this.creditCardMake;
	}

	public void setCreditCardMake(final CreditCardMake creditCardMake) {
		this.creditCardMake = creditCardMake;
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

	@Range(min = 1, max = 12)
	public int getExpirationMonth() {
		return this.expirationMonth;
	}

	public void setExpirationMonth(final int expirationMonth) {
		this.expirationMonth = expirationMonth;
	}

	@Digits(integer = 2, fraction = 0)
	public int getExpirationYear() {
		return this.expirationYear;
	}

	public void setExpirationYear(final int expirationYear) {
		this.expirationYear = expirationYear;
	}

	@NotBlank
	public String getNumber() {
		return this.number;
	}

	public void setNumber(final String number) {
		this.number = number;
	}

	@Range(min = 100, max = 999)
	public int getCCV() {
		return this.CCV;
	}

	public void setCCV(final int cCV) {
		this.CCV = cCV;
	}

}
