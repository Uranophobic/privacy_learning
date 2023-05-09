package com.privacy.web.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.privacy.web.model.Salvataggio;
import com.privacy.web.repository.SalvataggioRepository;
import com.privacy.web.service.SalvataggioService;

@Service
public class SalvataggioServiceImpl implements SalvataggioService {

	@Autowired
	private SalvataggioRepository salRep;
	
	@Override
	public void save(Salvataggio s) {
		salRep.save(s);
	}

	@Override
	public List<Salvataggio> findAllSalvataggio() {
		return (List<Salvataggio>) salRep.findAll();
	}

	@Override
	public List<Salvataggio> findByEmailAndIdTest(String email, int id) {
		
		return salRep.findByEmailAndIdTest(email, id);
	}

	@Override
	public void deleteByEmailAndIdTest(String email, int test) {
		salRep.deleteByEmailAndIdTest(email, test);
	}

	@Override
	public int returnLastIdtestByEmail(String email) {
		// TODO Auto-generated method stub
		return salRep.returnLastIdtestByEmail(email);
	}

	@Override
	public List<Salvataggio> findByEmail(String email) {
		return salRep.findByEmail(email);
	}

	@Override
	public List<Salvataggio> findByMaxIdTest(String email_utente) {
		return salRep.findByMaxIdTest(email_utente);
	}

	@Override
	public void delete(Salvataggio s) {
		// TODO Auto-generated method stub
		salRep.delete(s);
	}



	
	
 


	/*
	 * @Override public List<Salvataggio> findAllByEmail(String email) { return
	 * salRep.findAllByEmail(email); }
	 */

}
