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

@Entity
@Table(name = "test")
@Data //crea i metodi hashCode, equals e toString, getter e setter senza renderli visibili nella classe
@NoArgsConstructor //crea il costruttore vuoto senza renderlo visibile nella classe
@AllArgsConstructor //crea il costruttore con paramentri senza renderlo visibile nella classe
public class Test {
@Column(name="id_test")
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_test;	
	@Column(name="tipo_test")
	private String tipo_test;

	
}
