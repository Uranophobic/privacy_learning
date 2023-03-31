package com.privacy.web.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "gioco")
public class Gioco {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private int idGioco;
	private String titolo;
	private String descrizione;
	private String meta_info;

	
	

	public Gioco() {
		//super();
	}



	public Gioco(int idGioco, String titolo, String descrizione, String meta_info) {
		super();
		this.idGioco = idGioco;
		this.titolo = titolo;
		this.descrizione = descrizione;
		this.meta_info = meta_info;
	}



	public int getIdGioco() {
		return idGioco;
	}



	public void setIdGioco(int idGioco) {
		this.idGioco = idGioco;
	}



	public String getTitolo() {
		return titolo;
	}



	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}



	public String getDescrizione() {
		return descrizione;
	}



	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}



	public String getMeta_info() {
		return meta_info;
	}



	public void setMeta_info(String meta_info) {
		this.meta_info = meta_info;
	}



	@Override
	public String toString() {
		return "Gioco [idGioco=" + idGioco + ", titolo=" + titolo + ", descrizione=" + descrizione + ", meta_info="
				+ meta_info + "]";
	}
	
	
	

}
