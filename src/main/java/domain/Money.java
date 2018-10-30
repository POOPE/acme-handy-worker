
package domain;

public class Money {

	public double	amount;
	public String	currency;


	public Money(final double amount, final String currency) {
		super();
		this.amount = amount;
		this.currency = currency;
	}

	public double getAmount() {
		return this.amount;
	}

	public void setAmount(final double amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(final String currency) {
		this.currency = currency;
	}

	//TODO: fix this to work with multiple currencies
	public void add(final Money money) {
		this.amount += money.amount;
	}

	public void substract(final Money money) {
		this.amount -= money.amount;
	}
}
