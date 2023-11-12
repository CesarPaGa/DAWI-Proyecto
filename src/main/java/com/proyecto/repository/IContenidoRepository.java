package com.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.proyecto.model.Contenido;

@Repository
public interface IContenidoRepository extends JpaRepository<Contenido, Integer>{
	
}
