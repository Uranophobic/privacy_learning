package com.privacy.web.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.privacy.web.model.Test;

public interface TestRepository extends CrudRepository<Test, Integer> {
	
	@Query(value="SELECT(count) * FROM Domanda d WHERE d.id_test=?1", nativeQuery=true)
	int findNDomandeById(int id_test);
	
}
