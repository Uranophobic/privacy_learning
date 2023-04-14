package com.privacy.web.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.privacy.web.model.Favola;

@Service
public interface FavolaService {
	int findByLastId();
	public List<Favola> findAllFavole();
	Favola findById(int id);
	void save(Favola a);
	boolean existsByTesto(String testo);
	boolean existsByTitolo(String titolo);
	void deleteById(int id);
}
