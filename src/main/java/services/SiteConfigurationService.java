
package services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.SiteConfigurationRepository;
import domain.SiteConfiguration;

@Service
@Transactional
public class SiteConfigurationService {

	@Autowired
	private SiteConfigurationRepository	siteConfigurationRepository;


	public SiteConfiguration find() {
		return this.siteConfigurationRepository.find();
	}

	public SiteConfiguration update(SiteConfiguration siteConfig) {
		return this.siteConfigurationRepository.save(siteConfig);
	}

	public SiteConfiguration restore() {
		SiteConfiguration siteConfig = this.find();
		siteConfig.setSiteName("Acme Handy Worker");
		siteConfig.setBannerUrl("http://www.sample-banner.com/banner.png");
		siteConfig.setWelcomeMessage("");
		siteConfig.setVatRate(0.20f);
		siteConfig.setDefaultCountryCode(34);
		siteConfig.setFinderResLimit(10);
		siteConfig.setFinderTimeLimit(3600);

		List<String> spamWords = new ArrayList<>();
		spamWords.add("viagra");
		spamWords.add("sex");
		spamWords.add("one million");
		spamWords.add("cialis");
		spamWords.add("you've been selected");
		spamWords.add("nigeria");
		spamWords.add("sexo");
		spamWords.add("un millon");
		spamWords.add("ha sido seleccionado");
		siteConfig.setSpamWords(spamWords);

		List<String> goodWords = new ArrayList<>();
		goodWords.add("nuevo");
		siteConfig.setGoodWords(goodWords);

		List<String> badWords = new ArrayList<>();
		goodWords.add("chapuza");
		siteConfig.setBadWords(badWords);

		return this.update(siteConfig);
	}
}
