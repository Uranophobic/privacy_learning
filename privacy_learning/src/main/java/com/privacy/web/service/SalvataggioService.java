package com.privacy.web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.privacy.web.model.Salvataggio;

@Service
public interface SalvataggioService {
	
	Salvataggio save(Salvataggio s) throws Exception;
	List<Salvataggio> findAllSalvataggio();
	/* List<Salvataggio> findAllByEmail(String email); */
}
