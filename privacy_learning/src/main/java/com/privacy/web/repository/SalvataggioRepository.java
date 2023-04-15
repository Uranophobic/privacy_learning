package com.privacy.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.privacy.web.model.Salvataggio;

public interface SalvataggioRepository  extends CrudRepository<Salvataggio,Integer>{
	
	/*
	 * @Query(value = "SELECT * FROM Salvataggio s WHERE s.email_utente= ?1",
	 * nativeQuery = true) List<Salvataggio> findAllByEmail(String email);
	 */
	
    
	List<Salvataggio> findByEmailAndIdTest(String email, int idTest);
	@Query(value = "Delete * FROM Salvataggio s WHERE s.email_utente= ?1 and s.id_test=?2", nativeQuery = true)
	void deleteByEmailAndIdTest(String email,int test);
	
	@Query(value = "Select max(id_test) FROM Salvataggio s WHERE s.email_utente= ?1 ORDER BY s.id_test ASC", nativeQuery = true)
	int returnLastIdtestByEmail(String email);
}
