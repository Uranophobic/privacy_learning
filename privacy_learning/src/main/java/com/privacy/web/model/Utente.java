package com.privacy.web.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "utente")
public class Utente {
	
	@Id 
	private String email;
	
	private String password;
	private String nome;
	private String cognome;
	private String dataNascita;
	
	private int percentuale;

	private String livello;
	
	public Utente() {
		
	}

	
	public Utente(String email, String password, String nome, String cognome, String dataNascita, int percentuale, String livello) {
		super();
		this.email = email;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
		this.percentuale=percentuale;
		this.livello=livello;
		
	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDataNascita() {
		return dataNascita;
	}
	public void setDataNascita(String dataNascita) {
		this.dataNascita = dataNascita;
	}

	
	
	public int getPercentuale() {
		return percentuale;
	}


	public void setPercentuale(int percentuale) {
		this.percentuale = percentuale;
	}


	public void setLivello(String livello) {
		this.livello = livello;
	}
	
	public String getLivello() {
		return livello;
	}


	@Override
	public String toString() {
		return "Utente [email=" + email + ", password=" + password + ", nome=" + nome + ", cognome=" + cognome
				+ ", dataNascita=" + dataNascita + ", percentuale=" + percentuale + ", livello=" + livello + "]";
	}

}
