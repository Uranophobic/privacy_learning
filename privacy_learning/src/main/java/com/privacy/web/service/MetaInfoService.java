package com.privacy.web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.privacy.web.model.MetaInfo;

@Service
public interface MetaInfoService {
	public List<MetaInfo> findAll();
}
