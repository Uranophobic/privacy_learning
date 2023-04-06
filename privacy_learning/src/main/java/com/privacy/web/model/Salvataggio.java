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
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idSalvataggio; // auto
	@Column(name = "id_test")
	private int idTest;
	@Column(name = "email_utente")
	private String email_utente;
	@Column(name = "risposte")
	private String risposta;

	public Salvataggio(int idTest, @NonNull String email_utente, @NonNull String risposta) {
		this.idTest = idTest;
		this.email_utente = email_utente;
		this.risposta = risposta;
	}
}
