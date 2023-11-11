package com.proyecto.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="tb_usuario")
@Data
public class Usuario {
	@Id
	private int idUsuario ;
	private String nombres ;
	private String usua ;
	private String clave ;
	private int idTipo  ;
}
