package com.privacy.web.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.privacy.web.model.ArgomentoStudio;
@Service
public interface ArgomentoStudioService {
	int findByLastId();
	public ArgomentoStudio findById(int id);
	public List<ArgomentoStudio> findAllArgomenti();
	boolean existsByTitolo(String titolo);
	void save(ArgomentoStudio a);
	boolean existsByDescrizione(String descrizione);
	boolean existsByLink(String link);
}
