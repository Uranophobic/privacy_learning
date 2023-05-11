package com.privacy.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.privacy.web.model.ProgressoStudio;

public interface ProgressoRepository  extends CrudRepository<ProgressoStudio,Integer>{
	

	@Query(value = "SELECT * FROM progresso_studio p WHERE p.email_utente=?1", nativeQuery = true)
	List<ProgressoStudio> findByEmail (String email);

	@Query(value = "Select * FROM progresso_studio s WHERE s.email_utente=?1 AND s.arg_dastudiare=?2", nativeQuery = true)
	ProgressoStudio findByEmailAndArgomento(String email, String argomento);
	
	@Query(value = "Select * FROM progresso_studio s WHERE s.email=?1", nativeQuery = true)
	List<ProgressoStudio> findAllByEmail(String email);

}
