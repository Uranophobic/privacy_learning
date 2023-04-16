package com.privacy.web.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.privacy.web.model.Test;

public interface TestRepository extends CrudRepository<Test, Integer> {
	
	@Query(value = "Select * FROM Test t WHERE t.id_test= ?1", nativeQuery = true)
	Test findByIdTest(int id);
	
	@Query(value = "Select Test.tipo_test FROM Test t WHERE t.id_test= ?1", nativeQuery = true)
	String findTipoById(int id);
	
	@Query(value = "Select Test.id_test FROM Test t WHERE t.id_test= ?1", nativeQuery = true)
	int returnIdByTipo(String tipo);
}
