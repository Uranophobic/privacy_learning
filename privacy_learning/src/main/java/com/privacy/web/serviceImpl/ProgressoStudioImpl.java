package com.privacy.web.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.privacy.web.model.ProgressoStudio;
import com.privacy.web.repository.ProgressoRepository;
import com.privacy.web.service.ProgressoService;

@Service
public class ProgressoStudioImpl implements ProgressoService {

	@Autowired
	private ProgressoRepository progRep;
	
	@Override
	public void save(ProgressoStudio p) {
		progRep.save(p);
		
	}

	@Override
	public List<ProgressoStudio> findByEmail(String email) {
		return progRep.findByEmail(email);
	}

}
