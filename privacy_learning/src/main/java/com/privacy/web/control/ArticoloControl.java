package com.privacy.web.control;



import java.util.List;

import org.htmlunit.html.HtmlPage;
import org.htmlunit.*;
import org.htmlunit.html.HtmlTitle;
import org.htmlunit.html.HtmlMeta;
import org.htmlunit.html.DomElement;
import org.htmlunit.html.HtmlImage;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.privacy.web.model.Articolo;
import com.privacy.web.service.ArticoloService;

@Controller
@RequestMapping("/article")
public class ArticoloControl {

	@Autowired
	ArticoloService artSer;
	
	@GetMapping("/leggi-un-articolo") //pagina in cui ci sono tutti gli articoli
	public String home(Model model) {
		
		List<Articolo> allArticoli = artSer.findAllArticoli();
		model.addAttribute("tuttiArticoli", allArticoli);
	
		return "ListaAllArticoli";
	}
}
