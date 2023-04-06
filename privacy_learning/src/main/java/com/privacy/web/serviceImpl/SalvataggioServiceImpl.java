package com.privacy.web.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.privacy.web.model.Salvataggio;
import com.privacy.web.repository.SalvataggioRepository;
import com.privacy.web.service.SalvataggioService;

@Service
public class SalvataggioServiceImpl implements SalvataggioService {

	private SalvataggioRepository salRep;
	
	@Override
	public void save(Salvataggio s) {
		salRep.save(s);
	}

	@Override
	public List<Salvataggio> findAllSalvataggio() {
		return (List<Salvataggio>) salRep.findAll();
	}

}