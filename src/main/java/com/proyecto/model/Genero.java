package com.proyecto.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="tb_genero")
@Data
public class Genero {
	@Id
	private int id_genero;
	private String genero;
}
