package com.prueba.ProyectoAlquiler.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Produccion {

    @Id
    @EqualsAndHashCode.Include
    private Integer idProduccion;

    @Column(nullable = false)
    private LocalDate fechaProduccion;

    @Column(nullable = false)
    private int cantidad;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal costoProduccion;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    // relaciones
    @ManyToOne
    @JoinColumn(name = "id_prenda", nullable = false,
            foreignKey = @ForeignKey(name = "FK_prenda_produccion"))
    private Prenda prenda;

    @ManyToOne
    @JoinColumn(name = "id_empleado", nullable = false,
            foreignKey = @ForeignKey(name = "FK_trabajador_produccion"))
    private Empleado empleado;
}