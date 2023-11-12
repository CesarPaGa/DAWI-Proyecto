package com.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.proyecto.model.Usuario;
import com.proyecto.repository.ITipoUserRepository;
import com.proyecto.repository.IUsuarioRepository;

@Controller
public class UsuarioController {
	@Autowired
	private ITipoUserRepository repoTipoUser;	
	@Autowired
	private IUsuarioRepository repoUsuario;
	
	
	@GetMapping("/mantenimiento/usuario")
	public String cargarMantUsuario(Model model) {
		model.addAttribute("usuario", new Usuario());
		model.addAttribute("lstUsuarios", repoUsuario.findAll());
		model.addAttribute("lstTipoUser", repoTipoUser.findAll());
		return "crudusuario";
	}
	@GetMapping("/editaruser/{id_usuario}")
	public String editarUsuario(@PathVariable int id_usuario, Model model) {
		Usuario c = repoUsuario.findById(id_usuario).get();
		model.addAttribute("usuario", c);
		model.addAttribute("lstUsuarios", repoUsuario.findAll());
		model.addAttribute("lstTipoUser", repoTipoUser.findAll());
		return "crudusuario";
	}
	
	@PostMapping("/guardaruser")
	public String guardarUsuario(@ModelAttribute Usuario usuario, Model model) {
		try {
			repoUsuario.save(usuario);
			model.addAttribute("mensaje", "Informacion Guardada Correctamente");
			model.addAttribute("cssmensaje", "alert alert-success");
		} catch (Exception e) {
			System.out.println(e.getCause().getMessage());
			model.addAttribute("mensaje", "Error en el Guardado de Informacion");
			model.addAttribute("cssmensaje", "alert alert-danger");
		}
		return "redirect:/mantenimiento/usuario";
	}
	@GetMapping("/eliminaruser/{id_usuario}")
	public String eliminarUsuario(@PathVariable int id_usuario, Model model) {
		try {
			Usuario c = repoUsuario.findById(id_usuario).get();
			repoUsuario.delete(c);
			return "redirect:/mantenimiento/usuario";
		} catch (Exception e) {
			return "redirect:/mantenimiento/usuario";
		}
	}
}
