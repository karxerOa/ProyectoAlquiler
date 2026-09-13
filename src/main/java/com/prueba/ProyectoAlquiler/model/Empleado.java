package com.prueba.ProyectoAlquiler.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Empleado {
    @Id
    @EqualsAndHashCode.Include
    private Integer idEmpleado;
    @Column(nullable = false, unique = true,  length = 100)
    private String nombres;
    @Column(nullable = false, unique = true,  length = 100)
    private String apellidos;
    @Column(nullable = false)
    private int dni;
    @Column(nullable = false)
    private int telefono;

    @Column(nullable = false, unique = true,  length = 80)
    private String cargo;
    @Column(nullable = false, unique = true,  length = 20)
    private String estado;

    //relacion
    @OneToMany
    private List<Produccion> producciones;

    @OneToMany
    private List<Mantenimiento> mantenimientos;

    @OneToMany
    private List<Alquiler> alquileres;

    @OneToMany
    private List<Devolucion> devoluciones;
}