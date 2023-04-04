package com.privacy.web.repository;

import java.sql.SQLException;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.privacy.web.model.Utente;


public interface UtenteRepository extends CrudRepository<Utente, String> { 
	@Query(value = "Select * from Utente u where u.email=?1 and u.password=?1",nativeQuery=true)
	public Utente login(String email, String password) throws SQLException;

}
