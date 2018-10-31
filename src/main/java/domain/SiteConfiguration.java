
package domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

import org.hibernate.validator.constraints.URL;

public class SiteConfiguration {

	public String								siteName;
	public String								bannerUrl;
	public String								welcomeMessage;
	public Set<String>							spamWords;
	public Set<String> 							goodWords;
	public Set<String> 							badWords;
	public Float								vatRate;
	public Integer								defaultCountryCode;
	public Integer								finderResLimit;
	public Integer								finderTimeLimit;


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
	
	public Collection<String> getSpamWords() {
		return this.spamWords;
	}

	public void setSpamWords(Set<String> spamWords) {
		this.spamWords = spamWords;
	}
	
	public Set<String> getGoodWords() {
		return goodWords;
	}

	public void setGoodWords(Set<String> goodWords) {
		this.goodWords = goodWords;
	}

	public Set<String> getBadWords() {
		return badWords;
	}

	public void setBadWords(Set<String> badWords) {
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
