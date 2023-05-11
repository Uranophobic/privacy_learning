package com.privacy.web.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.privacy.web.service.MetaInfoService;


@Controller
@RequestMapping("/metainfo")
public class MetaInfoControl {
	
	@Autowired
	public MetaInfoService metaServ;
	
	@SuppressWarnings("null")
	@GetMapping ("/all-meta")
	public List<ModelAndView> tutte (Model model) {
		List<ModelAndView> mov = null;
		mov.add(new ModelAndView("createArgomento", "metainfo",metaServ.findAll()));
		mov.add(new ModelAndView("editArgomento", "metainfo",metaServ.findAll()));
		mov.add(new ModelAndView("editArticolo", "metainfo",metaServ.findAll()));
		
		return mov; ///boh
		
	}
	
	@GetMapping("/all")
	public String all(Model model) {
		model.addAttribute("metainfo",metaServ.findAll());
		return "metaView";
	}
}
