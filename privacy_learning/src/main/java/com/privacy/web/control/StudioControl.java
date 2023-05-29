package com.privacy.web.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.privacy.web.service.ProgressoService;

@Controller
@RequestMapping("/studio")
public class StudioControl {
	@Autowired
	ProgressoService suggServ;
	
	@GetMapping("/allSuggeriti/{email}")
	public String tuttiSuggerimenti(@PathVariable String email, Model model) {
		model.addAttribute("suggeriti", suggServ.findByEmail(email));
		return "SuggerimentiView";
	}
	
	
}
