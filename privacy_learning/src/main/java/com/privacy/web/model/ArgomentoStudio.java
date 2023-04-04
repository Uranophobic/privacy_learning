package com.privacy.web.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


@Entity
@Table(name = "argomento_studio")
@Data //crea i metodi hashCode, equals e toString, getter e setter senza renderli visibili nella classe
@NoArgsConstructor //crea il costruttore vuoto senza renderlo visibile nella classe

public class ArgomentoStudio {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private int idStudio;
	
	private String titolo;
	private String descrizione;
	private String link_video;
	private String meta_info;
	
	public ArgomentoStudio(@NonNull String titolo,@NonNull String descrizione,@NonNull String link_video,@NonNull String meta_info) {
		this.titolo = titolo;
		this.descrizione = descrizione;
		this.link_video = link_video;
		this.meta_info = meta_info;
	}
	
	
}

