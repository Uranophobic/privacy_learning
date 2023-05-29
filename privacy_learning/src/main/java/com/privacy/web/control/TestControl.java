package com.privacy.web.control;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.privacy.web.model.ArgomentoStudio;
import com.privacy.web.model.Domanda;
import com.privacy.web.model.Salvataggio;
import com.privacy.web.model.Test;
import com.privacy.web.model.ProgressoStudio;
import com.privacy.web.model.Utente;
import com.privacy.web.service.DomandaService;
import com.privacy.web.service.ProgressoService;
import com.privacy.web.service.SalvataggioService;
import com.privacy.web.service.TestService;
import com.privacy.web.service.UtenteService;
import com.privacy.web.service.ArgomentoStudioService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.Data;

@Controller
@Data
public class TestControl {

	@Autowired
	private DomandaService domServ;
	@Autowired
	private SalvataggioService salvServ;
	@Autowired
	private UtenteService utServ;
	@Autowired
	private TestService testServ;
	@Autowired
	private ProgressoService progServ;
	@Autowired
	private ArgomentoStudioService argServ;

	public TestControl(DomandaService domServ, SalvataggioService salvServ, UtenteService utServ, TestService testServ,
			ProgressoService progServ) {
		super();
		this.domServ = domServ;
		this.salvServ = salvServ;
		this.utServ = utServ;
		this.testServ = testServ;
		this.progServ = progServ;
	}

	@GetMapping("/test/viewTest/{id}")
	public String VisualizzaTest(@PathVariable int id, Model model) {
		// restituisco la lista di domande in base al test
		model.addAttribute("domande", domServ.findByIdTest(id));
		model.addAttribute("idTest", id);
		return "allDomande";
	}

	/* ALESSIA */
	// pagina test dalla homepage
	@GetMapping("/test")
	public String test(Model model) {
		List<Test> test = testServ.findAllTest();
		List<Domanda> domBase = domServ.findByIdTest(1);
		List<Domanda> domMedio = domServ.findByIdTest(2);
		List<Domanda> domAlto = domServ.findByIdTest(3);
		/* System.out.println(testServ.findAllTest()); */
		model.addAttribute("domBase", domBase);
		model.addAttribute("domMedio", domMedio);
		model.addAttribute("domAlto", domAlto);
		model.addAttribute("test", test);
		return "test";
	}

	/* ALESSIA */
	@PostMapping("/test/{email}")
	public String saveRisp(@ModelAttribute("testuser") Domanda d, @PathVariable String email,
			HttpServletRequest request, HttpServletResponse response, Model model, HttpSession userSession)
			throws Exception {

		Utente u = new Utente();
		u = utServ.findUtenteByEmail(email);
		String livello = u.getLivello();

		// faccio ogni possibile controllo per capire in che livello siamo
		/*
		 * Nessuno -> 1 Base -> 2 Alto -> 3
		 * 
		 */
		if (livello.equals("Nessuno") || (livello.equals("Base") && u.getPercentuale() < 80)) {
			livello = "1";
			u.setLivello("Base");
		} else if (livello.equals("Base") || (livello.equals("Medio") && u.getPercentuale() < 80)) {
			livello = "2";
			u.setLivello("Medio");
		} else if (livello.equals("Medio") || (livello.equals("Alto") && u.getPercentuale() < 80)) {
			livello = "3";
			u.setLivello("Alto");
		}

		// elimino i vecchi salvataggi cosi si resetta anche la percentuale
		List<Salvataggio> allS = salvServ.findByEmail(u.getEmail());
		if (allS.size() > 0) {
			for (int i = 0; i < allS.size(); i++) {
				salvServ.delete(allS.get(i));
			}
		}

		// lo trasformo in int
		int id = Integer.parseInt(livello);

		// cerco tute le domande di quel livello
		List<Domanda> domTest = domServ.findByIdTest(id);

		/// mi vado a salvare tutte le risposte che ha dato l'utente

		for (int i = 0; i < domTest.size(); i++) {
			Salvataggio s = new Salvataggio();
			s.setEmail_utente(u.getEmail());
			s.setId_test(id);

			s.setId_domanda(domTest.get(i).getId_domanda());
			s.setTesto_domanda(domTest.get(i).getTesto());

			// mi prendo l'id della risposta scelta dall'utente
			int idRisp = Integer.parseInt(request.getParameter("valore" + domTest.get(i).getId_domanda()));
			s.setId_risposta(idRisp);

			// dopo di che mi vado a vedere se è la rispsota 1, 2, 3 oppure la 4 e me la
			// setto
			if (idRisp == 1) {
				s.setRisposta_utente(domTest.get(i).getRisposta1());
			} else if (idRisp == 2) {
				s.setRisposta_utente(domTest.get(i).getRisposta2());
			} else if (idRisp == 3) {
				s.setRisposta_utente(domTest.get(i).getRisposta3());
			} else if (idRisp == 4) {
				s.setRisposta_utente(domTest.get(i).getRisposta4());
			}

			// mi vado a prendere anche il testo della risposta corretta cosi dopo posso
			// ottenure un risultato più completo
			// nella pagina risultati (solo con l'id potevo stampare il testo della domanda
			// e risposta)

			String idRispCorretta = String.valueOf(domTest.get(i).getRisposta_corretta()).toString();

			if (!idRispCorretta.equals("")) { // se risp corretta non è vuoto allora succede questo
				if (idRispCorretta.equals("1")) {
					s.setRisposta_corretta(domTest.get(i).getRisposta1());
				} else if (idRispCorretta.equals("2")) {
					s.setRisposta_corretta(domTest.get(i).getRisposta2());
				} else if (idRispCorretta.equals("3")) {
					s.setRisposta_corretta(domTest.get(i).getRisposta3());
				} else if (idRispCorretta.equals("4")) {
					s.setRisposta_corretta(domTest.get(i).getRisposta4());
				}
			}
			// mi salvo anche la meta info della domanda (mi servirà in futuro per capire
			// quale
			// argomento deve studiare l'utente
			s.setMeta_info(domTest.get(i).getMeta_info());
			salvServ.save(s);
		}

		// ora vado a vedere effettivamente quante cose ha sbagliato l'utente
		ArrayList<Salvataggio> rispCorrette = new ArrayList<>();
		ArrayList<Salvataggio> rispInCorrette = new ArrayList<>();
		int perc = 0;

		/// mi prendo tutte le risposte salvate dell'utente (che ho letteralmente
		/// salvato prima)
		List<Salvataggio> rispSalvate = salvServ.findByEmail(email);

		// questo for mi serve per vedere quante domande ha sbagliato l'utente nella
		// pagina risultati
		for (int i = 0; i < rispSalvate.size(); i++) {

			// mi scorrro anche le domande DEL TEST
			for (Domanda domanda : domTest) {

				// mi prendo le risposte solo del test appena fatto
				if (rispSalvate.get(i).getId_test() == Integer.parseInt(livello)) {

					// se l'id della domanda nel salvataggio è uguale ad un id all'interno delle
					// domande che prima ho preso dal db
					// (sicuramente lo sarà però è necessario per capire a quale domanda mi sto
					// riferendo)
					if (rispSalvate.get(i).getId_domanda() == domanda.getId_domanda()) {

						// allora vado a controllare se la risposta salvata è uguale a quellacorretta
						if (rispSalvate.get(i).getId_risposta() == domanda.getRisposta_corretta()) {
							// e me lo salvo nelle risposte corrette
							rispCorrette.add(rispSalvate.get(i));
						} else {
							// altrimenti nelle risposte non corrette
							rispInCorrette.add(rispSalvate.get(i));

							List<ArgomentoStudio> args = argServ.findArgomentoByMeta(domanda.getMeta_info());
							for (int z = 0; z < args.size(); z++) {
								//System.out.println("FIND ARG BY META" + args + "la meta è: " + domanda.getMeta_info());
								// controllo che il progresso non sia già esistente con quel titolp
								ProgressoStudio prog = progServ.findByEmailAndArgomento(u.getEmail(),
										args.get(z).getTitolo());
								if (prog == null) {
									System.out.println(" fare un cazzo");
									ProgressoStudio p = new ProgressoStudio();
									p.setArg_dastudiare(args.get(z).getTitolo());
									p.setArg_studiato(false);
									p.setEmail_utente(email);
									p.setMeta_info(args.get(z).getMeta_info());
									////System.out.println("il progresso creato " + p);
									progServ.save(p);

								
								} else if (prog != null) {
									//System.out.println("non fare un cazzo");
									prog.setArg_studiato(false);
									progServ.save(prog);
								}
							}
						}
					}
				}
			}
		}
		perc = (100 * rispCorrette.size()) / domTest.size();

		// mi salvo l'utente e aggiorno la sessione
		u.setPercentuale(perc);
		utServ.save(u);

		// mi devo aggiornare la sessione dell'utente
		userSession.setAttribute("userSession", u);

		// mi devo passare gli argomenti da studiare
		model.addAttribute("argDaStudiare", progServ.findByEmail(u.getEmail()));

		// mi passo tutte le risposte che l'utente ha sbagliato ed ha fatto bene
		model.addAttribute("rispCorrette", rispCorrette);
		model.addAttribute("rispInCorrette", rispInCorrette);

		int diff = 100 - perc;
		model.addAttribute("perc", perc);
		model.addAttribute("diff", diff);

		return "risultati";
	}

	/* ALESSIA */
	@GetMapping("/test/{email}")
	public String risultati(@ModelAttribute("testuser") Domanda d, @PathVariable String email,
			HttpServletRequest request, HttpServletResponse response, Model model, HttpSession userSession)
			throws Exception {

		Utente u = new Utente();
		u = utServ.findUtenteByEmail(email);

		List<Salvataggio> rispTest = new ArrayList<>(); // risposte ultimo tewst che ha fatto l'utente
		rispTest = salvServ.findByMaxIdTest(email);
		int i = 0, id = rispTest.get(i).getId_test();
		List<Domanda> domTest = domServ.findByIdTest(id);

		ArrayList<Salvataggio> rispCorrette = new ArrayList<>();
		ArrayList<Salvataggio> rispInCorrette = new ArrayList<>();
		ArrayList<String> metaSugg = new ArrayList<>();

		// questo for mi serve per vedere di nuovo quante domande ha fatto bene l'utente
		// perchè cosi posso fare il pulsante nel profilo "risultati ultimo test"
		for (i = 0; i < rispTest.size(); i++) {
			for (Domanda dom : domTest) {
				if (rispTest.get(i).getId_domanda() == dom.getId_domanda()) {
					if (rispTest.get(i).getId_risposta() == dom.getRisposta_corretta()) {
						rispCorrette.add(rispTest.get(i)); // e me lo salvo nelle risposte corrette
					} else {
						rispInCorrette.add(rispTest.get(i)); // altrimenti nelle risposte non corrette
						metaSugg.add(dom.getMeta_info());
					}
				}
			}
		}

		// mi devo aggiornare la sessione dell'utente
		userSession.setAttribute("userSession", u);

		// mi passo tutte le risposte che l'utente ha sbagliato ed ha fatto bene
		model.addAttribute("rispCorrette", rispCorrette);
		model.addAttribute("rispInCorrette", rispInCorrette);

		int perc = u.getPercentuale();
		if (perc != 0) {
			int diff = 100 - perc;
			model.addAttribute("perc", perc);
			model.addAttribute("diff", diff);

		}

		return "risultati";
	}

}