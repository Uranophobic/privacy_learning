package com.privacy.web.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "salvataggio_risposte")
public class Salvataggio {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private int idSalvataggio;
	
	private int idTest;
	private String email_utente;
	private String risposta;
	
	public Salvataggio() {}
	public Salvataggio(int idTest, String email_utente, String risposta) {
		super();
		this.idTest = idTest;
		this.email_utente = email_utente;
		this.risposta = risposta;
	}

	public int getIdSalvataggio() {
		return idSalvataggio;
	}
	
	public void setIdSalvataggio(int idSalvataggio) {
		this.idSalvataggio = idSalvataggio;
	}
	
	public int getIdTest() {
		return idTest;
	}
	
	public void setIdTest(int idTest) {
		this.idTest = idTest;
	}
	
	public String getEmail() {
		return email_utente;
	}
	
	public void setEmail(String email_utente) {
		this.email_utente = email_utente;
	}
	
	public String getRisposta() {
		return risposta;
	}
	
	public void setRisposta(String risposta) {
		this.risposta = risposta;
	}
	
	@Override
	public String toString() {
		return "Salvataggio [idSalvataggio=" + idSalvataggio + ", idTest=" + idTest + ", email_utente=" + email_utente
				+ ", risposta=" + risposta + "]";
	}
	
	
	
}
