package com.privacy.web.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.privacy.web.model.ArgomentoStudio;
import com.privacy.web.model.Articolo;
import com.privacy.web.model.Domanda;
import com.privacy.web.model.Favola;
import com.privacy.web.repository.MetaInfoRepository;
import com.privacy.web.service.ArgomentoStudioService;
import com.privacy.web.service.ArticoloService;
import com.privacy.web.service.DomandaService;
import com.privacy.web.service.FavolaService;
import com.privacy.web.service.MetaInfoService;
import com.privacy.web.service.TestService;

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
	@Autowired
	MetaInfoService metaServ;
	@Autowired
	DomandaService domServ;
	@Autowired
	TestService testServ;

	/*-----------------------------------CREAZIONE--------------------------------------------*/
//-----------------------METODI GET
	@GetMapping("/createArticolo")
	public String addArticolo(Model model) {
		model.addAttribute("metainfo", metaServ.findAll());
		return "createArticolo";
	}

	@GetMapping("/createArgomento")
	public String addArgomento(Model model) {
		model.addAttribute("metainfo", metaServ.findAll());
		return "createArgomento";
	}

	@GetMapping("/createFavola")
	public String addFavola(Model model) {
		model.addAttribute("metainfo", metaServ.findAll());
		return "createFavola";
	}
	
	@GetMapping("/createDomanda")
	public String addDomanda(Model model) {
		model.addAttribute("metainfo", metaServ.findAll());
		model.addAttribute("test", testServ.findAllTest());
		return "createDomanda";
	}

//-----------------------METODI POST
	@PostMapping("/addArticolo")
	public String createArticolo(@ModelAttribute("articolo") Articolo a, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Articolo art = new Articolo();
		art.setLink(request.getParameter("link"));
		art.setMetaInfo(request.getParameter("metainfo"));
		art.setTitolo(request.getParameter("titolo"));
		try {
			if (artServ.existsByTitolo(a.getTitolo())) {
				String error = "Esiste già un articolo con questo titolo";
				model.addAttribute("descrizione", error);
				return "redirect:/error?descrizione= " + error;
			} else if (artServ.existsByTitolo(a.getLink())) {
				String error = "Esiste già un articolo con questo link";
				model.addAttribute("descrizione", error);
				return "redirect:/error?descrizione= " + error;
			} else {
				artServ.save(art);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/article/leggi-un-articolo";
	}

	@PostMapping("/addArgomento")
	public String createArgomento(@ModelAttribute("argomento") ArgomentoStudio argomento, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		try {
			
			if (argServ.existsByTitolo(argomento.getTitolo())) {
				String error = "Esiste già un argomento con questo titolo";
				model.addAttribute("descrizione", error);
				return "redirect:/error?descrizione= " + error;
			} else if (argServ.existsByLink(argomento.getLinkvideo())) {
				String error = "Esiste già un argomento con questo link";
				model.addAttribute("descrizione", error);
				return "redirect:/error?descrizione= " + error;
			} else if (argServ.existsByDescrizione(argomento.getDescrizione())) {
				String error = "Esiste già un argomento con questa descrizione";
				model.addAttribute("descrizione", error);
				return "redirect:/error?descrizione= " + error;
			}  else if(argomento.getDescrizione().length()<200) {
				String error = "argomento non inserito o troppo corto";
				model.addAttribute("descrizione", error);
				return "redirect:/error?descrizione= " + error;
			} else {
				ArgomentoStudio a = new ArgomentoStudio();
				a.setLinkvideo(request.getParameter("link"));
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
	public String createFavola(@ModelAttribute("favola") Favola fav, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Favola f = new Favola();
		f.setTestofavola(request.getParameter("testo"));
		f.setMeta_info(request.getParameter("metainfo"));
		f.setTitolofavola(request.getParameter("titolo"));
		f.setImage_path(request.getParameter("path"));
		try {
			if (favServ.existsByTitolo(fav.getTitolofavola())) {
				String error = "Esiste già un articolo con questo titolo";
				model.addAttribute("descrizione", error);
				return "redirect:/error?descrizione= " + error;
			} else if (favServ.existsByTesto(fav.getTestofavola())) {
				String error = "Questa favola esiste già";
				model.addAttribute("descrizione", error);
				return "redirect:/error?descrizione= " + error;
			} else {

				System.out.println(f.toString());
				favServ.save(f);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/favole/leggi-una-favola";
	}

	@PostMapping("/addDomanda")
	public String createDomanda(@ModelAttribute("domanda") Domanda dom, Model model) {
		try {
			if (domServ.existsByTesto(dom.getTesto()) ) {
				String error = "titolo esistente";
				model.addAttribute("descrizione", error);
				return "redirect:/error?descrizione= " + error;
			} else {
				domServ.save(dom);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/admin/allDomande";
	}
	/*---------------------------------ELIMINAZIONE----------------------------------------------*/

	@GetMapping("/deleteArticolo/{id}")
	public String eliminaArticolo(@PathVariable int id, Model model) {
		favServ.deleteById(id);
		return "redirect:/article/leggi-un-articolo";
	}

	@GetMapping("/deleteArgomento/{id}")
	public String eliminaArgomento(@PathVariable int id, Model model) {
		favServ.deleteById(id);
		return "redirect:/argomenti/studia-con-noi";
	}

	@GetMapping("/deleteFavola/{id}")
	public String eliminaFavola(@PathVariable int id, Model model) {
		favServ.deleteById(id);
		return "redirect:/favole/leggi-una-favola";
	}
	
	@GetMapping("/deleteDomanda/{id}")
	public String eliminaDomanda(@PathVariable int id, Model model) {
		domServ.deleteById(id);
		return "redirect:/admin/AllDomande";
	}
	/*-----------------------------------MODIFICA--------------------------------------------*/

//----------------ARTICOLO
	@GetMapping("/fixedArticolo/{id}")
	public String editArticolo(@PathVariable int id, Model model) {
		model.addAttribute("articolo", artServ.findByIdArticolo(id));
		model.addAttribute("metainfo", metaServ.findAll());
		return "editArticolo";
	}

	@PostMapping("/modificaArticolo/{id}")
	public String updateArticolo(@PathVariable int id, @ModelAttribute("articolo") Articolo a, Model model) {
		Articolo artExist = artServ.findByIdArticolo(id);
		artExist.setId_articolo(id);
		artExist.setLink(a.getLink());
		artExist.setMetaInfo(a.getMetaInfo());
		artExist.setTitolo(a.getTitolo());

		try {
			if (artServ.existsByTitolo(a.getTitolo())) {
				String error = "Esiste già un articolo con questo titolo";
				model.addAttribute("descrizione", error);
				return "redirect:/error?descrizione= " + error;
			} else if (artServ.existsByTitolo(a.getLink())) {
				String error = "Esiste già un articolo con questo link";
				model.addAttribute("descrizione", error);
				return "redirect:/error?descrizione= " + error;
			} else {
				artServ.save(artExist);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/article/leggi-un-articolo";
	}

//----------------ARGOMENTO
	@GetMapping("/fixedArgomento/{id}")
	public String editArgomento(@PathVariable int id, Model model) {
		model.addAttribute("argomento", argServ.findById(id));
		model.addAttribute("metainfo", metaServ.findAll());
		System.out.println(metaServ.findAll());
		return "editArgomento";
	}

	@PostMapping("/modificaArgomento/{id}")
	public String updateArgomento(@PathVariable int id, @ModelAttribute("argomento") ArgomentoStudio a, Model model) {
		ArgomentoStudio argExist = argServ.findById(id);
		argExist.setId_studio(id);
		argExist.setDescrizione(a.getDescrizione());
		argExist.setLinkvideo(a.getLinkvideo());
		argExist.setMeta_info(a.getMeta_info());
		argExist.setTitolo(a.getTitolo());
		
		try {
			argServ.deleteById(id);
			if (argServ.existsByDescrizione(argExist.getTitolo())) {
				String error = "Esiste già un argomento con questo titolo";
				model.addAttribute("descrizione", error);
				return "redirect:/error?descrizione= " + error;
			} else if (argServ.existsByDescrizione(argExist.getDescrizione())) {
				String error = "Esiste già un argomento con questa descrizione";
				model.addAttribute("descrizione", error);
				return "redirect:/error?descrizione= " + error;
			} else {
				argServ.save(argExist);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/argomenti/studia-con-noi";
	}

//----------------FAVOLA
	@GetMapping("/fixedFavola/{id}")
	public String editFavola(@PathVariable int id, Model model) {
		model.addAttribute("favola", favServ.findById(id));
		model.addAttribute("metainfo", metaServ.findAll());
		return "editFavola";
	}

	@PostMapping("/modificaFavola/{id}")
	public String updateFavola(@PathVariable int id, @ModelAttribute("articolo") Favola f, Model model) {
		Favola favExist = favServ.findById(id);
		favExist.setId_favola(id);
		favExist.setImage_path(f.getImage_path());
		favExist.setMeta_info(f.getMeta_info());
		favExist.setTestofavola(f.getTestofavola());
		favExist.setTitolofavola(f.getTitolofavola());
		favServ.deleteById(id);
		try {
			if (favServ.existsByTesto(favExist.getTestofavola()) ) {
				String error = "titolo esistente";
				model.addAttribute("descrizione", error);
				return "redirect:/error?descrizione= " + error;
			} else if (favServ.existsByTitolo(favExist.getTitolofavola())) {
				String error = "favola già esiste";
				model.addAttribute("descrizione", error);
				return "redirect:/error?descrizione= " + error;
			} else {
				favServ.save(favExist);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/favole/leggi-una-favola";
	}

	//----------------DOMANDA
	@GetMapping("/fixedDomanda/{id}")
	public String editDomanda(@PathVariable int id, Model model) {
		model.addAttribute("domanda", domServ.findById(id));
		model.addAttribute("metainfo", metaServ.findAll());
		model.addAttribute("test", testServ.findAllTest());
		return "editDomanda";
	}

	@PostMapping("/modificaDomanda/{id}")
	public String updateFavola(@PathVariable int id, @ModelAttribute("domanda") Domanda d, Model model) {
		Domanda domExist = domServ.findById(id);
		
		domExist.setId_domanda(d.getId_domanda());
		domExist.setId_test(d.getId_test());
		domExist.setMeta_info(d.getMeta_info());
		domExist.setTesto(d.getTesto());
		domExist.setRisposta1(d.getRisposta1());
		domExist.setRisposta2(d.getRisposta2());
		domExist.setRisposta3(d.getRisposta3());
		domExist.setRisposta4(d.getRisposta4());
		domExist.setRisposta_corretta(d.getRisposta_corretta());
		
		domServ.deleteById(id);
		try {
			if (domServ.existsByTesto(domExist.getTesto()) ) {
				String error = "titolo esistente";
				model.addAttribute("descrizione", error);
				return "redirect:/error?descrizione= " + error;
			} else {
				domServ.save(domExist);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/domande/allDomande";
	}
}
