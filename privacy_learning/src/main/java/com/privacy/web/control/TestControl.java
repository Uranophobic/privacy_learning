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
import com.privacy.web.model.Utente;
import com.privacy.web.service.DomandaService;
import com.privacy.web.service.SalvataggioService;
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
	
	@GetMapping("/domandeTest/{id}") // tutti le domande di un determinato test
	public String listUser(@PathVariable int id, @PathVariable String email, Model model) {
		model.addAttribute("domandeTest", domServ.findByIdTest(id));
		return "DomandeView";
	}
	
	//si può fare perché ho visto su StackOverflow
	@PostMapping("/saveTest/{id}/{email}")
	public String salvaRisposte(@PathVariable int id, @PathVariable String email, 
			@ModelAttribute("domandeTest") Domanda domanda, 
			Model model, HttpServletRequest req, HttpServletResponse res) {
		
		Utente u= utServ.findUtenteByEmail(email);
		List<Domanda> dom= domServ.findByIdTest(id);
		ArrayList<String> risposte=new ArrayList<>();
		int count=0; 
		ArrayList<String> errate= new ArrayList<String>();
		
		//controllo se non l'utente ha già fatto il test e lo elimino
		if(!salvServ.findByEmailAndIdTest(email, id).isEmpty()) {salvServ.deleteByEmailAndIdTest(email, id);}
		
		//salvo le risposte
		for(int i=0;i< dom.size();i++) {
			risposte.add(req.getParameter("valore" + dom.get(i).getId_domanda()));
			
			//controllo se la risposta data sia corretta
			if(dom.get(i).getRisposta_corretta().equalsIgnoreCase(req.getParameter("valore" + dom.get(i).getId_domanda()))) {
				count++;
			} else {
				
				//creo direttamente la lista delle metainfo
				errate.add(dom.get(i).getMeta_info());
			}
		}
		
		//setto percentuale e livello ogni volta che creo un test
		u.setPercentuale(count*100/domServ.findByIdTest(id).size());
		u.setLivello(testServ.returnTipoById(id));
		
		model.addAttribute("suggerimentoMeta",errate);
		
		//dobbiamo vedere dove renderizzare
		return "redirect:/testView";
	}
}
