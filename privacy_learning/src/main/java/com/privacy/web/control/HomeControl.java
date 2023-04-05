package com.privacy.web.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.privacy.web.repository.ArgomentoStudioRepository;
import com.privacy.web.repository.FavolaRepository;
import com.privacy.web.service.UtenteService;

@Controller		
public class HomeControl {
@Autowired
private UtenteService utRep;
	@GetMapping("/prova")
	public String prova(Model model) {
		model.addAttribute("allUtenti", utRep.findAll());
		System.out.println(utRep.findAll());
		return "prova";
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
	

@Autowired
private ArgomentoStudioRepository argRep;
	@GetMapping("/homepage")
	public String argomenti(Model model) {
		System.out.println(argRep.findAll());
		return "HomePage";
	}
}
