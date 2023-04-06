package com.privacy.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.privacy.web.model.Articolo;
import com.privacy.web.repository.ArticoloRepository;

@Service
public interface ArticoloService {


	public List<Articolo> findAllArticoli();
}
