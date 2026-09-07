package com.prueba.ProyectoAlquiler.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

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

    //relaciones
    @ManyToOne
    @JoinColumn(name="idEmpledado", nullable = false,foreignKey = @ForeignKey(name="FK_empleado_devolucion"))
    private  Empleado empleado;
}