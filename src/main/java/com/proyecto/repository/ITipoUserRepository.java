package com.proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.model.Tipo_User;

@Repository
public interface ITipoUserRepository extends JpaRepository<Tipo_User, Integer>{

}
