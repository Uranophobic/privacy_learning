package com.privacy.web.repository;

import org.springframework.data.repository.CrudRepository;

import com.privacy.web.model.Test;

public interface TestRepository extends CrudRepository<Test, Integer> {
	
}
