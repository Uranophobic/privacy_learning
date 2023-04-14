package com.privacy.web.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.privacy.web.model.MetaInfo;
import com.privacy.web.repository.MetaInfoRepository;


@Controller
@RequestMapping("/metainfo")
public class MetaInfoControl {
	
	@Autowired
	public MetaInfoRepository metaRep;
	
	@GetMapping ("/all-meta")
	public String tutte (Model model) {
	
		System.out.println(metaRep.findAll());
		model.addAttribute("metainfo", metaRep.findAll());
		return "createArgomento"; ///boh
	}
}
