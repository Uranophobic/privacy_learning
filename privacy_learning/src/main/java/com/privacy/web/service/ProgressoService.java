package com.privacy.web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.privacy.web.model.ProgressoStudio;

@Service
public interface ProgressoService {
	
	void save(ProgressoStudio p);
	ProgressoStudio findByEmailAndArgomento(String email, String arg_dastudiare);
	List<ProgressoStudio> findByEmail(String email);
	///ProgressoStudio findByTitolo(String titolo);
}
