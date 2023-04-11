package com.privacy.web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.privacy.web.model.Articolo;

@Service
public interface ArticoloService {


	public List<Articolo> findAllArticoli();
}
