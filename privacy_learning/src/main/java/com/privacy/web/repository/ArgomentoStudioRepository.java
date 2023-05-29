package com.privacy.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.privacy.web.model.ArgomentoStudio;

public interface ArgomentoStudioRepository extends CrudRepository<ArgomentoStudio, Integer> {
	@Query(value = "Select max(id_studio) FROM argomento_studio ORDER BY id_studio ASC", nativeQuery = true)
	int returnLastId();
	@Query(value = "SELECT * FROM argomento_studio AS a WHERE a.id_studio= :id", nativeQuery = true)
	ArgomentoStudio findArgomentoById(@Param("id") int id);
	boolean existsByTitolo(String titolo);
	boolean existsByDescrizione(String descrizione);
	boolean existsByLinkvideo(String link);
	
	//argomenti da studiare dell'utente
	@Query(value = "select argomento_studio.* from argomento_studio join progresso_studio on progresso_studio.meta_info = argomento_studio.meta_info join utente on progresso_studio.email_utente=utente.email WHERE utente.email=?1", nativeQuery = true)
	List<ArgomentoStudio> findAllArgDaStudiare(String email);
	
	/*
	 * @Query(
	 * value="select * from argomento_studio where argomento_studio.meta_info not in (select argomento_studio.meta_info from argomento_studio join progresso_studio on progresso_studio.arg_dastudiare = argomento_studio.meta_info join utente on progresso_studio.email_utente=utente.email WHERE utente.email=?1)"
	 * , nativeQuery = true) List<ArgomentoStudio> findArgomentiNoStudy(String
	 * email); //MI PRENDE il restante degli argomenti che non deve studiare
	 * l'utente
	 */
	ArgomentoStudio findArgomentoByTitolo(String titolo);
	
	@Query(value = "SELECT * FROM argomento_studio WHERE argomento_studio.meta_info=?1", nativeQuery = true)
	List<ArgomentoStudio> findArgomentoByMeta(String meta_info);
}
