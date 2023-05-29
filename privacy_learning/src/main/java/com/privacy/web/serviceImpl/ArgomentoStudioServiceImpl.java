package com.privacy.web.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.privacy.web.model.ArgomentoStudio;
import com.privacy.web.repository.ArgomentoStudioRepository;
import com.privacy.web.service.ArgomentoStudioService;

@Service
public class ArgomentoStudioServiceImpl implements ArgomentoStudioService{

	@Autowired
	private ArgomentoStudioRepository argRep;
	
	@Override
	public List<ArgomentoStudio> findAllArgomenti() {
		return (List<ArgomentoStudio>) argRep.findAll();
	}

	@Override
	public ArgomentoStudio findById(int id) {
		return argRep.findArgomentoById(id);
	}

	@Override
	public int findByLastId() {
		return argRep.returnLastId();
	}

	@Override
	public boolean existsByTitolo(String titolo) {
		return argRep.existsByTitolo(titolo);
	}

	@Override
	public void save(ArgomentoStudio a) {
		argRep.save(a);		
	}

	@Override
	public boolean existsByDescrizione(String descrizione) {
		return argRep.existsByDescrizione(descrizione);
	}

	@Override
	public boolean existsByLink(String link) {
		
		return argRep.existsByLinkvideo(link);
	}

	@Override
	public void deleteById(int id) {
		argRep.deleteById(id);
	}

	@Override
	public List<ArgomentoStudio> findAllArgDaStudiare(String email) {
		return argRep.findAllArgDaStudiare(email);
	}

	/*
	 * @Override public List<ArgomentoStudio> findArgomentiNoStudy(String email) {
	 * return argRep.findArgomentiNoStudy(email); }
	 */

	@Override
	public ArgomentoStudio findArgomentoByTitolo(String titolo) {
		return argRep.findArgomentoByTitolo(titolo);
	}

	@Override
	public List<ArgomentoStudio> findArgomentoByMeta(String meta) {
		return argRep.findArgomentoByMeta(meta);
	}

	@Override
	public List<ArgomentoStudio> findArgomentiNoStudy(String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
