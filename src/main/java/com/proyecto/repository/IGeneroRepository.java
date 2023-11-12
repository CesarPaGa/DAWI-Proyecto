package com.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.model.Genero;


@Repository
public interface IGeneroRepository extends JpaRepository<Genero, Integer>{
	
}