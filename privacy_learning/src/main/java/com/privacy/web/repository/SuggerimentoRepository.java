package com.privacy.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.privacy.web.model.ProgressoStudio;

public interface SuggerimentoRepository  extends CrudRepository<ProgressoStudio,Integer>{

	@Query(value = "Select * FROM progresso_studio s WHERE s.email=?1 AND s.meta_info=?2", nativeQuery = true)
	ProgressoStudio findByEmailAndMeta(String email, String meta);
	
	@Query(value = "Select * FROM progresso_srudio s WHERE s.email=?1", nativeQuery = true)
	List<ProgressoStudio> findAllByEmail(String email);

}
