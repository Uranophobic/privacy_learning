package com.privacy.web.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "meta_info")
@Data //crea i metodi hashCode, equals e toString, getter e setter senza renderli visibili nella classe
@NoArgsConstructor //crea il costruttore vuoto senza renderlo visibile nella classe

public class MetaInfo {
	
	@Column(name="keyword") @Id
	private String keyword;

	/**
	 * @param meta_info
	 */
	public MetaInfo(String keyword) {
		super();
		this.keyword = keyword;
	}

	
}
