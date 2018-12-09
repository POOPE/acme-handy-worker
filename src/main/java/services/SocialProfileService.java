
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.SocialProfileRepository;
import domain.SocialProfile;

@Service
@Transactional
public class SocialProfileService {

	@Autowired
	private SocialProfileRepository	socialProfileRepository;

	@Autowired
	private ActorService			actorService;


	//CRUD ---------------------------------------------------------------

	public SocialProfile create() {
		SocialProfile socialProfile = new SocialProfile();
		return socialProfile;
	}

	public SocialProfile save(SocialProfile socialProfile) {
		return this.socialProfileRepository.save(socialProfile);
	}

	public SocialProfile find(int socialProfileId) {
		SocialProfile res = this.socialProfileRepository.findOne(socialProfileId);
		return res;
	}

	//OTHER ----------------------------------------------------------------

	public SocialProfile initialize(SocialProfile profile) {
		profile.setOwner(this.actorService.findPrincipal());
		SocialProfile res = this.save(profile);
		return res;
	}
}
