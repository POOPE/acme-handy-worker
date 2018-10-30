
package domain;

public class Sponsorship extends DomainEntity {

	//relations
	public Actor		author;
	//attributes
	public String		bannerUrl;
	public String		targetPage;
	public CreditCard	creditcard;


	public Actor getAuthor() {
		return this.author;
	}

	public void setAuthor(final Actor author) {
		this.author = author;
	}

	public String getBannerUrl() {
		return this.bannerUrl;
	}

	public void setBannerUrl(final String bannerUrl) {
		this.bannerUrl = bannerUrl;
	}

	public String getTargetPage() {
		return this.targetPage;
	}

	public void setTargetPage(final String targetPage) {
		this.targetPage = targetPage;
	}

	public CreditCard getCreditcard() {
		return this.creditcard;
	}

	public void setCreditcard(final CreditCard creditcard) {
		this.creditcard = creditcard;
	}

}
