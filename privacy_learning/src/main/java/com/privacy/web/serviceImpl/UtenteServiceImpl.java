package com.privacy.web.serviceImpl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.privacy.web.model.Utente;
import com.privacy.web.repository.UtenteRepository;
import com.privacy.web.service.UtenteService;

@Service
public class UtenteServiceImpl implements UtenteService {
	@Autowired
	private UtenteRepository utenteRep;

	public UtenteServiceImpl(UtenteRepository utenteRep) {
		super();
		this.utenteRep = utenteRep;
	}

	@Override
	public List<Utente> findAll() {
		return (List<Utente>) utenteRep.findAll();
	}

	/**
	 * Salva un utente preso in input
	 * 
	 * @param user utente da controllare
	 * @return Utente salvato, null altrimenti
	 */
	@Override
	public Utente save(Utente user) {
		return utenteRep.save(user);
	}

	@Override
	public boolean existsById(String id) {
		return utenteRep.existsById((String) id);
	}

	@Override
	public Utente findUtenteByEmail(String email) {
		return utenteRep.findUtenteByEmail(email);
	}

	@Override
	public Utente login(String email, String password) throws SQLException {
		return utenteRep.login(email, password);
	}

	@Override
	public Utente findUtenteByEmailAndPassword(String email, String password) {
		return utenteRep.findUtenteByEmailAndPassword(email, password);
	}

	@Override
	public void deleteById(String email) {
		utenteRep.deleteById(email);
	}

	@Override
	public void updateByEmail(String email) {
		utenteRep.updateByEmail(email);
		
	}

}
