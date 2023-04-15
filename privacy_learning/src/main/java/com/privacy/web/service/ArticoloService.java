package com.privacy.web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.privacy.web.model.Articolo;

@Service
public interface ArticoloService {
	public void deleteById(int id);
	public boolean existsByTitolo(String titolo);
	public List<Articolo> findAllArticoli();
	public void save(Articolo a);
	public Articolo findByIdArticolo(int id);
//	public int findLastId();
}
