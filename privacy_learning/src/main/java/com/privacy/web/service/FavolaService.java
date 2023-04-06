package com.privacy.web.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.privacy.web.model.Favola;

@Service
public interface FavolaService {
	
	public List<Favola> findAllFavole();
}
