package com.privacy.web.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "favola")
public class Favola {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idfavola;

	private String titolo_favola;
	private String testo_favola;

	private String image_path;
	private String meta_info;

	public Favola() {
	}

	public Favola(String titolo_favola, String testo_favola, String image_path, String meta_info) {
		super();
		this.titolo_favola = titolo_favola;
		this.testo_favola = testo_favola;
		this.image_path = image_path;
		this.meta_info = meta_info;
	}

	public int getIdfavola() {
		return idfavola;
	}

	public void setIdfavola(int idfavola) {
		this.idfavola = idfavola;
	}

	public String getTitolo() {
		return titolo_favola;
	}

	public void setTitolo(String titolo) {
		this.titolo_favola = titolo_favola;
	}

	public String getTesto() {
		return testo_favola;
	}

	public void setTesto(String testo) {
		this.testo_favola = testo_favola;
	}

	public String getImg() {
		return image_path;
	}

	public void setImg(String img) {
		this.image_path = image_path;
	}

	public String getMetaInfo() {
		return meta_info;
	}

	public void setMetaInfo(String metaInfo) {
		this.meta_info = meta_info;
	}

	@Override
	public String toString() {
		return "favola [idfavola=" + idfavola + ", titolo_favola=" + titolo_favola + ", testo_favola=" + testo_favola
				+ ", image_path=" + image_path + ", meta_info=" + meta_info + "]";
	}

}
