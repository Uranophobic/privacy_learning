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


@Entity(name = "argomento_studio")
@Table(name = "argomento_studio")
@Data //crea i metodi hashCode, equals e toString, getter e setter senza renderli visibili nella classe
@NoArgsConstructor //crea il costruttore vuoto senza renderlo visibile nella classe

public class ArgomentoStudio {
	@Column(name = "id_studio")
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_studio;
	@Column(name = "titolo")
	private String titolo;
	@Column(name = "descrizione")
	private String descrizione;
	@Column(name = "link_video")
	private String linkvideo;
	@Column(name = "meta_info")
	private String meta_info;
	
	public ArgomentoStudio(@NonNull String titolo,@NonNull String descrizione,@NonNull String link_video,@NonNull String meta_info) {
		this.titolo = titolo;
		this.descrizione = descrizione;
		this.linkvideo = link_video;
		this.meta_info = meta_info;
	}
	
	
}

