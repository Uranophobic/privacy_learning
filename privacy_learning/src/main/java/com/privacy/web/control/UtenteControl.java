package com.privacy.web.control;

import java.io.IOException;
import java.util.ArrayList;

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
import com.privacy.web.repository.SalvataggioRepository;
import com.privacy.web.repository.TestRepository;
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
	@Autowired
	private SalvataggioRepository salvataggioRep; //oggetto SalvataggioRepository
	@Autowired
	private TestRepository testRep;
	
	/**
     * Questo metodo richiama il metodo post
     *
     * @param model, variabile model
     * @param user , variabile utente
     * @throws IOException      errore input output
     */

	@GetMapping("/registrati")
	public ModelAndView registrati(Model model) throws IOException{
		return new ModelAndView("registrazione", "registrazione", new Utente());
	}
	/**
     * Questo metodo controlla le operazioni per effettuare una registrazione
     *
     * @param request  , request
     * @param response , response
     * @param user	   , variabile utente
     * @throws IOException      errore input output
     * @return ModelAndView un modello con parametri: nome della View da renderizzare, nome del modello, oggetto utente creato
     */
	@PostMapping("/registrazione")
	public ModelAndView registrati(@ModelAttribute("registrazione") @RequestBody Utente user, HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		if (Check.checkName(request.getParameter("nome")) && Check.checkSurname(request.getParameter("cognome")) && Check.checkEmail(request.getParameter("email"))) {
			user.setNome(request.getParameter("nome"));
			user.setCognome(request.getParameter("cognome"));
			user.setEmail(request.getParameter("email"));
			user.setPassword(request.getParameter("pwd"));
			try {
				if(utRep.existsById(request.getParameter("email"))==false) {
					ArrayList<String> risposteArrayList= new ArrayList<>();
					for(int i=1;i<=testRep.findNDomandeById(4);i++) { //popolo l'array di risposte date con id_test 4("conoscitivo")
						risposteArrayList.add(request.getParameter("r"+i)); //id della risposta i
				}
					for(int i=0;i<risposteArrayList.size();i++) {
						salvataggioRep.save(new Salvataggio(4, user.getEmail(), risposteArrayList.get(i)));
					}
					response.getWriter().write("5"); //registrazione avvenuta con successo
				} else {
                    response.getWriter().write("4"); // errore nella registrazione
                    String error = "Esiste già un utente con questa e-mail";
                    response.sendRedirect("./templates/Registrazione.html?error=" + error);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
			
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
