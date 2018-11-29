
package services;

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
}
