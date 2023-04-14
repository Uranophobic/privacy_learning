package com.privacy.web.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.privacy.web.model.ArgomentoStudio;

public interface ArgomentoStudioRepository extends CrudRepository<ArgomentoStudio, Integer> {
/*	@Query(name = "Select LAST(id_studio) FROM argomento_studio ORDER BY id_studio ASC", nativeQuery = true)
	int returnLastId();
*/	@Query(value = "SELECT * FROM argomento_studio AS a WHERE a.id_studio= :id", nativeQuery = true)
	ArgomentoStudio findArgomentoById(@Param("id") int id);
	boolean existsByTitolo(String titolo);
	boolean existsByDescrizione(String descrizione);
	boolean existsByLinkvideo(String link);
}
