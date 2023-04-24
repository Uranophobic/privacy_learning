package com.privacy.web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.privacy.web.model.ProgressoStudio;
import com.privacy.web.model.Salvataggio;

@Service
public interface ProgressoService {
	
	void save(ProgressoStudio p);

	List<ProgressoStudio> findByEmail(String email);
}
