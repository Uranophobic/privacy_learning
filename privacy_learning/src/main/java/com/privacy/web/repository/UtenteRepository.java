package com.privacy.web.repository;

import java.sql.SQLException;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.privacy.web.model.Utente;


public interface UtenteRepository extends CrudRepository<Utente, String> { 
	
	@Query(value = "SELECT * FROM Utente u WHERE u.email=?1 and u.password=?2",nativeQuery=true)
	public Utente login(String email, String password) throws SQLException;

}
