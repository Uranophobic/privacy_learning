package com.privacy.web.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "utente")
@Data //crea i metodi hashCode, equals e toString, getter e setter senza renderli visibili nella classe
@NoArgsConstructor //crea il costruttore vuoto senza renderlo visibile nella classe
@AllArgsConstructor //crea il costruttore con paramentri senza renderlo visibile nella classe

public class Utente {
	
	@Id 
	private String email;
	
	private String password;
	private String nome;
	private String cognome;
	private String dataNascita;
	private int percentuale;
	private String livello;

}
