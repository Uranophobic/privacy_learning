package com.privacy.web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.privacy.web.model.Domanda;

@Service
public interface DomandaService {
	int countDomandeByIdTest(int id_test);
	List<Domanda> findByIdTest(int idTest);
	Domanda findById(int id);
	void deleteById(int id);
	void save(Domanda domExist);
	boolean existsByTesto(String testo);
	
	
}
