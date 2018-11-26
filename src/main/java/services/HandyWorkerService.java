
package services;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import domain.HandyWorker;

@Service
@Transactional
public interface HandyWorkerService extends JpaRepository<HandyWorker, Integer> {

	@Query("select r from HandyWorker r where r.userAccount.id = ?1")
	HandyWorker findByUserAccountId(int userAccountId);

}
