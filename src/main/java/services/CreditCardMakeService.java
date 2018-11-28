package services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.CreditCardMakeRepository;
import domain.CreditCardMake;

@Service
@Transactional
public class CreditCardMakeService {

	@Autowired
	private CreditCardMakeRepository creditCardMakeRepository;

	public List<CreditCardMake> findCreditCardMake(String name) {
		return this.creditCardMakeRepository.findByName(name);
	}

}
