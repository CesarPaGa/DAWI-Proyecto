package com.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.proyecto.repository.IContenidoRepository;

@Controller
public class HomeController {
	
	@Autowired
	private IContenidoRepository repoContenido;
	
	@GetMapping("/")
	public String df(Model model) {
		return "redirect:/login";
	}

	@GetMapping("/inicio")
	public String cargarInicio(Model model) {
		long cantidadPeliculas = repoContenido.countByTipo("Pelicula");
	    long cantidadSeries = repoContenido.countByTipo("Serie");
	    long cantidadTrailers = repoContenido.countByTipo("Trailer");

	    // Otros datos que quieras enviar
	    int cantidadUsuarios = repoUsuario.count(); // Por ejemplo, la cantidad de usuarios

	    // Agregar los datos al modelo
	    model.addAttribute("cantidadPeliculas", cantidadPeliculas);
	    model.addAttribute("cantidadSeries", cantidadSeries);
	    model.addAttribute("cantidadTrailers", cantidadTrailers);
	    model.addAttribute("cantidadUsuarios", cantidadUsuarios);
		return "home";
	}
}
