package com.privacy.web.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.events.Event.ID;

import com.privacy.web.model.Utente;
import com.privacy.web.repository.UtenteRepository;
import com.privacy.web.service.UtenteService;
import com.privacy.web.utils.Check;

@Service
public class UtenteServiceImpl implements UtenteService {

	private UtenteRepository utenteRep;

	public UtenteServiceImpl(UtenteRepository utenteRep) {
		super();
		this.utenteRep = utenteRep;
	}

	@Override
	public List<Utente> findAll() {
		// TODO Auto-generated method stub
		return (List<Utente>) utenteRep.findAll();
	}

	/**
	 * Salva un utente preso in input
	 *@param user utente da controllare
	 *@throws Exception, eccezioni in caso di errori
	 *@return Utente salvato, null altrimenti
	 */
	@Override
	public Utente saveUser(Utente user) throws Exception {
/*		try {
			if (utenteRep.existsById(user.getEmail())) {
				throw new Exception("L'account con email " + user.getEmail() + "esiste già");
			}
			Utente utente = new Utente();
			if (Check.checkName(user.getNome()) && Check.checkSurname(user.getCognome())
					&& Check.checkEmail(user.getEmail())) {
				utente.setNome(user.getNome());
				utente.setCognome(user.getCognome());
				utente.setEmail(user.getEmail());
				utente.setPassword(user.getPassword());

				if(utRep.existsById(request.getParameter("email"))==false) {
				ArrayList<String> risposteArrayList= new ArrayList<>();
					for(int i=1;i<=testRep.findNDomandeById(4);i++) { //popolo l'array di risposte date con id_test 4("conoscitivo")
						risposteArrayList.add(request.getParameter("r"+i)); //id della risposta i
					}
					for(int i=0;i<risposteArrayList.size();i++) {
						salvataggioRep.save(new Salvataggio(4, user.getEmail(), risposteArrayList.get(i)));
					}
					response.getWriter().write("5"); //registrazione avvenuta con successo
				} else {
                    response.getWriter().write("4"); // errore nella registrazione
                    response.sendRedirect("./templates/Registrazione.html?error=" + error);
                }
				return utenteRep.save(user);
			} else {
				if (!Check.checkName(user.getNome())) {
					throw new Exception("nome non corretto");
				}
				if (!Check.checkSurname(user.getCognome())) {
					throw new Exception("Cognome non corretto");
				}
				if (!Check.checkName(user.getEmail())) {
					throw new Exception("Email non corretta");
				}
			String descrizione = "Siamo spiacenti si è verificato un errore con la registrazione. Riprova!";			
			response.sendRedirect(descrizione);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	*/
		return utenteRep.save(user);
		}

	@Override
	public boolean existsById(String id) {
		return utenteRep.existsById((String) id);
	}

	@Override
	public Utente findUtenteByEmail(String email) {
		return	utenteRep.findUtenteByEmail(email);
	}

	
	
}
