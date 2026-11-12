package com.prueba.ProyectoAlquiler.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Devolucion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idDevolucion;
    @Column(nullable = false, length = 50)
    private LocalDateTime fechaDevolucion;
    @Column(nullable = false, length = 50)
    private String estadoPrenda;
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

    @com.fasterxml.jackson.annotation.JsonIgnore
    @lombok.ToString.Exclude
    @OneToMany(mappedBy = "devolucion")
    private List<Penalidad> penalidades;
}
