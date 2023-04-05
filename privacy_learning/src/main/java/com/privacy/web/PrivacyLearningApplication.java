package com.privacy.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.privacy.web.repository.ArgomentoStudioRepository;
import com.privacy.web.model.ArgomentoStudio;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.privacy.web.repository")
public class PrivacyLearningApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(PrivacyLearningApplication.class, args);

	}

}
