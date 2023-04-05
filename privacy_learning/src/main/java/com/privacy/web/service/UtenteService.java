package com.privacy.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.privacy.web.model.Utente;
import com.privacy.web.repository.UtenteRepository;

@Service
public class UtenteService {
	
	@Autowired
	private UtenteRepository utRep;
	
	public List<Utente> findAll(){
		return (List<Utente>) this.utRep.findAll();
	}
	
	 
}
