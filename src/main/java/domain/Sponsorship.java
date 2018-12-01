
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Sponsorship extends DomainEntity {

	//relations
	public Sponsor		author;
	//attributes
	public String		bannerUrl;
	public String		targetPage;
	public CreditCard	creditcard;


	@ManyToOne(optional = false)
	public Sponsor getAuthor() {
		return this.author;
	}

	public void setAuthor(final Sponsor author) {
		this.author = author;
	}
	@URL
	public String getBannerUrl() {
		return this.bannerUrl;
	}

	public void setBannerUrl(final String bannerUrl) {
		this.bannerUrl = bannerUrl;
	}
	@URL
	public String getTargetPage() {
		return this.targetPage;
	}

	public void setTargetPage(final String targetPage) {
		this.targetPage = targetPage;
	}

	@ManyToOne(optional = false)
	public CreditCard getCreditcard() {
		return this.creditcard;
	}

	public void setCreditcard(final CreditCard creditcard) {
		this.creditcard = creditcard;
	}

}
