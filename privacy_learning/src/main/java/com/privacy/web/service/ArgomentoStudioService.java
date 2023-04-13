package com.privacy.web.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.privacy.web.model.ArgomentoStudio;
@Service
public interface ArgomentoStudioService {
	public ArgomentoStudio findById(int id);
	public List<ArgomentoStudio> findAllArgomenti();
}
