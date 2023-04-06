package com.privacy.web.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.privacy.web.model.Favola;
import com.privacy.web.service.FavolaService;
import com.privacy.web.serviceImpl.FavolaServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/favole")
public class FavolaControl {
	@Autowired
	private FavolaServiceImpl favSer;
	
	
	
	@GetMapping("/leggi-una-favola") //pagina in cui ci sono tutte le favole
	public String home(Model model) {
		model.addAttribute(favSer.findAllFavole());
		return "Favola";
	}
	
	@GetMapping("/favoleHome")
	public ModelAndView visualizzaArgomenti(@ModelAttribute("listaFavoleHome") HttpServletRequest request, HttpServletResponse resp) {
		List<Favola> favoleHome= favSer.findAllFavole();
		for(Favola list : favoleHome) {
			if(list.getId_favola() > 3) favoleHome.remove(list);
		}
	return new ModelAndView("HomePage", "listaFavoleHome", favoleHome);
}
}
