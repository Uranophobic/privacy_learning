package com.privacy.web.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.privacy.web.repository.FavolaRepository;

@Controller		
public class HomeControl {
@Autowired
private FavolaRepository favRep;
	@GetMapping("/homepage")
	public String home(Model model) {
		System.out.println(favRep.findAll());
		return "HomePage";
	}
	
	@GetMapping("/login")
	public String login(Model model) {
		return "Login";
	}
	
	@GetMapping("/registrazione")
	public String registrazione(Model model)
	{
		return  "forward:/utente/registrati";
	}
}
