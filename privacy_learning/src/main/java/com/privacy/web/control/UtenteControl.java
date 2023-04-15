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
import org.springframework.web.bind.annotation.RequestMapping;

import com.privacy.web.model.Articolo;
import com.privacy.web.model.Domanda;
import com.privacy.web.model.Salvataggio;
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

	public UtenteControl(UtenteService utServ, SalvataggioService salvataggioRep, TestService testServ) {
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

	/*--------REGISTRAZIONE--------*/
	// metodo che crea un nuovo utente
	@GetMapping("/registrazione")
	public String newUser(Model model) {

		// crea un nuovo utente
		Utente user = new Utente();
		model.addAttribute("user", user);
		return "create_user";
	}

	@GetMapping("/reg") // qui ci dovrebbe andasre il link della registrazioe ma non sono sicura
	public String prova(Model model) {
		List<Domanda> questionario = domServ.findByIdTest(4);
		List<Domanda> prima = split(questionario);
		List<Domanda> seconda = split2(questionario);

		// System.out.println(prima);
		// System.out.println(seconda);

		Utente user = new Utente();

		model.addAttribute("user", user);
		model.addAttribute("questionario1", prima);
		model.addAttribute("questionario2", seconda);
		return "registrazione";
	}

	@PostMapping("/reg")
	public String provarisposte(@ModelAttribute("user") Utente user, HttpServletRequest request,
			HttpServletResponse response, Model model, HttpSession userSession) throws Exception {
		if (Check.checkName(request.getParameter("nome")) && Check.checkSurname(request.getParameter("cognome"))
				&& Check.checkEmail(request.getParameter("email"))) {
			user.setNome(request.getParameter("nome"));
			user.setCognome(request.getParameter("cognome"));
			user.setEmail(request.getParameter("email"));
			user.setPassword(request.getParameter("password"));
			user.setDataNascita(request.getParameter("dataNascita"));
			try {
				if (utServ.existsById(request.getParameter("email"))) {
					response.getWriter().write("4 // errore nella registrazione");
					String error = "Esiste già un utente con questa e-mail";
					model.addAttribute("descrizione", error);
					return "redirect:/error?descrizione= " + error;

				} else {
					utServ.saveUser(user);
					userSession.setAttribute("userSession", user);
					List<Domanda> questionario = domServ.findByIdTest(4);

					ArrayList<String> risposte = new ArrayList<>();
					for (int i = 0; i < domServ.findByIdTest(4).size(); i++) {
						risposte.add(request.getParameter("valore" + questionario.get(i).getId_domanda()));

					}
					System.out.println(user.getEmail());
					Salvataggio s = new Salvataggio();
					s.setEmail_utente(user.getEmail());
					s.setId_test(4);
					int ultimo = salvataggioServ.findAllSalvataggio().size() + 1;
					for (int i = 0; i < risposte.size(); i++) {
						s.setId_salvataggio(ultimo + i);
						s.setRisposte(risposte.get(i));
						System.out.println(s);
						salvataggioServ.save(s);
					}
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
			model.addAttribute("descrizione", descrizione);
			return "redirect:/error";
		}

		return "redirect:/profilo";
	}

	@PostMapping("/all")
	public String saveUtente(@ModelAttribute("user") Utente user) {
		try {
			utServ.saveUser(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/homepage";
	}

	// metodo che inoltra alla pagina privacy
	@GetMapping("/privacy")
	public String privacy(Model model) {
		return "privacy";
	}

	/*-----------------------------------LOGIN------------------------------------------*/
	@PostMapping("/login")
	public String sessioneUtente(@ModelAttribute("user") Utente user, HttpServletRequest request,
			HttpServletResponse response, Model model, HttpSession userSession) throws Exception {

		if (userSession.getAttribute("user") != null) {// "sessione già esistente"
			return "forward:/profilo";
		}
		String email = request.getParameter("email");
		String pwd = request.getParameter("password");
		if (email != null && pwd != null) {
			if (email.trim().length() == 0) {
				response.getWriter().write("1"); // email vuota
			}
			if (pwd.trim().length() == 0) {
				response.getWriter().write("2"); // password vuota
			}
			try {
				user = utServ.findUtenteByEmailAndPassword(email, pwd);
				if (user != null) {
					userSession.setAttribute("userSession", user);
					return "redirect:/profilo";
				} else {
					response.getWriter().write("4: utente non valido");
					String error = "Email o password errata";
					model.addAttribute("descrizione", error);
					return "redirect:/error?descrizione= " + error;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		String error = "email o password null";
		return "redirect:/login?errorDesc=" + error;
	}

	@GetMapping("/delete/{id}")
	public String eliminaUtente(@PathVariable String id, Model model) {
		utServ.deleteById(id);
		return "redirect:/users/all";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/homepage";
	}

/*----------------------------------MODIFICA------------------------------------------*/
	@GetMapping("/fixedUtente/{email}")
	public String editArgomento(@PathVariable String email, Model model) {
		model.addAttribute("utente", utServ.findUtenteByEmail(email));
		return "editUtente";
	}

	@PostMapping("/modificaArticolo/{email}")
	public String updateArticolo(@PathVariable String email, @ModelAttribute("utente") Utente u, Model model,
			HttpSession userSession) {
		if (Check.checkName(u.getNome()) && Check.checkSurname(u.getCognome()) && Check.checkEmail(u.getEmail())) {

			Utente ut = utServ.findUtenteByEmail(email);
			ut.setNome(u.getNome());
			ut.setCognome(u.getCognome());
			ut.setEmail(u.getEmail());
			ut.setPassword(u.getPassword());
			utServ.deleteById(email);
			
			try {
				if (utServ.existsById(ut.getEmail())) {
					String error = "Esiste già un utente con questa e-mail";
					model.addAttribute("descrizione", error);
					return "redirect:/error?descrizione= " + error;

				} else {
					utServ.saveUser(ut);
					userSession.setAttribute("userSession", ut);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			String descrizione = "Siamo spiacenti si è verificato un errore con la registrazione. Riprova!";
			model.addAttribute("descrizione", descrizione);
			return "redirect:/error";
		}
		return "redirect:/profilo";
	}


/*------------------------------------METODI INTERNI-------------------------------*/
	// Metodo che prende solo la prima parte delle domande del questionario utente
	public static List<Domanda> split(List<Domanda> list) {
		// crea due liste vuote
		List<Domanda> first = new ArrayList<Domanda>();

		// ottieni la dimensione dell'elenco
		int size = list.size();

		// elabora ogni elemento dell'elenco e lo aggiunge al primo elenco
		// o secondo elenco in base alla sua posizione
		for (int i = 0; i < size; i++) {
			if (i < (size + 1) / 2) {
				first.add(list.get(i));
			}

		}
		// restituisce un array di elenchi per contenere entrambi gli elenchi
		return first;
	}

	// Metodo che prende solo la seconda parte delle domande del questionario utente
	public static List<Domanda> split2(List<Domanda> list) {
		// crea due liste vuote
		List<Domanda> second = new ArrayList<Domanda>();

		// ottieni la dimensione dell'elenco
		int size = list.size();

		// elabora ogni elemento dell'elenco e lo aggiunge al primo elenco
		// o secondo elenco in base alla sua posizione
		for (int i = 0; i < size; i++) {
			if (i >= (size + 1) / 2) {
				second.add(list.get(i));
			}

		}
		// restituisce un array di elenchi per contenere entrambi gli elenchi
		return second;
	}
}
