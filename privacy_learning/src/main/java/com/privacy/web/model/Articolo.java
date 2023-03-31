package com.privacy.web.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "articolo")
public class Articolo {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private int idArticolo;
	private String link;
	private String titolo;
	private String metaInfo;
	
	public Articolo() {}

	public Articolo(String link, String titolo, String metaInfo) {
		this.link = link;
		this.titolo = titolo;
		this.metaInfo = metaInfo;
	}
	
	
	public int getIdArticolo() {
		return idArticolo;
	}
	
	public void setIdArticolo(int idArticolo) {
		this.idArticolo = idArticolo;
	}
	
	public String getLink() {
		return link;
	}
	
	public void setLink(String link) {
		this.link = link;
	}
	
	public String getTitolo() {
		return titolo;
	}
	
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	
	public String getMetaInfo() {
		return metaInfo;
	}
	
	public void setMetaInfo(String metaInfo) {
		this.metaInfo = metaInfo;
	}
	
	@Override
	public String toString() {
		return "Articolo [idArticolo=" + idArticolo + ", link=" + link + ", titolo=" + titolo + ", metaInfo=" + metaInfo
				+ "]";
	}
	
	
}
