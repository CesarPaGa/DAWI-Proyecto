package com.proyecto.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tb_Contenido")
@Data
public class Contenido {
	@Id
	private int id_con;
	private String nombre;
	private String director;
	private String descripcion;
	private String idioma;
	public double precio;
	public double puntuacion;
	public double duracion;
	public int id_tipo;
	public int id_genero;
	public int anio_publicacion;
}
