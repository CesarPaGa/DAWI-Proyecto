package com.proyecto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	@GetMapping("/")
	public String df(Model model) {
		return "redirect:/login";
	}

	@GetMapping("/inicio")
	public String cargarInicio(Model model) {
		return "home";
	}
}
