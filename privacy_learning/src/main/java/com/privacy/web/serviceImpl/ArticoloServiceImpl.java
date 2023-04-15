package com.privacy.web.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.privacy.web.model.Articolo;
import com.privacy.web.repository.ArticoloRepository;
import com.privacy.web.service.ArticoloService;

@Service
public class ArticoloServiceImpl implements ArticoloService {
	
	@Autowired
	private ArticoloRepository artRep;

	public List<Articolo> findAllArticoli() {
		return (List<Articolo>) artRep.findAll();
	}

	@Override
	public boolean existsByTitolo(String titolo) {
		return artRep.existsByTitolo(titolo);
	}

	@Override
	public void save(Articolo a) {
		artRep.save(a);
	}

	@Override
	public Articolo findByIdArticolo(int id) {
		// TODO Auto-generated method stub
		return artRep.findArticoloById(id);
	}

	@Override
	public void deleteById(int id) {
		artRep.deleteById(id);
		
	}

/*	@Override
	public int findLastId() {
		// TODO Auto-generated method stub
		return artRep.returnLastId();
	}
*/
}
