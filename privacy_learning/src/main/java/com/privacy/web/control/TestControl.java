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

import com.privacy.web.model.Domanda;
import com.privacy.web.model.Salvataggio;
import com.privacy.web.model.ProgressoStudio;
import com.privacy.web.model.Utente;
import com.privacy.web.service.DomandaService;
import com.privacy.web.service.SalvataggioService;
import com.privacy.web.service.SuggerimentoService;
import com.privacy.web.service.TestService;
import com.privacy.web.service.UtenteService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;

@RequestMapping("/test")
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
	SuggerimentoService sugServ;

	@GetMapping("/domandeTest/{id}") // tutti le domande di un determinato test
	public String listUser(@PathVariable int id, @PathVariable String email, Model model) {
		model.addAttribute("domande", domServ.findByIdTest(id));
		return "DomandeView";
	}

	/*
	 * Con questo metodo salvo le risposte date, i suggerimenti e setto il punteggio
	 * preso ed il livello
	 */

	// si può fare perché ho visto su StackOverflow
	// id è l'id test, email già sai
	@PostMapping("/saveTest/{id}/{email}")
	public String salvaRisposte(@PathVariable int id, @PathVariable String email,
			@ModelAttribute("domandeTest") Domanda domanda, Model model, HttpServletRequest req,
			HttpServletResponse res) {

		Utente u = utServ.findUtenteByEmail(email);
		List<Domanda> dom = domServ.findByIdTest(id);

		int count = 0;
		ArrayList<String> metaErrate = new ArrayList<String>();
		try {
			// controllo se non l'utente ha già fatto il test e lo elimino
			if (!salvServ.findByEmailAndIdTest(email, id).isEmpty()) {
				salvServ.deleteByEmailAndIdTest(email, id);
			}
			// creo il salvataggio (manca solo il settaggio della risposta)
			Salvataggio s = new Salvataggio();
			s.setEmail_utente(u.getEmail());
			s.setIdTest(id);

			// suggerimenti
			ProgressoStudio sug = new ProgressoStudio();
			sug.setEmail(u.getEmail());
			sug.setArgStudiato(false);
			sug.setIdTest(id);

			for (int i = 0; i < dom.size(); i++) {
				// salvo le risposte
				s.setRisposte(req.getParameter("valore" + dom.get(i).getId_domanda()));
				salvServ.save(s);
				// controllo se la risposta data sia corretta
				if (dom.get(i).getRisposta_corretta()
						.equalsIgnoreCase(req.getParameter("valore" + dom.get(i).getId_domanda()))) {
					count++;
				} else {
					// qui setto argomento a false se esiste già, ma se togliamo "argomento
					// studiato", questo controllo non necessita
					if (sugServ.findByEmailAndMeta(sug.getEmail(), dom.get(i).getMeta_info()) != null) {
						sugServ.findByEmailAndMeta(sug.getEmail(), dom.get(i).getMeta_info()).setArgStudiato(false);
						continue;
					}
					// salvo i suggerimenti
					sug.setMetainfo(dom.get(i).getMeta_info());
					sugServ.save(sug);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// setto percentuale e livello ogni volta che faccio un test di livello
		// successivo
		if (testServ.returnIdByTipo(u.getLivello()) == 4 || testServ.returnIdByTipo(u.getLivello()) < id) {
			u.setLivello(testServ.returnTipoById(id));
			u.setPercentuale(count * 100 / domServ.findByIdTest(id).size());
		}

		/*
		 * SE VOGLIAMO SALVARCI I LIVELLI E LE PERCENTUALI DI TUTTI I TEST CHE FA UN UTENTE DOBBIAMO CREARE 
		 * UNA NUOVA ENTITà 'PUNTEGGI' CON: id, email, livello(tipo_test o id_test), percentuale
		 */
		
		return "redirect:/TestView";
	}
}
