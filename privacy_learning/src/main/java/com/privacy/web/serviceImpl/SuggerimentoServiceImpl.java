package com.privacy.web.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.privacy.web.model.Suggerimento;
import com.privacy.web.repository.SuggerimentoRepository;
import com.privacy.web.service.SuggerimentoService;

@Service
public class SuggerimentoServiceImpl implements SuggerimentoService{

	@Autowired
	SuggerimentoRepository sugRep;

	@Override
	public void save(Suggerimento s) {
		sugRep.save(s);
	}

	@Override
	public Suggerimento findByEmailAndMeta(String email, String meta) {
		return sugRep.findByEmailAndMeta(email,meta);
		
		
	}

	@Override
	public List<Suggerimento> findAllByEmail(String email) {
		return sugRep.findAllByEmail(email);
	}
	
}
