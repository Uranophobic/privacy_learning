package com.privacy.web.control;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.privacy.web.model.Domanda;
import com.privacy.web.service.DomandaService;

import jakarta.servlet.annotation.WebServlet;

@WebServlet
@Controller
public class TestControl {

	@Autowired
	private DomandaService domServ;

	
}
