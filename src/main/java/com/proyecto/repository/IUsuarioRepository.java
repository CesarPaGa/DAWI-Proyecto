package com.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.model.Usuario;

public interface IUsuarioRepository extends JpaRepository <Usuario,Integer>{
	
	Usuario findByUsuaAndClave(String usua , String clave);
}
