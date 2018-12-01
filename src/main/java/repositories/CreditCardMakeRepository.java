package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.CreditCardMake;

@Repository
public interface CreditCardMakeRepository extends
		JpaRepository<CreditCardMake, Integer> {
	@Query("select a from CreditCardMake a where a.name = ?1")
	List<CreditCardMake> findByName(String name);

}
