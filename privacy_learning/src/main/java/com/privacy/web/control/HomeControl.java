package com.privacy.web.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.privacy.web.serviceImpl.ArgomentoStudioServiceImpl;
import com.privacy.web.serviceImpl.ArticoloServiceImpl;
import com.privacy.web.serviceImpl.FavolaServiceImpl;
import com.privacy.web.serviceImpl.UtenteServiceImpl;

@Controller		
public class HomeControl {
@Autowired
private UtenteServiceImpl utServ;
@Autowired
private FavolaServiceImpl favServ;
@Autowired
private ArticoloServiceImpl artServ;

	@GetMapping("/prova")
	public String prova(Model model) {
		model.addAttribute("allUtenti", utServ.findAll());
		System.out.println(utServ.findAll());
		return "prova";
	}
	
	@GetMapping("/login")
	public String login(Model model) {
		return "Login";
	}
	
//	@GetMapping("/registrazione")
//	public String registrazione(Model model)
//	{
//		return  "forward:/utente/registrati";
//	}
	

@Autowired
private ArgomentoStudioServiceImpl argRep;
	@GetMapping("/homepage")
	public String argomenti(Model model) {
		model.addAttribute("argomentiView", argRep.findAllArgomenti());
		model.addAttribute("tutteFavole", favServ.findAllFavole());
		model.addAttribute("tuttiArticoli", artServ.findAllArticoli());
		//System.out.println(argRep.findAll());
		return "HomePage";
	}
}
