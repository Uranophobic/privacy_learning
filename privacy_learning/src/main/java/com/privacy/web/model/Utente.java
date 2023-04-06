package com.privacy.web.model;

import jakarta.persistence.Column;
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
	
	@Id @Column(name="email")
	private String email;
	@Column(name="password")
	private String password;
	@Column(name="nome")
	private String nome;
	@Column(name="cognome")
	private String cognome;
	@Column(name="data_nascita")
	private String dataNascita;
	@Column(name="percentuale")
	private int percentuale;
	@Column(name="livello")
	private String livello;

}
