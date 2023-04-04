package com.privacy.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;

import com.privacy.web.model.Favola;

@EnableJpaRepositories
 public interface FavolaRepository extends CrudRepository<Favola,Integer> {

	 @Query(value = "SELECT * FROM FAVOLA", nativeQuery = true)
	  public List<Favola> findAll();
}
