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
	
	Salvataggio save(Salvataggio s);
}
