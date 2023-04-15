package com.privacy.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;

import com.privacy.web.model.Favola;

@EnableJpaRepositories
public interface FavolaRepository extends CrudRepository<Favola, Integer> {
	@Query(value = "Select max(id_favola) FROM Favola", nativeQuery = true)
	int returnLastId();
	@Query(value = "SELECT * FROM Favola", nativeQuery = true)
	public List<Favola> findAll();
	boolean existsByTestofavola(String testo);
	boolean existsByTitolofavola(String titolo);
	@Query(value="Select * FROM Favola f WHERE f.id_favola=?1" ,nativeQuery = true)
	Favola findByIdFavola(int id);
}
