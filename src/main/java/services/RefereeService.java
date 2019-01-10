
package services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.RefereeRepository;
import security.Authority;
import domain.Referee;

@Service
@Transactional
public class RefereeService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private RefereeRepository	refereeRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private ActorService		actorService;

	@Autowired
	private UserAccountService	userAccountService;


	// Constructors -----------------------------------------------------------

	public RefereeService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Referee createReferee() {
		Referee referee = new Referee();
		referee = (Referee) this.actorService.initialize(referee);
		referee = this.initializeReferee(referee);
		return referee;
	}

	public Referee save(Referee referee) {
		if (referee.getId() == 0) {
			Md5PasswordEncoder encoder = new Md5PasswordEncoder();
			referee.getUser().setPassword(encoder.encodePassword(referee.getUser().getPassword(), null));
			Referee res = this.refereeRepository.save(referee);
			res = (Referee) this.actorService.postInitialize(res);
			return res;

		} else {
			Referee current = this.findOne(referee.getId());
			if (current.getUser().getPassword() != referee.getUser().getPassword()) {
				Md5PasswordEncoder encoder = new Md5PasswordEncoder();
				referee.getUser().setPassword(encoder.encodePassword(referee.getUser().getPassword(), null));
			}
			return this.refereeRepository.save(referee);
		}
	}

	public Referee findOne(int refereeId) {
		Assert.isTrue(refereeId > 0);
		return this.refereeRepository.findOne(refereeId);
	}
	// Other business methods -------------------------------------------------

	public Referee findPrincipal() {
		Assert.isTrue(this.actorService.getPrincipalAuthority().contains(Authority.REFEREE), "The user logged is not a referee.");
		return (Referee) this.actorService.findPrincipal();
	}

	public Referee initializeReferee(Referee referee) {
		referee.setUser(this.userAccountService.addAuthority(referee.getUser(), "REFEREE"));
		return referee;
	}
}
