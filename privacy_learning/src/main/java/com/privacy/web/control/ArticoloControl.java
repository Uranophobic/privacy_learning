package com.privacy.web.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.privacy.web.service.ArticoloService;

@Controller
@RequestMapping("/article")
public class ArticoloControl {

	@Autowired
	ArticoloService artSer;
	
	@GetMapping("/leggi-un-articolo") //pagina in cui ci sono tutti gli articoli
	public String home(Model model) {
		model.addAttribute("tuttiArticoli",artSer.findAllArticoli());
		return "ListaAllArticoli";
	}
}
