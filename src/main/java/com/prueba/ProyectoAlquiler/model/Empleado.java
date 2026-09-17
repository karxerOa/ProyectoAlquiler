package com.prueba.ProyectoAlquiler.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Empleado {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer idEmpleado;
    @Column(nullable = false,  length = 100)
    private String nombres;
    @Column(nullable = false,  length = 100)
    private String apellidos;
    @Column(nullable = false, unique = true)
    private Integer dni;
    @Column(nullable = false, unique = true)
    private Integer telefono;

    @Column(nullable = false,  length = 80)
    private String cargo;
    @Column(nullable = false,  length = 20)
    private String estado;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "empleado")
    private List<Produccion> producciones;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "empleado")
    private List<Mantenimiento> mantenimientos;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "empleado")
    private List<Alquiler> alquileres;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "empleado")
    private List<Devolucion> devoluciones;
}