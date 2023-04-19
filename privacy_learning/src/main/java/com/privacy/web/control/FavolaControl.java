package com.privacy.web.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.privacy.web.model.Favola;
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
		model.addAttribute("tutteFavole",favSer.findAllFavole());
		return "ListaAllFavole";
	}
	
	@GetMapping("/favolaView/{id}")
	public String favolaSingola(@PathVariable int id, Model model) {
		model.addAttribute("favola", favSer.findById(id));
		model.addAttribute("ultimoId", favSer.findByLastId());
		return "FavolaView";
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
