package com.privacy.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import com.privacy.web.model.Favola;
import com.privacy.web.model.Utente;

@EnableJpaRepositories
 public interface FavolaRepository extends JpaRepository<Favola,Integer> {

	 @Query(value = "SELECT * FROM FAVOLA", nativeQuery = true)
	  public List<Favola> findAll();
}
