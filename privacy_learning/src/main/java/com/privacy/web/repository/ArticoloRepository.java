package com.privacy.web.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.privacy.web.model.Articolo;

public interface ArticoloRepository  extends CrudRepository<Articolo,Integer>{
/*	@Query(name = "Select LAST(id_articolo) FROM Articolo ORDER BY id_articolo ASC", nativeQuery = true)
	int returnLastId();
*/	boolean existsByTitolo(String titolo);
	boolean existsByLink(String link);

}
