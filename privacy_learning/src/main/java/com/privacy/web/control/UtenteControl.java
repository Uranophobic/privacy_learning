package com.privacy.web.control;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.privacy.web.model.Utente;
import com.privacy.web.repository.UtenteRepository;
import com.privacy.web.utils.Check;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;

@Controller
@RequestMapping("/utente")
@Data
public class UtenteControl {
	@Autowired
	private UtenteRepository utRep; // oggetto utenteRepository

	@GetMapping("/registrati")
	public ModelAndView registrati() throws ServletException, IOException{
		return new ModelAndView("Registrazione", "registrazione", new Utente());
	}

	@PostMapping("/registrazione")
	public ModelAndView registrati(@ModelAttribute("registrazione") @RequestBody Utente user, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (Check.checkName(request.getParameter("nome")) && Check.checkSurname(request.getParameter("cognome"))
				&& Check.checkEmail(request.getParameter("email"))) {
			user.setNome(request.getParameter("nome"));
			user.setCognome(request.getParameter("cognome"));
			user.setEmail(request.getParameter("email"));
			user.setPassword(request.getParameter("pwd"));
		} else {
			if (!Check.checkName(request.getParameter("nome"))) {
				response.getWriter().write("1"); // nome non corretto
			}
			if (!Check.checkSurname(request.getParameter("cognome"))) {
				response.getWriter().write("2"); // cognome non corretto
			}
			if (!Check.checkEmail(request.getParameter("email"))) {
				response.getWriter().write("3"); // email non corretto
			}
			String descrizione = "Siamo spiacenti si è verificato un errore con la registrazione. Riprova!";
			response.sendRedirect(descrizione);
		}
		utRep.save(user);
		return new ModelAndView("login", "registrazione", new Utente());
	}
}
