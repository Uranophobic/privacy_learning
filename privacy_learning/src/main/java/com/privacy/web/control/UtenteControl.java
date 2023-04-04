package com.privacy.web.control;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.privacy.web.model.Salvataggio;
import com.privacy.web.model.Utente;
import com.privacy.web.repository.UtenteRepository;
import com.privacy.web.utils.Check;

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
	public ModelAndView registrati(Model model) throws IOException{
		return new ModelAndView("registrazione", "registrazione", new Utente());
	}

	@PostMapping("/registrazione")
	public ModelAndView registrati(@ModelAttribute("registrazione") @RequestBody Utente user, @RequestBody Salvataggio salvataggio, HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		if (Check.checkName(request.getParameter("nome")) && Check.checkSurname(request.getParameter("cognome")) && Check.checkEmail(request.getParameter("email"))) {
			
			user.setNome(request.getParameter("nome"));
			user.setCognome(request.getParameter("cognome"));
			user.setEmail(request.getParameter("email"));
			user.setPassword(request.getParameter("pwd"));
			System.out.println(user.getEmail());
			try {
				Utente utente= utRep.login(user.getEmail(), user.getPassword());
				boolean risultato= utRep.existsById(request.getParameter("email"));
				
				System.out.println("ho il risultato di esistenza ed è: " +risultato);
				if(risultato=false) {
					request.getSession().setAttribute("user", utente);
					 response.getWriter().write("5"); //registrazione avvenuta con successo
				} else {
                    response.getWriter().write("4"); // errore nella registrazione
                    String error = "Esiste già un utente con questa e-mail";
                    response.sendRedirect("./templates/Registrazione.html?error=" + error);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
			salvataggio.setEmail_utente(request.getParameter("email"));
			salvataggio.setIdTest(4); //sono test conoscitivo con id 4
			salvataggio.setRisposta(request.getParameter("risposta"));
		} else {
			if (!Check.checkName(request.getParameter("nome"))) {
				response.getWriter().write("1: nome non corretto"); 
			}
			if (!Check.checkSurname(request.getParameter("cognome"))) {
				response.getWriter().write("2: cognome non corretto"); 
			}
			if (!Check.checkEmail(request.getParameter("email"))) {
				response.getWriter().write("3: email non corretta"); 
			}
			String descrizione = "Siamo spiacenti si è verificato un errore con la registrazione. Riprova!";
			response.sendRedirect(descrizione);
		}
		utRep.save(user);
		
		
		return new ModelAndView("login", "registrazione", new Utente());
	}
	
}
