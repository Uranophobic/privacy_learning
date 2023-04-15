package com.privacy.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.privacy.web.model.Domanda;

public interface DomandaRepository extends CrudRepository<Domanda,Integer> {
	
	
	
	@Query(value="SELECT * FROM Domanda d WHERE d.id_test=?1", nativeQuery=true)
	List<Domanda> findByIdTest(int idTest);
	
	@Query(value="SELECT * FROM Domanda d WHERE d.id_domanda=?1", nativeQuery=true)
	Domanda findDomandaById(int id);

	boolean existsByTesto(String testo);

	
	
}
