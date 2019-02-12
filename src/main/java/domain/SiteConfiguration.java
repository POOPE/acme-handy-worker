
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class SiteConfiguration extends DomainEntity {

	public String				siteName;
	public String				bannerUrl;
	public String				welcomeMessage;
	public String				welcomeMessageEsp;
	public Collection<String>	spamWords;
	public Collection<String>	goodWords;
	public Collection<String>	badWords;
	public Float				vatRate;
	public Integer				defaultCountryCode;
	public Integer				finderResLimit;
	public Integer				finderTimeLimit;


	public String getSiteName() {
		return this.siteName;
	}

	public void setSiteName(final String siteName) {
		this.siteName = siteName;
	}
	@URL
	public String getBannerUrl() {
		return this.bannerUrl;
	}

	public void setBannerUrl(final String bannerUrl) {
		this.bannerUrl = bannerUrl;
	}

	public String getWelcomeMessage() {
		return this.welcomeMessage;
	}

	public void setWelcomeMessage(final String welcomeMessage) {
		this.welcomeMessage = welcomeMessage;
	}

	@ElementCollection
	public Collection<String> getSpamWords() {
		return this.spamWords;
	}

	public void setSpamWords(final Collection<String> spamWords) {
		this.spamWords = spamWords;
	}

	@ElementCollection
	public Collection<String> getGoodWords() {
		return this.goodWords;
	}

	public void setGoodWords(final Collection<String> goodWords) {
		this.goodWords = goodWords;
	}

	@ElementCollection
	public Collection<String> getBadWords() {
		return this.badWords;
	}

	public void setBadWords(final Collection<String> badWords) {
		this.badWords = badWords;
	}

	public Float getVatRate() {
		return this.vatRate;
	}

	public void setVatRate(final Float vatRate) {
		this.vatRate = vatRate;
	}

	public Integer getDefaultCountryCode() {
		return this.defaultCountryCode;
	}

	public void setDefaultCountryCode(final Integer defaultCountryCode) {
		this.defaultCountryCode = defaultCountryCode;
	}

	public Integer getFinderResLimit() {
		return this.finderResLimit;
	}

	public void setFinderResLimit(final Integer finderResLimit) {
		this.finderResLimit = finderResLimit;
	}

	public Integer getFinderTimeLimit() {
		return this.finderTimeLimit;
	}

	public void setFinderTimeLimit(final Integer finderTimeLimit) {
		this.finderTimeLimit = finderTimeLimit;
	}

}
