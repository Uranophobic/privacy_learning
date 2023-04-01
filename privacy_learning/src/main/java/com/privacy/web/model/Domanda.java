package com.privacy.web.model;


/*
 * Entity per le domande
 */

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "domanda")
@Data //crea i metodi hashCode, equals e toString, getter e setter senza renderli visibili nella classe
@NoArgsConstructor //crea il costruttore vuoto senza renderlo visibile nella classe

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








}
