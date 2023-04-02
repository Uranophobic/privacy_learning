package com.privacy.web.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "favola")
@Data //crea i metodi hashCode, equals e toString, getter e setter senza renderli visibili nella classe
@NoArgsConstructor //crea il costruttore vuoto senza renderlo visibile nella classe
public class Favola {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idfavola;

	private String titolo_favola;
	private String testo_favola;

	private String image_path;
	private String meta_info;

	public Favola(String titolo_favola, String testo_favola, String image_path, String meta_info) {
		
		this.titolo_favola = titolo_favola;
		this.testo_favola = testo_favola;
		this.image_path = image_path;
		this.meta_info = meta_info;
	}
}
