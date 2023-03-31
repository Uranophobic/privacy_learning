package com.privacy.web.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller		
public class HomeControl {

	@GetMapping("/homepage")
	public String home(Model model) {
		return "HomePage";
	}
	
	@GetMapping("/login")
	public String login(Model model) {
		return "Login";
	}
}
