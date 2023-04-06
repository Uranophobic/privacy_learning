package com.privacy.web.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.privacy.web.model.Favola;
import com.privacy.web.repository.FavolaRepository;

@Service
public class FavolaService {
	@Autowired
	private FavolaRepository favolaRep;
	
	public List<Favola> findAllFavole(){
		return (List<Favola>) favolaRep.findAll();
	}
}
