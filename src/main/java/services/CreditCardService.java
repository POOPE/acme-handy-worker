
package services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.CreditCardRepository;
import domain.CreditCard;

@Service
@Transactional
public class CreditCardService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private CreditCardRepository	creditCardRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private ActorService			actorService;


	public CreditCard findById(int id) {
		return this.creditCardRepository.findOne(id);
	}

	public CreditCard findByPrinciapl() {
		return this.creditCardRepository.findByActor(this.actorService.findPrincipal().getId());
	}

}
