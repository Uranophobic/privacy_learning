package com.privacy.web.control;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.Data;

@Controller
@Data
public class TestControl {

	@Autowired
	DomandaService domServ;
	@Autowired
	SalvataggioService salvServ;
	@Autowired
	UtenteService utServ;
	@Autowired
	TestService testServ;
	@Autowired
	ProgressoService progServ;

	
	@GetMapping("/test/viewTest/{id}")
	public String VisualizzaTest(@PathVariable int id, Model model) {
		//restituisco la lista di domande in base al test
		model.addAttribute("domande", domServ.findByIdTest(id));
		model.addAttribute("idTest", id);
		//model.addAttribute("numDomande", domServ.countDomandeByIdTest())
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

		 System.out.println("email: " + email);

		Utente u = new Utente();
		u = utServ.findUtenteByEmail(email);
		//System.out.println("utente dentro save risp test control" + u);
		String livello = u.getLivello();

		//System.out.println("livello: " + livello);

		// faccio ogni possibile controllo per capire in che livello siamo
		/*
		 * Nessuno -> 1 Base -> 2 Alto -> 3
		 */
		/*
		 * if (livello.equals("Nessuno")) { livello = "1"; u.setLivello("Base"); } else
		 * if (livello.equals("Base")) { livello = "2"; u.setLivello("Medio"); } else {
		 * livello = "3"; u.setLivello("Alto"); }
		 */

		
		if(livello.equals("Nessuno") || (livello.equals("Base") && u.getPercentuale()<=80) ) {
			livello="1";
			u.setLivello("Base");
		} else if (livello.equals("Base") || (livello.equals("Medio") && u.getPercentuale()<=80)) {
			livello="2";
			u.setLivello("Medio");
		} else if (livello.equals("Medio") || (livello.equals("Alto") && u.getPercentuale()<=80)) {
			livello="3";
			u.setLivello("Alto");
		}
		
		
		
		
		// lo trasformo in int
		int id = Integer.parseInt(livello);
		//System.out.println("livello dopo if : " + livello);

		// cerco tute le domande di quel livello
		List<Domanda> domTest = domServ.findByIdTest(id);

		//System.out.println("ECCO LE DOMANDE: " + domTest.size());
		/// mi vado a salvare tutte le risposte che ha dato l'utente

		for (int i = 0; i < domTest.size(); i++) {
			Salvataggio s = new Salvataggio();
			s.setEmail_utente(u.getEmail());
			s.setId_test(id);

			//System.out.println("Id della domanda: " + domTest.get(i).getId_domanda());
			s.setId_domanda(domTest.get(i).getId_domanda());
			//System.out.println("Id della risposta: " +Integer.parseInt(request.getParameter("valore" + domTest.get(i).getId_domanda())) );
			s.setTesto_domanda(domTest.get(i).getTesto());

			// mi prendo l'id della risposta scelta dall'utente
			int idRisp = Integer.parseInt(request.getParameter("valore" + domTest.get(i).getId_domanda()));
			s.setId_risposta(idRisp);
			//System.out.println("idRisp: " + idRisp);
			
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
			// System.out.println("idRisposta corretta:" + idRispCorretta);
			 
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
			} else {
				//System.out.println("syso di prova: il valore è vuoto" + idRispCorretta);
			}
			
			// mi salvo anche la meta info della domanda (mi servirà in futuro per capire quale 
			// argomento deve studiare l'utente
			s.setMeta_info(domTest.get(i).getMeta_info());
			salvServ.save(s);
			//System.out.println("SALVATAGGIO CREATO: " + s.toString());
		}

		// ora vado a vedere effettivamente quante cose ha sbagliato l'utente
		ArrayList<Salvataggio> rispCorrette = new ArrayList<>();
		ArrayList<Salvataggio> rispInCorrette = new ArrayList<>();
		ArrayList<String> argDaStudiare = new ArrayList<>(); // questa è una prova potrei anche non usarlo
		int perc = 0;

		/// mi prendo tutte le risposte salvate dell'utente (che ho letteralmente
		/// salvato prima)
		List<Salvataggio> rispSalvate = salvServ.findByEmail(email);
		//System.out.println("Risposte salvate" + rispSalvate);

		// questo for mi serve per vedere quante domande ha sbagliato l'utente nella
		// pagina risultati
		for (int i = 0; i < rispSalvate.size(); i++) {
			
			// mi scorrro anche le domande DEL TEST
			for (Domanda domanda : domTest) { 
				
				// mi prendo le risposte solo del test appena fatto
				if (rispSalvate.get(i).getId_test() == Integer.parseInt(livello)) {
					
					// se l'id della domanda nel salvataggio è uguale ad un id all'interno delle domande che prima ho preso dal db 
					// (sicuramente lo sarà però è necessario per capire a quale domanda mi sto riferendo)
					if (rispSalvate.get(i).getId_domanda() == domanda.getId_domanda()) { 
						
						// allora vado a controllare se la risposta salvata è uguale a quellacorretta 
						if (rispSalvate.get(i).getId_risposta() == domanda.getRisposta_corretta()) { 
							// e me lo salvo nelle risposte corrette
							rispCorrette.add(rispSalvate.get(i)); 
						} else {
							// altrimenti nelle risposte non corrette
							rispInCorrette.add(rispSalvate.get(i)); 
							//controllo che il progresso non sia già esistente
							ProgressoStudio prog= progServ.findByEmailAndArgomento(u.getEmail(), domanda.getMeta_info());
							if(prog==null) {
							// mi salvo subito il progresso (anche se qui non ha studiato ma almeno inizio a settarlo perchè ho tutto quello che mi serve
								ProgressoStudio p = new ProgressoStudio(); 
								p.setEmail_utente(u.getEmail());
								p.setArg_dastudiare(domanda.getMeta_info());
								progServ.save(p);
								argDaStudiare.add(domanda.getMeta_info());
							} else if(prog!=null && prog.getArg_studiati()!=null) {
								prog.setArg_studiati(null);
								progServ.save(prog);
								argDaStudiare.add(domanda.getMeta_info());
							}
						}
					}
				}
			}
		}

		//System.out.println("Numero di risposte corrette: " + rispCorrette.size());
		//System.out.println("Numero di risposte INcorrette: " + rispInCorrette.size());

		perc = (100 * rispCorrette.size()) / domTest.size();

		//System.out.println("Percentuale di risposte giuste dell'utente: " + perc + "%");

		// mi salvo l'utente e aggiorno la sessione
		u.setPercentuale(perc);
		utServ.save(u);

		// mi devo aggiornare la sessione dell'utente
		userSession.setAttribute("userSession", u);
		
		//System.out.println("TUTTI gli argomenti da studiare:"+ argDaStudiare);
		//
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
		//System.out.println("email: " + email);

		Utente u = new Utente();
		u = utServ.findUtenteByEmail(email);
		
		//System.out.println("utente dentro metodo risultati" + u);

		


		List<Salvataggio> rispTest = new ArrayList<>(); // risposte ultimo tewst che ha fatto l'utente
		rispTest = salvServ.findByMaxIdTest(email);
		int i = 0, id = rispTest.get(i).getId_test();
		List<Domanda> domTest = domServ.findByIdTest(id);

		ArrayList<Salvataggio> rispCorrette = new ArrayList<>();
		ArrayList<Salvataggio> rispInCorrette = new ArrayList<>();
		ArrayList<String> metaSugg= new ArrayList<>();
		ProgressoStudio p= new ProgressoStudio();
		
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
			//System.out.println("differenza: " + diff);
			////System.out.println("percentuale: " + perc);
			
		}

		return "risultati";
	}

	/*
	 * // bottone per andare in testView
	 * 
	 * @GetMapping("/testView/{email}") public String redirect(@PathVariable String
	 * email, Model model) { model.addAttribute("utente",
	 * utServ.findUtenteByEmail(email)); model.addAttribute("ultimoTest",
	 * salvServ.returnLastIdtestByEmail(email)); return "TestView"; }
	 * 
	 * // NELLA PAGINA HTML BISOGNA CONTROLLARE PRIMA SE L'UTENTE LOGGATO E' LO
	 * STESSO // E SE {ID} E' <= DI (ULTIMO TEST UTENTE) + 1 // IL CONTROLLO SERVE
	 * AD EVITARE CHE ALL'UTENTE BASTI CAMBIARE L'ID DALL'URL PER // OTTENERE IL
	 * TEST SUCCESSIVO
	 * 
	 * @GetMapping("/domandeTest/{email}/{id}") // tutti le domande di un
	 * determinato test public String listUser(@PathVariable int id, @PathVariable
	 * String email, Model model) { model.addAttribute("ultimoTestSalvato",
	 * salvServ.returnLastIdtestByEmail(email)); model.addAttribute("domande",
	 * domServ.findByIdTest(id)); return "DomandeView"; }
	 */
	/*
	 * Con questo metodo salvo le risposte date, i suggerimenti e setto il punteggio
	 * preso ed il livello
	 */

	// si può fare perché ho visto su StackOverflow
	// id è l'id test, email già sai
	/*
	 * @PostMapping("/saveTest/{id}/{email}") public String
	 * salvaRisposte(@PathVariable int id, @PathVariable String email,
	 * 
	 * @ModelAttribute("domandeTest") Domanda domanda, Model model,
	 * HttpServletRequest req, HttpServletResponse res) {
	 * 
	 * Utente u = utServ.findUtenteByEmail(email); List<Domanda> dom =
	 * domServ.findByIdTest(id);
	 * 
	 * int count = 0; try { // controllo se non l'utente ha già fatto il test e lo
	 * elimino if (!salvServ.findByEmailAndIdTest(email, id).isEmpty()) {
	 * salvServ.deleteByEmailAndIdTest(email, id); } // creo il salvataggio (manca
	 * solo il settaggio della risposta) Salvataggio s = new Salvataggio();
	 * s.setEmail_utente(u.getEmail()); s.setId_test(id);
	 * 
	 * // suggerimenti ProgressoStudio sug = new ProgressoStudio();
	 * sug.setEmail(u.getEmail()); sug.setArgStudiato(false); sug.setIdTest(id);
	 * 
	 * for (int i = 0; i < dom.size(); i++) { // salvo le risposte
	 * s.setRisposte(req.getParameter("valore" + dom.get(i).getId_domanda()));
	 * salvServ.save(s);
	 * 
	 * // controllo se la risposta data sia corretta if
	 * (dom.get(i).getRisposta_corretta()
	 * .equalsIgnoreCase(req.getParameter("valore" + dom.get(i).getId_domanda()))) {
	 * count++; } else { // qui setto argomento a false se esiste già, ma se
	 * togliamo "argomento // studiato", questo controllo non necessita if
	 * (sugServ.findByEmailAndMeta(sug.getEmail(), dom.get(i).getMeta_info()) !=
	 * null) { sugServ.findByEmailAndMeta(sug.getEmail(),
	 * dom.get(i).getMeta_info()).setArgStudiato(false); } // salvo i suggerimenti
	 * sug.setMetainfo(dom.get(i).getMeta_info()); sugServ.save(sug); } } } catch
	 * (Exception e) { e.printStackTrace(); } // setto percentuale e livello ogni
	 * volta che faccio un test di livello // successivo if
	 * (testServ.returnIdByTipo(u.getLivello()) < id) {
	 * u.setLivello(testServ.returnTipoById(id)); u.setPercentuale(count * 100 /
	 * domServ.findByIdTest(id).size()); } return "redirect:/TestView"; }
	 */
}