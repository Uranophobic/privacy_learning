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
import com.privacy.web.utils.Check;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;

import com.privacy.web.service.DomandaService;
import com.privacy.web.service.SalvataggioService;
import com.privacy.web.service.TestService;
import com.privacy.web.service.UtenteService;
import com.privacy.web.serviceImpl.SalvataggioServiceImpl;
import com.privacy.web.serviceImpl.TestServiceImpl;
import com.privacy.web.serviceImpl.UtenteServiceImpl;

@Controller
@RequestMapping("/users")
@Data
public class UtenteControl {
	@Autowired
	private SalvataggioService salvataggioServ; // oggetto SalvataggioRepository
	@Autowired
	private TestService testServ;
	@Autowired
	private UtenteService utServ;
	@Autowired
	private DomandaService domServ;

	public UtenteControl(UtenteServiceImpl utServ, SalvataggioServiceImpl salvataggioRep, TestServiceImpl testServ) {
		super();
		this.utServ = utServ;
		this.salvataggioServ = salvataggioRep;
		this.testServ = testServ;
	}

	// metodo che prende la lista di tutti gli utenti e ritorna una model and view
	@GetMapping("/all") // tutti gli utenti
	public String listUser(Model model) {
		model.addAttribute("utenti", utServ.findAll());
		return "ListaAllUser";
	}

	// metodo che crea un nuovo utente
	@GetMapping("/registrazione")
	public String newUser(Model model) {

		// crea un nuovo utente
		Utente user = new Utente();
		model.addAttribute("user", user);
		return "create_user";
	}

	@PostMapping("/all")
	public String saveUtente(@ModelAttribute("user") Utente user, HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		if (Check.checkName(request.getParameter("nome")) 
				&& Check.checkSurname(request.getParameter("cognome"))
				&& Check.checkEmail(request.getParameter("email"))) {
			user.setNome(request.getParameter("nome"));
			user.setCognome(request.getParameter("cognome"));
			user.setEmail(request.getParameter("email"));
			user.setPassword(request.getParameter("pwd"));
			try {
				if (utServ.existsById(request.getParameter("email"))) {
					response.getWriter().write("4 // errore nella registrazione");
					String error = "Esiste già un utente con questa e-mail";
					model.addAttribute("descrizione", error);
					return "redirect:/error";
				} else {
					ArrayList<String> risposteArrayList = new ArrayList<>();
					for (int i = 1; i <= domServ.countDomandeByIdTest(4); i++) {
						if (request.getParameter("r" + i).isEmpty()) {
							String error = "mancata risposta alla domanda n: " + i;
							model.addAttribute("descrizione", error);
							return "redirect:/registrazione";
						}
						risposteArrayList.add(request.getParameter("r" + i));// id della risposta i
					}
					for (int i = 0; i < risposteArrayList.size(); i++) {
						salvataggioServ.save(new Salvataggio(4, user.getEmail(), risposteArrayList.get(i)));
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			utServ.saveUser(user);

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
			model.addAttribute("descrizione", descrizione);
			return "redirect:/error";
		}
		return "redirect:/homepage";
	}

	/**
	 * Questo metodo richiama il metodo post
	 *
	 * @param model, variabile model
	 * @param user   , variabile utente
	 * @throws IOException errore input output
	 */

//	@GetMapping("/registrati")
//	public ModelAndView registrati(Model model) throws IOException{
//		return new ModelAndView("registrazione", "registrazione", new Utente());
//	}
//	/**
//     * Questo metodo controlla le operazioni per effettuare una registrazione
//     *
//     * @param request  , request
//     * @param response , response
//     * @param user	   , variabile utente
//     * @throws IOException      errore input output
//     * @return ModelAndView un modello con parametri: nome della View da renderizzare, nome del modello, oggetto utente creato
//     */
//	@PostMapping("/registrazione")
//	public ModelAndView registrati(@ModelAttribute("registrazione") @RequestBody Utente user, HttpServletRequest request, HttpServletResponse response) throws IOException {
//		
//		if (Check.checkName(request.getParameter("nome")) && Check.checkSurname(request.getParameter("cognome")) && Check.checkEmail(request.getParameter("email"))) {
//			user.setNome(request.getParameter("nome"));
//			user.setCognome(request.getParameter("cognome"));
//			user.setEmail(request.getParameter("email"));
//			user.setPassword(request.getParameter("pwd"));
//			try {
//				if(utRep.existsById(request.getParameter("email"))==false) {
//					ArrayList<String> risposteArrayList= new ArrayList<>();
//					for(int i=1;i<=testRep.findNDomandeById(4);i++) { //popolo l'array di risposte date con id_test 4("conoscitivo")
//						risposteArrayList.add(request.getParameter("r"+i)); //id della risposta i
//				}
//					for(int i=0;i<risposteArrayList.size();i++) {
//						salvataggioRep.save(new Salvataggio(4, user.getEmail(), risposteArrayList.get(i)));
//					}
//					response.getWriter().write("5"); //registrazione avvenuta con successo
//				} else {
//                    response.getWriter().write("4"); // errore nella registrazione
//                    String error = "Esiste già un utente con questa e-mail";
//                    response.sendRedirect("./templates/Registrazione.html?error=" + error);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//			
//		} else {
//			if (!Check.checkName(request.getParameter("nome"))) {
//				response.getWriter().write("1: nome non corretto"); 
//			}
//			if (!Check.checkSurname(request.getParameter("cognome"))) {
//				response.getWriter().write("2: cognome non corretto"); 
//			}
//			if (!Check.checkEmail(request.getParameter("email"))) {
//				response.getWriter().write("3: email non corretta"); 
//			}
//			String descrizione = "Siamo spiacenti si è verificato un errore con la registrazione. Riprova!";
//			response.sendRedirect(descrizione);
//		}
//		utRep.save(user);
//		return new ModelAndView("login", "registrazione", new Utente());
//	}

}