package com.privacy.web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.privacy.web.model.ProgressoStudio;
import com.privacy.web.model.Salvataggio;

@Service
public interface ProgressoService {
	
	void save(ProgressoStudio p);
	ProgressoStudio findByEmailAndArgomento(String email, String m);
	List<ProgressoStudio> findAllByEmail(String email);
	List<ProgressoStudio> findByEmail(String email);
}
