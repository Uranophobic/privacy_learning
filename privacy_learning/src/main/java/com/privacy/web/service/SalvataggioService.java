package com.privacy.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.privacy.web.model.Salvataggio;
import com.privacy.web.repository.SalvataggioRepository;

@Service
public class SalvataggioService {
	@Autowired
	private SalvataggioRepository salvRep;
	
	void save(Salvataggio s) {
		salvRep.save(s);
	}
	
	List<Salvataggio> findAllSalvataggio(){
		return (List<Salvataggio>) salvRep.findAll();
	}
	
	List<Salvataggio> findAllByEmail(String email){
		return salvRep.findAllByEmail(email);
	}
}
