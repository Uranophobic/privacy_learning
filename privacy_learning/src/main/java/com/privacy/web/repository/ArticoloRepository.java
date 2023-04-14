package com.privacy.web.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.privacy.web.model.Articolo;

public interface ArticoloRepository  extends CrudRepository<Articolo,Integer>{
	@Query(name = "Select Last(id_articolo) as LastId FROM Articolo", nativeQuery = true)
	int findByLastId();
	boolean existsByTitolo(String titolo);
	boolean existsByLink(String link);

}
