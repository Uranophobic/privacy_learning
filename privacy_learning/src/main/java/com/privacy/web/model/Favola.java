package com.privacy.web.model;

import jakarta.persistence.Column;
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
	@Column(name="id_favola")
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_favola;
	@Column(name="titolo_favola")
	private String titolofavola;
	@Column(name="testo_favola")
	private String testofavola;
	@Column(name="image_path")
	private String image_path;
	@Column(name="meta_info")
	private String meta_info;

	public Favola(String titolo_favola, String testo_favola, String image_path, String meta_info) {
		
		this.titolofavola = titolo_favola;
		this.testofavola = testo_favola;
		this.image_path = image_path;
		this.meta_info = meta_info;
	}
}
