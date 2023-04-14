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
				Articolo ar=new Articolo();
				ar.setLink(request.getParameter("link"));
				ar.setMetaInfo(request.getParameter("metainfo"));
				ar.setTitolo(request.getParameter("titolo"));
				System.out.println(ar.toString());
				artServ.save(ar);
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
				ArgomentoStudio argomento= new ArgomentoStudio();
				argomento.setLinkvideo(request.getParameter("link"));
				argomento.setMeta_info(request.getParameter("metainfo"));
				argomento.setTitolo(request.getParameter("titolo"));
				argomento.setDescrizione(request.getParameter("descrizione"));
				System.out.println(argomento.toString());
				argServ.save(argomento);
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
				Favola f=new Favola();
				f.setTestofavola(request.getParameter("testo"));
				f.setMeta_info(request.getParameter("metainfo"));
				f.setTitolofavola(request.getParameter("titolo"));
				f.setImage_path(request.getParameter("path"));
				System.out.println(f.toString());
				favServ.save(f);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/favole/leggi-una-favola";
	}

}
