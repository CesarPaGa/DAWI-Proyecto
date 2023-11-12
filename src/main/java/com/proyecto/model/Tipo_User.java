package com.proyecto.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="tb_tipo_user")
@Data
public class Tipo_User {
	@Id
	private int id_tipo;
	private String tipo;
}
