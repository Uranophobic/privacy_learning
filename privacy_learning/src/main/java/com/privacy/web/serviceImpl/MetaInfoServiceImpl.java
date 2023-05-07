package com.privacy.web.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.privacy.web.model.MetaInfo;
import com.privacy.web.repository.MetaInfoRepository;
import com.privacy.web.service.MetaInfoService;

@Service
public class MetaInfoServiceImpl implements MetaInfoService{

	@Autowired
	private MetaInfoRepository metaRep;

	@Override
	public List<MetaInfo> findAll() {
		// TODO Auto-generated method stub
		return (List<MetaInfo>) metaRep.findAll();
	}

	@Override
	public void save(MetaInfo m) {
		metaRep.save(m);
	}

	@Override
	public void delete(String meta) {
		metaRep.deleteById(meta);
	}
	
}
