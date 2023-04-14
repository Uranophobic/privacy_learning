package com.privacy.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;

import com.privacy.web.model.Favola;

@EnableJpaRepositories
 public interface FavolaRepository extends CrudRepository<Favola,Integer> {
	@Query(name = "Select Last(id_favola) as LastId FROM Favola", nativeQuery = true)
	int findByLastId();
	 @Query(value = "SELECT * FROM Favola", nativeQuery = true)
	  public List<Favola> findAll();
	 boolean existsByTesto_favola(String testo);
	 boolean existsByTitolo_favola(String titolo);
	 
}
