package com.privacy.web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.privacy.web.model.Suggerimento;

@Service
public interface SuggerimentoService {
	void save(Suggerimento s);
	Suggerimento findByEmailAndMeta(String email, String m);
	List<Suggerimento> findAllByEmail(String email);
}
