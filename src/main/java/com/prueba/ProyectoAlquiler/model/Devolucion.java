package com.prueba.ProyectoAlquiler.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Devolucion {
    @Id
    @EqualsAndHashCode.Include
    private Integer idDevolucion;
    @Column(nullable = false, unique = true,  length = 50)
    private Date fechaDevolucion;
    @Column(nullable = false, unique = true,  length = 50)
    private String estadoprenda;
    @Column(nullable = false)
    private String observacion;

    // relaciones
    @ManyToOne
    @JoinColumn(name = "id_alquiler", nullable = false,
            foreignKey = @ForeignKey(name = "FK_alquiler_devolucion"))
    private Alquiler alquiler;

    @ManyToOne
    @JoinColumn(name = "id_empleado", nullable = false,
            foreignKey = @ForeignKey(name = "FK_trabajador_devolucion"))
    private Empleado empleado;

    @OneToMany
    private List<Penalidad> penalidades;
}