package com.proyecto.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tb_venta")
@Data
public class Venta {

    @Id
    private int id_ven;
    private String fec_compra;
    private int id_con;
    private String nombre;
    private Double precio;
    private int id_tipo;
    private int id_genero;
}
