package com.privacy.web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.privacy.web.model.Salvataggio;

@Service
public interface SalvataggioService {
	
	void save(Salvataggio s);
	List<Salvataggio> findAllSalvataggio();
	List<Salvataggio> findByEmailAndIdTest(String email, int id);
	void deleteByEmailAndIdTest(String email,int test);
	int returnLastIdtestByEmail(String email);
	List<Salvataggio> findByEmail(String email);
	List<Salvataggio> findByMaxIdTest(String email_utente);
	void delete(Salvataggio s);
}
