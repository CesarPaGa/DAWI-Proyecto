package com.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.proyecto.repository.IUsuarioRepository;
import com.proyecto.model.Usuario;

@Controller
public class LoginController {
	@Autowired
	private IUsuarioRepository repoUsu;

	@GetMapping("/login")
	public String cargarLogin(Model model) {
		return "login";
	}

	@GetMapping("/registro")
	public String cargarRegistro(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "registro";
	}

	@PostMapping("/login")
	public String login(@RequestParam("txtUsuario") String usuario, @RequestParam("txtClave") String clave,
			Model model) {
		Usuario usu = repoUsu.findByUsuaAndClave(usuario, clave);
		if (usu != null) {
			model.addAttribute("Mensaje", "Bivenido " + usu.getNombres());
			return "home";
		} else {
			model.addAttribute("mensaje", "El usuario o la contraseña son incorrectos");
			model.addAttribute("cssmensaje", "alert alert-warning m-3");
			return "login";
		}

	}

	@PostMapping("/registro")
	public String registro(@ModelAttribute Usuario usuario, Model model) {

		try {
			usuario.setId_tipo(2);
			repoUsu.save(usuario);
			return "login";
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("usuario", usuario);
			model.addAttribute("mensaje", "Error al registrar, el usuario ya existe");
			model.addAttribute("cssmensaje", "alert alert-warning m-3");
			return "registro";
		}
	}
}
