package com.privacy.web.control;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.privacy.web.model.ArgomentoStudio;
import com.privacy.web.model.Salvataggio;
import com.privacy.web.model.Utente;
import com.privacy.web.service.ArgomentoStudioService;
import com.privacy.web.service.ArticoloService;
import com.privacy.web.service.FavolaService;
import com.privacy.web.service.ProgressoService;
import com.privacy.web.service.SalvataggioService;
import com.privacy.web.service.UtenteService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeControl {
	@Autowired
	private UtenteService utServ;
	@Autowired
	private FavolaService favServ;
	@Autowired
	private ArticoloService artServ;
	@Autowired
	private SalvataggioService salvServ;
	@Autowired
	private ArgomentoStudioService argServ;
	@Autowired
	private ProgressoService progServ;

	
	@GetMapping("/icon")
	public String icon(Model model) {
		return "icon-prove";
	}
	
	@GetMapping("/scroll")
	public String scroll(Model model) {
		return "scrollpage";
	}

	@GetMapping("/login")
	public String login(Model model) {
		return "Login";
	}

	@PostMapping("/login")
	public String loginSession(Model model) {
		return "forward:/profilo";
	}

	@GetMapping("/registrazione")
	public String registrazione(Model model) {
		return "forward:/utente/registrati";
	}

	@GetMapping("/homepage")
	public String argomenti(Model model, HttpSession session) {
		model.addAttribute("argomentiView", argServ.findAllArgomenti());
		model.addAttribute("tutteFavole", favServ.findAllFavole());
		model.addAttribute("tuttiArticoli", artServ.findAllArticoli());
		// System.out.println(argServ.findAll());
		return "HomePage";
	}

	@GetMapping("/profilo/{email}")
	public String profilo(@PathVariable String email, @ModelAttribute("user") Utente user, HttpServletRequest request,
			HttpServletResponse response, Model model, HttpSession userSession) throws Exception {

		System.out.println(email);
		Utente u = utServ.findUtenteByEmail(email);

		if (u != null) { // qui è tutto ok

			// devi settare la percentuale
			if (u.getPercentuale() != 0) {
				int diff = 100 - u.getPercentuale();
				model.addAttribute("perc", u.getPercentuale());
				model.addAttribute("diff", diff);
			}

			// la sessione
			userSession.setAttribute("userSession", u);
		
		}
		
		

		// ed eventuali argomenti da studare

		model.addAttribute("argDaStudiare", progServ.findByEmail(u.getEmail()));
		return "profilo";
	}
}
