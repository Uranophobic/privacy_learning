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
import lombok.NonNull;
import lombok.Setter;

@Entity
@Table(name = "progresso_studio")
@Data //crea i metodi hashCode, equals e toString, getter e setter senza renderli visibili nella classe
@NoArgsConstructor //crea il costruttore vuoto senza renderlo visibile nella classe
public class Suggerimento {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private int idSuggerimento;
	
	private String email;
	private int idTest;
	
	private String argSuggerito;
	private boolean argStudiato;
	
	public Suggerimento(@NonNull String email, @NonNull int idTest, @NonNull String argSuggerito,@NonNull boolean argStudiato) {
		this.email = email;
		this.idTest = idTest;
		this.argSuggerito = argSuggerito;
		this.argStudiato = argStudiato;
	}
}
