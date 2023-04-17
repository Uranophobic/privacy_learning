package com.privacy.web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.privacy.web.model.ProgressoStudio;

@Service
public interface SuggerimentoService {
	void save(ProgressoStudio s);
	ProgressoStudio findByEmailAndMeta(String email, String m);
	List<ProgressoStudio> findAllByEmail(String email);
}
