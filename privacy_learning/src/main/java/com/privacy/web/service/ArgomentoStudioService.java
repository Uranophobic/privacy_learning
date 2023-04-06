package com.privacy.web.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.privacy.web.model.ArgomentoStudio;
import com.privacy.web.repository.ArgomentoStudioRepository;

@Service
public class ArgomentoStudioService {
	
	@Autowired
	ArgomentoStudioRepository argRep;
	
	public List<ArgomentoStudio> findAllArgomenti(){
		return (List<ArgomentoStudio>) argRep.findAll();
	}
}
