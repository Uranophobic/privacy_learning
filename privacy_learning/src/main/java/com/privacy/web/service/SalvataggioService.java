package com.privacy.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.privacy.web.model.Salvataggio;
import com.privacy.web.repository.SalvataggioRepository;

@Service
public interface SalvataggioService {
	
	void save(Salvataggio s);
	List<Salvataggio> findAllSalvataggio();
	List<Salvataggio> findAllByEmail(String email);
}
