package com.privacy.web.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name = "salvataggio_risposte")
@Data // crea i metodi hashCode, equals e toString, getter e setter senza renderli
		// visibili nella classe
@NoArgsConstructor // crea il costruttore vuoto senza renderlo visibile nella classe
@AllArgsConstructor
public class Salvataggio {
	@Column(name = "id_salvataggio")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_salvataggio; // auto
	
	@Column(name = "email_utente")
	private String email_utente;

	@Column(name = "id_test")
	private int id_test;

	@Column(name = "id_domanda")
	private int id_domanda;

	@Column(name = "id_risposta")
	private int id_risposta;
	
	@Column(name = "risposta_corretta")
	private String risposta_corretta;
	
	@Column(name = "risposta_utente")
	private String risposta_utente;
	
	@Column(name = "testo_domanda")
	private String testo_domanda;
	
	@Column(name = "meta_info")
	private String meta_info;

}
