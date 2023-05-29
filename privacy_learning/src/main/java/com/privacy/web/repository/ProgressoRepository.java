package com.privacy.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.privacy.web.model.ArgomentoStudio;
import com.privacy.web.model.ProgressoStudio;

public interface ProgressoRepository  extends CrudRepository<ProgressoStudio,Integer>{
	

	@Query(value = "SELECT * FROM progresso_studio  WHERE email_utente=?1", nativeQuery = true)
	List<ProgressoStudio> findByEmail (String email);

	@Query(value = "Select * FROM progresso_studio  WHERE email_utente=?1 AND arg_dastudiare=?2", nativeQuery = true)
	ProgressoStudio findByEmailAndArgomento(String email, String arg_dastudiare);
	
	/*
	 * @Query(value = "Select * FROM progresso_studio  WHERE arg_dastudiare=?1",
	 * nativeQuery = true) ProgressoStudio findByTitolo(String titolo);
	 */
	

	
}
