package com.privacy.web.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.privacy.web.model.ArgomentoStudio;
import com.privacy.web.model.Articolo;
import com.privacy.web.model.Favola;
import com.privacy.web.service.ArgomentoStudioService;
import com.privacy.web.service.ArticoloService;
import com.privacy.web.service.FavolaService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/admin")
public class AmministratoreControl {

	@Autowired
	ArticoloService artServ;
	@Autowired
	ArgomentoStudioService argServ;
	@Autowired
	FavolaService favServ;

	@GetMapping("/createArticolo")
	public String addArticolo(Model model) {
		return "createArticolo";
	}

	@GetMapping("/createArgomento")
	public String addArgomento(Model model) {
		return "createArgomento";
	}

	@GetMapping("/createFavola")
	public String addFavola(Model model) {
		return "createFavola";
	}

	@PostMapping("/addArticolo")
	public String createArticolo(@ModelAttribute("articolo") Articolo a, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		try {
			if (artServ.existsByTitolo(request.getParameter("titolo"))) {
				String error = "Esiste già un articolo con questo titolo";
				model.addAttribute("descrizione", error);
				return "redirect:/error?descrizione= " + error;
			} else if (artServ.existsByTitolo(request.getParameter("link"))) {
				String error = "Esiste già un articolo con questo link";
				model.addAttribute("descrizione", error);
				return "redirect:/error?descrizione= " + error;
			} else {
				a.setId_articolo(artServ.findByLastId() + 1);
				a.setLink(request.getParameter("link"));
				a.setMetaInfo(request.getParameter("metainfo"));
				a.setTitolo(request.getParameter("titolo"));
				artServ.save(a);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/users/leggi-un-articolo";
	}
	
	@PostMapping("/addArgomento")
	public String createArgomento(@ModelAttribute("argomento") ArgomentoStudio a, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		try {
			if (argServ.existsByTitolo(request.getParameter("titolo"))) {
				String error = "Esiste già un argomento con questo titolo";
				model.addAttribute("descrizione", error);
				return "redirect:/error?descrizione= " + error;
			} else if (argServ.existsByLink(request.getParameter("link"))) {
				String error = "Esiste già un argomento con questo link";
				model.addAttribute("descrizione", error);
				return "redirect:/error?descrizione= " + error;
			} else if (argServ.existsByDescrizione(request.getParameter("Descrizione"))) {
				String error = "Esiste già un argomento con questa descrizione";
				model.addAttribute("descrizione", error);
				return "redirect:/error?descrizione= " + error;
			}else {
				a.setId_studio(argServ.findByLastId() + 1);
				a.setLink_video(request.getParameter("link"));
				a.setMeta_info(request.getParameter("metainfo"));
				a.setTitolo(request.getParameter("titolo"));
				a.setDescrizione(request.getParameter("descrizione"));
				argServ.save(a);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/argomenti/studia-con-noi";
	}

	@PostMapping("/addFavola")
	public String createFavola(@ModelAttribute("favola") Favola a, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		try {
			if (favServ.existsByTitolo(request.getParameter("titolo"))) {
				String error = "Esiste già un articolo con questo titolo";
				model.addAttribute("descrizione", error);
				return "redirect:/error?descrizione= " + error;
			} else if (favServ.existsByTesto(request.getParameter("testo"))) {
				String error = "Questa favola esiste già";
				model.addAttribute("descrizione", error);
				return "redirect:/error?descrizione= " + error;
			} else {
				a.setId_favola(favServ.findByLastId() +1);
				a.setTesto_favola(request.getParameter("testo"));
				a.setMeta_info(request.getParameter("metainfo"));
				a.setTitolo_favola(request.getParameter("titolo"));
				a.setImage_path(request.getParameter("path"));
				favServ.save(a);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/favole/leggi-una-favola";
	}

}
