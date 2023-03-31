package com.privacy.web.model;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Lista {
	
	private List<Domanda> lista;
	
	public List<Domanda> getLista() {
		return lista;
	}
	public void setLista(List<Domanda> lista) {
		this.lista = lista;
	}
	

	
}
