package com.privacy.web.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "meta_info")
public class MetaInfo {

	@Id 
	private String meta_info;

	public MetaInfo() {}

	/**
	 * @param meta_info
	 */
	public MetaInfo(String meta_info) {
		super();
		this.meta_info = meta_info;
	}

	public String getMeta_info() {
		return meta_info;
	}

	public void setMeta_info(String meta_info) {
		this.meta_info = meta_info;
	}
	
	
}
