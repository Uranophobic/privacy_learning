package com.privacy.web.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.privacy.web.model.ArgomentoStudio;
@Service
public interface ArgomentoStudioService {
	void deleteById(int id);
	int findByLastId();
	public ArgomentoStudio findById(int id);
	public List<ArgomentoStudio> findAllArgomenti();
	boolean existsByTitolo(String titolo);
	void save(ArgomentoStudio a);
	boolean existsByDescrizione(String descrizione);
	boolean existsByLink(String link);
	List<ArgomentoStudio> findAllArgDaStudiare(String email);
	List<ArgomentoStudio> findArgomentiNoStudy(String email); //da non studiare
	ArgomentoStudio findArgomentoByTitolo(String titolo);
	List<ArgomentoStudio> findArgomentoByMeta(String meta); //da non studiare
	}
