package com.privacy.web.model;


/*
 * Entity per le domande
 */

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "domanda")
public class Domanda {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private int idDomanda;
	private int idTest;
	private String testo;
	private String risposta_corretta;
	private String meta_info;
	private String risposta1;
	private String risposta2;
	private String risposta3;
	private String risposta4;


	public Domanda() {}

	public Domanda(int idTest, String meta_info, String testo, String risposta_corretta, String risposta1,
			String risposta2, String risposta3, String risposta4) {
		this.testo = testo;
		this.risposta_corretta = risposta_corretta;
		this.idTest = idTest;
		this.risposta1 = risposta1;
		this.risposta2 = risposta2;
		this.risposta3 = risposta3;
		this.risposta4 = risposta4;
		this.meta_info= meta_info;
	}

	public int  getIdDomanda() {
		return idDomanda;
	}
	
	public void setIdDomanda(int idDomanda) {
		this.idDomanda = idDomanda;
	}

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

	public String getRisposta_corretta() {
		return risposta_corretta;
	}
	
	public void setRisposta_corretta(String risposta_corretta) {
		this.risposta_corretta = risposta_corretta;
	}
	
	public String getRisposta1() {
		return risposta1;
	}

	public void setRisposta1(String risposta1) {
		this.risposta1 = risposta1;
	}

	public String getRisposta2() {
		return risposta2;
	}

	public void setRisposta2(String risposta2) {
		this.risposta2 = risposta2;
	}

	public String getRisposta3() {
		return risposta3;
	}

	public void setRisposta3(String risposta3) {
		this.risposta3 = risposta3;
	}

	public int getIdTest() {
		return idTest;
	}

	public void setIdTest(int idTest) {
		this.idTest = idTest;
	}

	public String getMeta_info() {
		return meta_info;
	}

	public void setMeta_info(String meta_info) {
		this.meta_info = meta_info;
	}

	public String getRisposta4() {
		return risposta4;
	}

	public void setRisposta4(String risposta4) {
		this.risposta4 = risposta4;
	}

	@Override
	public String toString() {
		return "Domanda [idDomanda=" + idDomanda + ", idTest=" + idTest + ", testo=" + testo + ", risposta_corretta="
				+ risposta_corretta + ", meta_info=" + meta_info + ", risposta1=" + risposta1 + ", risposta2="
				+ risposta2 + ", risposta3=" + risposta3 + ", risposta4=" + risposta4 + "]";
	}

	








}
