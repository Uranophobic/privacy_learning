package com.privacy.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.privacy.web.repository")
public class PrivacyLearningApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(PrivacyLearningApplication.class, args);
	}

}
