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
public class Suggerimento {
	@Column(name = "id_suggerimento")
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idSuggerimento;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "meta_info")
	private String metainfo;
	
	@Column(name = "id_test")
	private int idTest;
/*
	@Column(name = "titolo_argomento")
	private String titoloSuggerito;
*/	
	//non so se toglierlo. vorrei eliminare la tupla una volta aperto un argomento con quella meta_info
	@Column(name = "studiato")
	private boolean argStudiato;

	public Suggerimento(@NonNull String email, String metainfo, int idTest, @NonNull String titArgSuggerito, boolean argStudiato) {
		this.email = email;
		this.metainfo=metainfo;
		this.idTest= idTest;
		this.argStudiato = argStudiato;
		
	}
}
