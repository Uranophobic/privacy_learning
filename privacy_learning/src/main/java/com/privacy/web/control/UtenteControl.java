package com.privacy.web.control;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.privacy.web.model.Utente;
import com.privacy.web.utils.Check;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
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

	//metodo che inoltra alla pagina privacy
	@GetMapping("/privacy")
	public String privacy(Model model) {
		return "privacy";
	}
	
	@PostMapping("/registrati")
	public String saveUtente(@ModelAttribute("user") Utente user, HttpServletRequest request,
			HttpServletResponse response, Model model, HttpSession userSession) throws Exception {
		if (Check.checkName(request.getParameter("nome")) 
				&& Check.checkSurname(request.getParameter("cognome"))
				&& Check.checkEmail(request.getParameter("email"))) {
			user.setNome(request.getParameter("nome"));
			user.setCognome(request.getParameter("cognome"));
			user.setEmail(request.getParameter("email"));
			user.setPassword(request.getParameter("password"));
			try {
				if (utServ.existsById(request.getParameter("email"))) {
					response.getWriter().write("4 // errore nella registrazione");
					String error = "Esiste già un utente con questa e-mail";
					model.addAttribute("descrizione", error);
					return "redirect:/error?descrizione= "+error;
	/*			} else {
					ArrayList<String> risposteArrayList = new ArrayList<>();
					for (int i = 1; i <= domServ.countDomandeByIdTest(4); i++) {
						//potrebbe non servire se si inserisce il required nella pagina  html
						if (request.getParameter("valore").isEmpty()) {
							String error = "mancata risposta alla domanda n: " + i;
							model.addAttribute("descrizione", error);
							return "redirect:/registrazione?error=";
						}
						risposteArrayList.add(request.getParameter("valore"));// id della risposta i
					}
					for (int i = 0; i < risposteArrayList.size(); i++) {
						salvataggioServ.save(new Salvataggio(4, user.getEmail(), risposteArrayList.get(i)));
					} */
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			utServ.saveUser(user);
			userSession.setAttribute("userSession", user.getEmail());
			
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

	@PostMapping("/login")
	public String sessioneUtente(@ModelAttribute("user") Utente user, HttpServletRequest request,
			HttpServletResponse response, Model model, HttpSession userSession) throws Exception {
		
		if(userSession.getAttribute("user")!=null) {//"sessione già esistente"
			return "forward:/profilo";
		}
		String email=request.getParameter("email");
		String pwd= request.getParameter("password");
		if (email != null && pwd != null) {
            if (email.trim().length() == 0) {
                response.getWriter().write("1"); //email vuota
            }
            if (pwd.trim().length() == 0) {
                response.getWriter().write("2"); //password vuota
            }
      try {
    	  user = utServ.findUtenteByEmailAndPassword(email, pwd);
		if (user!=null) {
			userSession.setAttribute("userSession", user);
			return "redirect:/profilo";
		}
		else  {
		response.getWriter().write("4: utente non valido");
		String error = "Email o password errata";
		model.addAttribute("descrizione", error);
		return "redirect:/error?descrizione= "+error;
		}
      } catch (Exception e) {
		e.printStackTrace();
	}
		}
		String error="email o password null";
    	  return "redirect:/login?errorDesc="+error; 
	}
	
	@GetMapping("/users/{id}")
	public String eliminaUtente(@PathVariable String email, Model model) {
		utServ.deleteById(email);
		return "redirect:/users/all";
	}

}