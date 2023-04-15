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

import com.privacy.web.model.ArgomentoStudio;
import com.privacy.web.serviceImpl.ArgomentoStudioServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/argomenti")
public class ArgomentoStudioControl {
	@Autowired
	private ArgomentoStudioServiceImpl argService;

	// pagina in cui ci sono tutti gli argomenti studio
	@GetMapping("/studia-con-noi")
	public String argomenti(Model model) {
		model.addAttribute("argomentiView", argService.findAllArgomenti());
		return "ListaAllArgomenti";
	}

	@GetMapping("/argomentiHome")
	public ModelAndView visualizzaArgomenti(@ModelAttribute("listaArgomenti") HttpServletRequest request,
			HttpServletResponse resp) {
		List<ArgomentoStudio> argomentiHome = argService.findAllArgomenti();
		for (ArgomentoStudio list : argomentiHome) {
			if (list.getId_studio() > 3)
				
				argomentiHome.remove(list);
		}
		return new ModelAndView("HomePage", "listaArgomenti", argomentiHome);
	}
	
	//metodo che visualizza l'argomento nel dettaglio
	@GetMapping("/argomentoView/{id}")
	public String favolaSingola(@PathVariable int id, Model model) {
		model.addAttribute("argomento", argService.findById(id));
		model.addAttribute("ultimoId", argService.findByLastId());
		return "ArgomentoView";
	}
}