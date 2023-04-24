package com.privacy.web.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name = "progresso_studio")
@Data // crea i metodi hashCode, equals e toString, getter e setter senza renderli
		// visibili nella classe
@NoArgsConstructor // crea il costruttore vuoto senza renderlo visibile nella classe
public class ProgressoStudio {
	@Column(name = "id_progresso")
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_progresso;
	
	@Column(name = "email_utente")
	private String email_utente;
	
	@Column(name = "meta_info") ///ma a che serve??
	private String meta_info;

	@Column(name = "arg_studiati")
	private String arg_studiati;

	@Column(name = "arg_dastudiare")
	private String arg_dastudiare;

}
