package com.privacy.web.control;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Date;

import org.hibernate.internal.util.compare.CalendarComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.privacy.web.model.ArgomentoStudio;
import com.privacy.web.model.Articolo;
import com.privacy.web.model.Domanda;
import com.privacy.web.model.Salvataggio;
import com.privacy.web.model.Utente;
import com.privacy.web.model.MetaInfo;
import com.privacy.web.model.ProgressoStudio;
import com.privacy.web.utils.Check;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.Data;

import com.privacy.web.service.ArgomentoStudioService;
import com.privacy.web.service.DomandaService;
import com.privacy.web.service.ProgressoService;
import com.privacy.web.service.SalvataggioService;
import com.privacy.web.service.TestService;
import com.privacy.web.service.UtenteService;

@Controller
@RequestMapping("/users")
@Data
public class UtenteControl {
	@Autowired
	private SalvataggioService salvServ;
	@Autowired
	private TestService testServ;
	@Autowired
	private UtenteService utServ;
	@Autowired
	private DomandaService domServ;
	@Autowired
	private ProgressoService progServ;
	@Autowired
	private ArgomentoStudioService argServ;

	/*--------REGISTRAZIONE--------*/

	@GetMapping("/registrazione")
	public String prova(Model model) {
		List<Domanda> questionario = domServ.findByIdTest(0);
		List<Domanda> prima = split(questionario);
		List<Domanda> seconda = split2(questionario);

		Utente user = new Utente();

		model.addAttribute("user", user);
		model.addAttribute("questionario1", prima);
		model.addAttribute("questionario2", seconda);
		return "registrazione";
	}

	@PostMapping("/registrazione")
	public String provarisposte(@ModelAttribute("user") Utente user, HttpServletRequest request,
			HttpServletResponse response, Model model, HttpSession userSession) throws Exception {
		if (Check.checkName(request.getParameter("nome")) && Check.checkSurname(request.getParameter("cognome"))
				&& Check.checkEmail(request.getParameter("email"))) {
			user.setNome(request.getParameter("nome"));
			user.setCognome(request.getParameter("cognome"));
			user.setEmail(request.getParameter("email"));
			user.setPassword(request.getParameter("password"));
			SimpleDateFormat dtf = new SimpleDateFormat("yyyy/MM/dd");
			Date today = Calendar.getInstance().getTime();
			try {
				if (dtf.format(today).compareTo(request.getParameter("dataNascita")) <= 0) {
					String descrizione = "La data inserita è invalida";
					model.addAttribute("descrizione", descrizione);
					return "redirect:/users/reg?error=" + descrizione;
				} else {
					user.setDataNascita(request.getParameter("dataNascita"));
				}
				if (utServ.existsById(request.getParameter("email"))) {
					response.getWriter().write("4 // errore nella registrazione");
					String error = "Esiste già un utente con questa e-mail";
					model.addAttribute("descrizione", error);
					return "redirect:/error?descrizione= " + error;

				} else {
					// salvo l'utente nel database
					user.setLivello("Nessuno");
					utServ.save(user);

					List<Domanda> questionario = domServ.findByIdTest(0);

					for (Domanda q : questionario) {
						// mi salvo ogni risposta al questionario che ha dato
						Salvataggio s = new Salvataggio();
						s.setEmail_utente(user.getEmail());
						s.setId_test(0);
						s.setId_domanda(q.getId_domanda());
						String risp = request.getParameter("valore" + q.getId_domanda());
						if (risp.equals("Mai")) {
							s.setId_risposta(1);
						} else if (risp.equals("Raramente")) {
							s.setId_risposta(2);
						} else if (risp.equals("Spesso")) {
							s.setId_risposta(3);
						} else if (risp.equals("Sempre")) {
							s.setId_risposta(4);
						} else if (risp.equals("Si")) {
							s.setId_risposta(1);
						} else if (risp.equals("No")) {
							s.setId_risposta(2);
						}
						// mi salvo anche le info della domanda
						s.setRisposta_corretta("0");
						s.setRisposta_utente(risp);
						s.setTesto_domanda(q.getTesto());
						s.setMeta_info(q.getMeta_info());
						salvServ.save(s);
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
		userSession.setAttribute("userSession", user);
		return "profilo";
	}

	@PostMapping("/all")
	public String saveUtente(@ModelAttribute("user") Utente user) {
		try {
			utServ.save(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/homepage";
	}

	@GetMapping("/allUser")
	public String allUser(Model model) {
		model.addAttribute("allUtenti", utServ.findAll());
		return "utenti";
	}

	// metodo che inoltra alla pagina privacy
	@GetMapping("/privacy")
	public String privacy(Model model) {
		return "privacy";
	}

	/*-----------------------------------PROFILO------------------------------------------*/
	@PostMapping("/profilo")
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

				if (user != null) { // ok

					// devi settare la percentuale
					if (user.getPercentuale() != 0) {
						int diff = 100 - user.getPercentuale();
						model.addAttribute("perc", user.getPercentuale());
						model.addAttribute("diff", diff);
					}

					// la sessione
					userSession.setAttribute("userSession", user);

					// ed eventuali argomenti da studare
					model.addAttribute("argDaStudiare", progServ.findByEmail(user.getEmail()));
					return "profilo";

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

	@GetMapping("/modifica")
	public String modifica(Model model) {
		// mi serve solo per aprire modifica utente
		return "editUtente";
	}

	@PostMapping("/modifica/{email}")
	public String updateUser(@ModelAttribute("user") Utente user, HttpServletRequest request,
			HttpServletResponse response, Model model, HttpSession userSession) throws Exception {

		Utente u = utServ.findUtenteByEmail(user.getEmail());

		// controllo che il campo non sia vuoto, perchè se è vuoto vuol dire che
		// l'utente non vuole cambiarlo

		if (!request.getParameter("cognome").equals("")) {
			u.setCognome(request.getParameter("cognome"));
		}

		if (!request.getParameter("nome").equals("")) {
			u.setNome(request.getParameter("nome"));
		}

		if (!request.getParameter("password").equals("")) {
			u.setPassword(request.getParameter("password"));
		}

		SimpleDateFormat dtf = new SimpleDateFormat("yyyy/MM/dd");
		Date today = Calendar.getInstance().getTime();
		if (dtf.format(today).compareTo(request.getParameter("dataNascita")) <= 0) {
			String descrizione = "La data inserita è invalida";
			model.addAttribute("descrizione", descrizione);
			return "redirect:/users/reg?error=" + descrizione;
		} else {
			if (!request.getParameter("dataNascita").equals("")) {
				u.setDataNascita(request.getParameter("dataNascita"));
			}

		}

		utServ.save(u);
		userSession.setAttribute("userSession", u);

		return "redirect:/profilo/{email}";
	}

	/* pag lezioni */
	@GetMapping("/profilo/lezioni-private/{email}")
	public String lezioni(@PathVariable String email, @ModelAttribute("user") Utente user, HttpServletRequest request,
			HttpServletResponse response, Model model, HttpSession userSession) throws Exception {

		System.out.println("SONO NEL GET MAPPING DELLE LEZIONI");
		Utente u = utServ.findUtenteByEmail(email);
		
		userSession.setAttribute("userSession", u);
		System.out.println("utente sessione  " + userSession + " utnete " + u.toString());
		 
		List<ProgressoStudio> allprog = progServ.findByEmail(email);
		model.addAttribute("allprog", allprog);
		
		/*
		 * List<ArgomentoStudio> argDaStudiare =
		 * argServ.findAllArgDaStudiare(email); model.addAttribute("argDaStudiare",
		 * argDaStudiare); System.out.println("arg da studiare  utente " +
		 * argDaStudiare); List<ProgressoStudio> progUtente =
		 * progServ.findByEmail(email); model.addAttribute("progUtente", progUtente);
		 * 
		 * System.out.println(" aaaaaaaaaaaaaaaaaaaaaa prog utente " + progUtente);
		 * 
		 * List<ArgomentoStudio> argNoStudy = argServ.findArgomentiNoStudy(email);
		 * model.addAttribute("argNoStudy", argNoStudy);
		 */

		return "lezioni";
	}

	/* pagina studio lezione */

	@GetMapping("/profilo/lezioni-private/{email}/{titolo}")
	public String progresso(@PathVariable String email, @PathVariable String titolo, @ModelAttribute("user") Utente user,
			HttpServletRequest request, HttpServletResponse response, Model model, HttpSession userSession)
			throws Exception {

		Utente u = utServ.findUtenteByEmail(email);
		userSession.setAttribute("userSession", u);

		ArgomentoStudio arg = argServ.findArgomentoByTitolo(titolo);
		model.addAttribute("arg", arg);

		return "studiolezione";
	}

	@PostMapping("/profilo/lezioni-private/{email}/{titolo}")
	public String lezione_studiata(Model model, @PathVariable String email, @PathVariable String titolo,
			@ModelAttribute("user") Utente user, HttpServletRequest request, HttpServletResponse response,
			HttpSession userSession) throws Exception {

		//7System.out.println("prova prova sa sa " + email);
		//System.out.println("email nel path:" + email);
		//System.out.println("titolo nel path: " + titolo);

		List<ProgressoStudio> prog = progServ.findByEmail(email);
		//System.out.println("progressi utente" + prog);

		ArgomentoStudio argStudiato = argServ.findArgomentoByTitolo(titolo);
		model.addAttribute("arg", argStudiato);
		//System.out.println("argo studiato" + argStudiato);

		ProgressoStudio p = progServ.findByEmailAndArgomento(email, titolo);
		p.setArg_studiato(true);
		progServ.save(p);
		
		Utente u = utServ.findUtenteByEmail(email);
		
		userSession.setAttribute("userSession", u);
		//System.out.println("utente sessione  POST " + userSession + " utnete " + u.toString());
		 
		List<ProgressoStudio> allprog = progServ.findByEmail(email);
		model.addAttribute("allprog", allprog);
		return "lezioni";
	}

	@GetMapping("/delete/{id}") //// ??????? (commento di alessia) non credo che questo debba stare qui
	public String eliminaUtente(@PathVariable String id, Model model) {
		utServ.deleteById(id);
		return "redirect:/users/all";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/homepage";
	}

	/*------------------------------------METODI INTERNI-------------------------------*/
	// Metodo che prende solo la prima parte delle domande del questionario utente
	public static List<Domanda> split(List<Domanda> list) {
		List<Domanda> first = new ArrayList<Domanda>();
		for (int i = 0; i < (list.size() + 1) / 2; i++) {
			first.add(list.get(i));
		}
		// restituisce un array che contiene la prima parte delle domande
		return first;
	}

	// Metodo che prende solo la seconda parte delle domande del questionario utente
	public static List<Domanda> split2(List<Domanda> list) {
		List<Domanda> second = new ArrayList<Domanda>();
		for (int i = ((list.size() + 1) / 2); i < list.size(); i++) {
			second.add(list.get(i));
		}
		// restituisce un array con la seconda parte delle domande
		return second;
	}
}
