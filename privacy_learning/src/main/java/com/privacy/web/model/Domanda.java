package com.privacy.web.model;

import jakarta.persistence.Column;

/*
 * Entity per le domande
 */

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "domanda")
@Data // crea i metodi hashCode, equals e toString, getter e setter senza renderli
		// visibili nella classe
@NoArgsConstructor // crea il costruttore vuoto senza renderlo visibile nella classe

public class Domanda {
	@Column(name = "id_domanda")
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_domanda;
	
	@Column(name = "testo")
	private String testo;
	
	@Column(name = "meta_info")
	private String meta_info;
	
	@Column(name = "risposta1")
	private String risposta1;
	
	@Column(name = "risposta2")
	private String risposta2;
	
	@Column(name = "risposta3")
	private String risposta3;
	
	@Column(name = "risposta4")
	private String risposta4;
	
	@Column(name = "risposta_corretta")
	private int risposta_corretta;

	@Column(name = "id_test")
	private int id_test;
	
	
	public Domanda(int id_test, String meta_info, String testo, int risposta_corretta, String risposta1,
			String risposta2, String risposta3, String risposta4) {
		this.testo = testo;
		this.risposta_corretta = risposta_corretta;
		this.id_test = id_test;
		this.risposta1 = risposta1;
		this.risposta2 = risposta2;
		this.risposta3 = risposta3;
		this.risposta4 = risposta4;
		this.meta_info = meta_info;
	}

}
