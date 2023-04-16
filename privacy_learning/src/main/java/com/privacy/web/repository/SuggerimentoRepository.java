package com.privacy.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.privacy.web.model.Suggerimento;

public interface SuggerimentoRepository  extends CrudRepository<Suggerimento,Integer>{

	@Query(value = "Select * FROM suggerimento s WHERE s.email=?1 AND s.meta_info=?2", nativeQuery = true)
	Suggerimento findByEmailAndMeta(String email, String meta);
	
	@Query(value = "Select * FROM suggerimento s WHERE s.email=?1", nativeQuery = true)
	List<Suggerimento> findAllByEmail(String email);

}
