package com.privacy.web.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "argomento_studio")
public class Studio {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private int idStudio;
	
	private String titolo;
	private String descrizione;
	private String link_video;
	private String meta_info;
	
	public Studio() {}

	public int getIdStudio() {
		return idStudio;
	}

	public void setIdStudio(int idStudio) {
		this.idStudio = idStudio;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getLink_video() {
		return link_video;
	}

	public void setLink_video(String link_video) {
		this.link_video = link_video;
	}

	public String getMeta_info() {
		return meta_info;
	}

	public void setMeta_info(String meta_info) {
		this.meta_info = meta_info;
	}

	@Override
	public String toString() {
		return "Studio [idStudio=" + idStudio + ", titolo=" + titolo + ", descrizione=" + descrizione + ", link_video="
				+ link_video + ", meta_info=" + meta_info + "]";
	}

}

