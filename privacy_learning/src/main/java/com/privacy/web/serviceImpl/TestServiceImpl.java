package com.privacy.web.serviceImpl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.privacy.web.model.Test;
import com.privacy.web.repository.TestRepository;
import com.privacy.web.service.TestService;

@Service
public class TestServiceImpl implements TestService {
	@Autowired
	private TestRepository testRep;

	@Override
	public List<Test> findAllTest() {
		return (List<Test>) testRep.findAll();
	}

	@Override
	public Test findById(int id) {
		return (Test) testRep.findByIdTest(id);
	}

	@Override
	public String returnTipoById(int id) {
		return testRep.findTipoById(id);
	}

	@Override
	public int returnIdByTipo(String tipo) {
		return testRep.returnIdByTipo(tipo);
	}

	
}
