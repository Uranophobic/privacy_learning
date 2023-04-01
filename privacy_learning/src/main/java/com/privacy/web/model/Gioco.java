package com.privacy.web.model;

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
@Table(name = "gioco")
@Data //crea i metodi hashCode, equals e toString, getter e setter senza renderli visibili nella classe
@NoArgsConstructor //crea il costruttore vuoto senza renderlo visibile nella classe
public class Gioco {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private int idGioco;
	private String titolo;
	private String descrizione;
	private String meta_info;
	
	public Gioco(String titolo, String descrizione, String meta_info) {
		this.titolo = titolo;
		this.descrizione = descrizione;
		this.meta_info = meta_info;
	}
}
