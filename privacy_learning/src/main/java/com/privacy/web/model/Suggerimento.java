package com.privacy.web.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "progresso_studio")
public class Suggerimento {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private int idSuggerimento;
	
	private String email;
	private int idTest;
	
	private String argSuggerito;
	private boolean argStudiato;
	
	public Suggerimento() {}
	
	public Suggerimento(int idSuggerimento, String email, int idTest, String argSuggerito, boolean argStudiato) {
		this.idSuggerimento = idSuggerimento;
		this.email = email;
		this.idTest = idTest;
		this.argSuggerito = argSuggerito;
		this.argStudiato = argStudiato;
	}

	public int getIdSuggerimento() {
		return idSuggerimento;
	}
	
	public void setIdSuggerimento(int idSuggerimento) {
		this.idSuggerimento = idSuggerimento;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getIdTest() {
		return idTest;
	}
	
	public void setIdTest(int idTest) {
		this.idTest = idTest;
	}
	
	public String getArgSuggerito() {
		return argSuggerito;
	}
	
	public void setArgSuggerito(String argSuggerito) {
		this.argSuggerito = argSuggerito;
	}
	
	public boolean isArgStudiato() {
		return argStudiato;
	}
	
	public void setArgStudiato(boolean argStudiato) {
		this.argStudiato = argStudiato;
	}
	
	@Override
	public String toString() {
		return "Suggerimento [idSuggerimento=" + idSuggerimento + ", email=" + email + ", idTest=" + idTest
				+ ", argSuggerito=" + argSuggerito + ", argStudiato=" + argStudiato + "]";
	}
}
