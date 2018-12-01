package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.SiteConfiguration;

@Repository
public interface SiteConfigurationRepository extends
		JpaRepository<SiteConfiguration, Integer> {

	@Query("select a from SiteConfiguration a")
	SiteConfiguration find();
}
