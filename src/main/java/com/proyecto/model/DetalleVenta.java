// package com.proyecto.model;

// import java.math.BigDecimal;

// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.ManyToOne;
// import jakarta.persistence.Table;
// import jakarta.validation.constraints.Min;
// import jakarta.validation.constraints.PositiveOrZero;
// import lombok.Data;

// @Entity
// @Table(name = "tb_detalle_venta")
// @Data
// public class DetalleVenta {

// @Id
// @GeneratedValue(strategy = GenerationType.IDENTITY)
// private int id_detalle;

// @ManyToOne
// @JoinColumn(name = "venta_id", insertable = false, updatable = false)
// public Venta ventaId;

// @ManyToOne
// @JoinColumn(name = "contenido_id", insertable = false, updatable = false)
// public Contenido contenidoId;

// @Min(0)
// private int cantidad;

// @PositiveOrZero
// private BigDecimal precio;

// }
