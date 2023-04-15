package com.privacy.web.serviceImpl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.privacy.web.model.Domanda;
import com.privacy.web.repository.DomandaRepository;
import com.privacy.web.service.DomandaService;

@Service
public class DomandaServiceImpl implements DomandaService {

	@Autowired
	private DomandaRepository domRep;


	@Override
	public List<Domanda> findByIdTest(int idTest) {
		return domRep.findByIdTest(idTest);
	}


	@Override
	public int countDomandeByIdTest(int id_test) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public Domanda findById(int id) {
		// TODO Auto-generated method stub
		return (Domanda) domRep.findDomandaById(id);
	}


	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		domRep.deleteById(id);
	}


	@Override
	public void save(Domanda d) {
		// TODO Auto-generated method stub
		domRep.save(d);
	}


	@Override
	public boolean existsByTesto(String testo) {
		return domRep.existsByTesto(testo);
	}

	

	

}
