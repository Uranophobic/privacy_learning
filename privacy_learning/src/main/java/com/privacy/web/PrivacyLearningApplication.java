package com.privacy.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.privacy.web.control"})
public class PrivacyLearningApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrivacyLearningApplication.class, args);
	}

}
