package com.privacy.web.control;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.privacy.web.model.ArgomentoStudio;
import com.privacy.web.model.Utente;
import com.privacy.web.repository.ArgomentoStudioRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/argomenti")
public class ArgomentoStudioControl {
	@Autowired
	private ArgomentoStudioRepository argRep;
	
	
	
	@GetMapping("/studia-con-noi") //pagina in cui ci sono tutti gli argomenti studio
	public String argomenti(Model model) {
		System.out.println(argRep.findAll());
		return "argomento";
	}
	
	@GetMapping("/argomentiHome")
	public ModelAndView visualizzaArgomenti(@ModelAttribute("listaArgomenti") ArgomentoStudio argomento, HttpServletRequest request, HttpServletResponse resp) {
		List<ArgomentoStudio> argomentiHome = argRep.findAll();
	return new ModelAndView("HomePage", "listaArgomenti", argomentiHome );
}
}