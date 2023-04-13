package com.privacy.web.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.privacy.web.model.Domanda;
import com.privacy.web.service.DomandaService;

import jakarta.servlet.annotation.WebServlet;

@WebServlet
@Controller
public class TestControl {
	
	@Autowired
	private DomandaService domServ;
	
	@GetMapping("/regprova")  //qui ci dovrebbe andasre il link della registrazione ma non sono sicura
	public String prova(Model model) {
		List<Domanda> questionario = domServ.findByIdTest(4);
		model.addAttribute("questionario", questionario);
		return "niente";  
	}


}
