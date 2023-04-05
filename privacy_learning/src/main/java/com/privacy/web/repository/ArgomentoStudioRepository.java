package com.privacy.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import com.privacy.web.model.ArgomentoStudio;

public interface ArgomentoStudioRepository extends CrudRepository<ArgomentoStudio, Integer> {

	@Query(value = "SELECT * FROM argomento_studio", nativeQuery = true)
	  public List<ArgomentoStudio> findAll();
}
