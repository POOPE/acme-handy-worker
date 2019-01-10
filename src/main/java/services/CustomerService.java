
package services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CustomerRepository;
import domain.Customer;

@Service
@Transactional
public class CustomerService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private CustomerRepository	customerRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private ActorService		actorService;

	@Autowired
	private UserAccountService	userAccountService;


	// Constructors -----------------------------------------------------------

	public CustomerService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Customer createCustomer() {
		Customer customer = new Customer();
		customer = (Customer) this.actorService.initialize(customer);
		customer = this.initializeCustomer(customer);
		return customer;
	}

	public Customer save(Customer customer) {
		if (customer.getId() == 0) {
			Md5PasswordEncoder encoder = new Md5PasswordEncoder();
			customer.getUser().setPassword(encoder.encodePassword(customer.getUser().getPassword(), null));
			Customer res = this.customerRepository.save(customer);
			res = (Customer) this.actorService.postInitialize(res);
			return res;
		} else {
			Customer current = this.findOne(customer.getId());
			if (current.getUser().getPassword() != customer.getUser().getPassword()) {
				Md5PasswordEncoder encoder = new Md5PasswordEncoder();
				customer.getUser().setPassword(encoder.encodePassword(customer.getUser().getPassword(), null));
			}
			return this.customerRepository.save(customer);
		}
	}

	public Customer findOne(int customerId) {
		Assert.isTrue(customerId > 0);
		return this.customerRepository.findOne(customerId);
	}
	// Other business methods -------------------------------------------------

	public Customer findPrincipal() {
		this.actorService.assertPrincipalAuthority("CUSTOMER");
		return (Customer) this.actorService.findPrincipal();
	}

	public Customer initializeCustomer(Customer customer) {
		customer.setUser(this.userAccountService.addAuthority(customer.getUser(), "CUSTOMER"));
		return customer;
	}
}
