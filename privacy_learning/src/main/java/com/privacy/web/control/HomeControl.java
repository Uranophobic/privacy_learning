package com.privacy.web.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.privacy.web.service.ArgomentoStudioService;
import com.privacy.web.service.ArticoloService;
import com.privacy.web.service.FavolaService;
import com.privacy.web.service.UtenteService;

@Controller
public class HomeControl {
	@Autowired
	private UtenteService utServ;
	@Autowired
	private FavolaService favServ;
	@Autowired
	private ArticoloService artServ;
	@Autowired
	private ArgomentoStudioService argRep;
	
	
//	@GetMapping("/prova")
//	public String prova(Model model) {
////		model.addAttribute("allUtenti", utServ.findAll());
////		System.out.println(utServ.findAll());
//		return "niente";
//	}

	@GetMapping("/login")
	public String login(Model model) {
		return "Login";
	}
	
	  
	  @PostMapping("/login") 
	  public String loginSession (Model model) { return
	  "redirect:/profilo"; }
	 
	@GetMapping("/registrazione")
	public String registrazione(Model model)
	{
		return  "forward:/utente/registrati";
	}

	

	@GetMapping("/homepage")
	public String argomenti(Model model) {
		model.addAttribute("argomentiView", argRep.findAllArgomenti());
		model.addAttribute("tutteFavole", favServ.findAllFavole());
		model.addAttribute("tuttiArticoli", artServ.findAllArticoli());
		// System.out.println(argRep.findAll());
		return "HomePage";
	}

	@GetMapping("/profilo")
	public String profilo(Model model) {
		return "profilo";
	}

}
