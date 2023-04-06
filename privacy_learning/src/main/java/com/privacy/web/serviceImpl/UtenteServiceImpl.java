package com.privacy.web.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.privacy.web.model.Utente;
import com.privacy.web.repository.UtenteRepository;
import com.privacy.web.service.UtenteService;

@Service
public class UtenteServiceImpl implements UtenteService {

	private UtenteRepository utenteRep;
	
	
	
	public UtenteServiceImpl(UtenteRepository utenteRep) {
		super();
		this.utenteRep = utenteRep;
	}



	@Override
	public List<Utente> findAll() {
		// TODO Auto-generated method stub
		return (List<Utente>) utenteRep.findAll();
	}



	@Override
	public Utente saveUser(Utente user) {
		return utenteRep.save(user);
	}

}
