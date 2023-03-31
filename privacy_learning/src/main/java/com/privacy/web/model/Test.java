package com.privacy.web.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "test")
public class Test {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private int idTest;
	
	private String tipo;
	
	
	
	public Test() {	}

	public Test(int idTest, String tipo) {
		this.idTest = idTest;
		this.tipo = tipo;
	}


	public int getIdTest() {
		return idTest;
	}


	public void setIdTest(int idTest) {
		this.idTest = idTest;
	}
	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Test [idTest=" + idTest + ", tipologia test=" + tipo + ","+ "]";
	}


	
	
	
	

}
