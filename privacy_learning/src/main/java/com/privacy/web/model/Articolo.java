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
@Table(name = "articolo")
@Data //crea i metodi hashCode, equals e toString, getter e setter senza renderli visibili nella classe
@NoArgsConstructor //crea il costruttore vuoto senza renderlo visibile nella classe
public class Articolo {
	@Column(name="id_articolo")
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_articolo;
	@Column(name="link")
	private String link;
	@Column(name="titolo_articolo")
	private String titolo;
	@Column(name="meta_info")
	private String metaInfo;

	public Articolo(String link, String titolo, String metaInfo) {
		this.link = link;
		this.titolo = titolo;
		this.metaInfo = metaInfo;
	}
	
}
