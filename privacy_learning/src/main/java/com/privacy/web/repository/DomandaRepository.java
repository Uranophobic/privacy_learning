package com.privacy.web.repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.privacy.web.model.Domanda;

public interface DomandaRepository extends CrudRepository<Domanda,Integer> {

	public ArrayList<Domanda> findByIdTest(int idTest);
	
	

}
