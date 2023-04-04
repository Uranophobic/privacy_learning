package com.privacy.web.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.privacy.web.repository.FavolaRepository;

@Controller
@RequestMapping("/favole")
public class FavolaControl {
	@Autowired
	private FavolaRepository favRep;
	
	
	
	@GetMapping("/leggi-una-favola") //pagina in cui ci sono tutte le favole
	public String home(Model model) {
		System.out.println(favRep.findAll());
		return "Favola";
	}
	
	
}
