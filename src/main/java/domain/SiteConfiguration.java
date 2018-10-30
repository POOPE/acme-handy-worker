
package domain;

import java.util.Collection;
import java.util.HashMap;

public class SiteConfiguration {

	public String								siteName;
	public String								bannerUrl;
	public String								welcomeMessage;
	public Collection<String>					spamWords;
	public HashMap<String, Collection<String>>	scoreWords;
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

	public void setSpamWords(final Collection<String> spamWords) {
		this.spamWords = spamWords;
	}

	public HashMap<String, Collection<String>> getScoreWords() {
		return this.scoreWords;
	}

	public void setScoreWords(final HashMap<String, Collection<String>> scoreWords) {
		this.scoreWords = scoreWords;
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
