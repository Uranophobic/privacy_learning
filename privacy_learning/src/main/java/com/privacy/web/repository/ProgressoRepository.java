package com.privacy.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.privacy.web.model.ProgressoStudio;
import com.privacy.web.model.Salvataggio;

public interface ProgressoRepository  extends CrudRepository<ProgressoStudio,Integer>{
	

	@Query(value = "SELECT * FROM progresso_studio p WHERE p.email_utente=?1", nativeQuery = true)
	List<ProgressoStudio> findByEmail (String email);


}
