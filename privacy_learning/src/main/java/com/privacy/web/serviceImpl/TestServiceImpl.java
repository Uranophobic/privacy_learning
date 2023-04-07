package com.privacy.web.serviceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.privacy.web.repository.TestRepository;
import com.privacy.web.service.TestService;

@Service
public class TestServiceImpl implements TestService {
	@Autowired
	private TestRepository testRep;

}
