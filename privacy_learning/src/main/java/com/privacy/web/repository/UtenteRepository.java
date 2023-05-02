package com.privacy.web.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.privacy.web.model.Utente;

public interface UtenteRepository extends CrudRepository<Utente, String> {

	@Query(value = "SELECT Utente u FROM Utente WHERE u.email=?1 and u.password=?2", nativeQuery = true)
	public Utente login(String email, String password);

	public Utente findUtenteByEmailAndPassword(String email, String password);
	public Utente findUtenteByEmail(String email);

	@Query(value = "Delete Utente u FROM Utente u WHERE u.email=?1", nativeQuery = true)
	public void deleteByEmail(String email);
	
	@Query(value = "Update u FROM Utente u WHERE u.email=?1", nativeQuery = true)
	public void updateByEmail(String email);
}
