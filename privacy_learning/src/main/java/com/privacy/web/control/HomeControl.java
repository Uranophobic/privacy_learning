package com.privacy.web.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.privacy.web.service.ArgomentoStudioService;
import com.privacy.web.service.ArticoloService;
import com.privacy.web.service.FavolaService;
import com.privacy.web.service.UtenteService;

@Controller		
public class HomeControl {
@Autowired
private UtenteService utSer;
@Autowired
private FavolaService favSer;	
@Autowired
private ArgomentoStudioService argSer;
@Autowired
private ArticoloService artSer;


	@GetMapping("/prova")
	public String prova(Model model) {
		model.addAttribute("allUtenti", utSer.findAll());
		System.out.println(utSer.findAll());
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

	@GetMapping("/homepage")
	public String argomenti(Model model) {
		model.addAttribute("argomentiView", argSer.findAllArgomenti());
		model.addAttribute("tutteFavole", favSer.findAllFavole());
		model.addAttribute("tuttiArticoli", artSer.findAllArticoli());
		return "homepage";
	}
}
