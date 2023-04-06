package com.privacy.web.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.privacy.web.model.ArgomentoStudio;

public interface ArgomentoStudioRepository extends CrudRepository<ArgomentoStudio, Integer> {

	@Query(value = "Select * FROM argomento_studio AS a WHERE a.id_studio= :id", nativeQuery = true)
	ArgomentoStudio findArgomentoById(@Param("id") int id);
}
