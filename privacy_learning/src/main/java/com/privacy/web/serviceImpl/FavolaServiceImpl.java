package com.privacy.web.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.privacy.web.model.Favola;
import com.privacy.web.repository.FavolaRepository;
import com.privacy.web.service.FavolaService;

@Service
public class FavolaServiceImpl implements FavolaService {

	@Autowired
	private FavolaRepository favRep;

	@Override
	public List<Favola> findAllFavole() {
		return favRep.findAll();
	}

	@Override
	public Favola findById(int id) {
		return favRep.findByIdFavola(id);
	}

	@Override
	public int findByLastId() {
		return favRep.returnLastId();
	}

	@Override
	public void save(Favola a) {
		favRep.save(a);
	}

	@Override
	public boolean existsByTesto(String testo) {
		return favRep.existsByTestofavola(testo);
	}

	@Override
	public boolean existsByTitolo(String titolo) {
		return favRep.existsByTitolofavola(titolo);
	}

	@Override
	public void deleteById(int id) {
		favRep.deleteById(id);
	}

}
