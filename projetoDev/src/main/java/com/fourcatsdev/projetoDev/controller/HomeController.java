package com.fourcatsdev.projetoDev.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	
	@RequestMapping("/")
	public String index(Model modelo) {
		modelo.addAttribute("msnBemVindo", "Seja bem vindo ao meu site!");
		
		return "pagina-index";
	}

}
