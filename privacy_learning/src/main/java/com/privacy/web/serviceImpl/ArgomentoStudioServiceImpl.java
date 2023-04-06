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

}
