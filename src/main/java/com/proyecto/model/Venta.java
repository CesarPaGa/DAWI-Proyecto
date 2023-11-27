package com.proyecto.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Entity
@Table(name = "tb_venta")
@Data
public class Venta {

    @Id
    private String id_venta;

    @ManyToOne
    @JoinColumn(name = "contenido_id", insertable = true, updatable = true)
    public Contenido contenidoId;

    @NotNull
    private LocalDate fecha_registro;

    @PositiveOrZero(message = "positivos")
    private BigDecimal total;

    @ManyToOne
    @JoinColumn(name = "usuario_id", insertable = true, updatable = true)
    public Usuario usuarioId;

}
