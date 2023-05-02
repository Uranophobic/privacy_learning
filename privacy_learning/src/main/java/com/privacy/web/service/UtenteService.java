package com.privacy.web.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.privacy.web.model.Utente;

@Service
public interface UtenteService {

	List<Utente> findAll();
	Utente save(Utente user);
	boolean existsById(String id);
	Utente findUtenteByEmail (String email);
	Utente login(String email, String password) throws SQLException;
	Utente findUtenteByEmailAndPassword(String email, String password);
	void deleteById(String email);
	void updateByEmail(String email);
}
