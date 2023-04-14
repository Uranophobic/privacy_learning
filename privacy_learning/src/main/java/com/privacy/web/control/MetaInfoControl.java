package com.privacy.web.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.privacy.web.repository.MetaInfoRepository;

@Controller
@RequestMapping("/metainfo")
public class MetaInfoControl {
	
	private MetaInfoRepository metaRep;
	
	@GetMapping ("/all-meta")
	public String tutte (Model model) {
		model.addAttribute("metainfo", metaRep.findAll());
		return "redirect:/homepage"; ///boh
	}
}
