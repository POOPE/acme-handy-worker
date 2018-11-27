
package services;

import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import security.Authority;
import security.UserAccount;
import security.UserAccountRepository;

@Service
@Transactional
public class UserAccountService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private UserAccountRepository	adminRepository;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public UserAccountService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public UserAccount createUserAccount() {
		UserAccount user = new UserAccount();
		user.setAuthorities(new HashSet<Authority>());
		return user;
	}
	// Other business methods -------------------------------------------------

	public UserAccount addAuthority(UserAccount user, String authority) {
		Authority auth = new Authority();
		auth.setAuthority(authority);
		user.getAuthorities().add(auth);
		return user;
	}
}
