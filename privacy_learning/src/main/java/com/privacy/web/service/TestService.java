package com.privacy.web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.privacy.web.model.Test;

@Service
public interface TestService {
	List<Test> findAllTest();
	Test findById(int id);
	String returnTipoById(int id);
	int returnIdByTipo(String tipo);
}
