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

}
