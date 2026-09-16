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
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idCliente;
    @Column(nullable = false,  length = 50)
    private String nombre;
    @Column(nullable = false,  length = 50)
    private String apellidos;
    @Column(nullable = false, unique = true)
    private Integer dni;
    @Column(nullable = false, unique = true)
    private Integer telefono;

    @Column(nullable = false,  length = 100)
    private String direccion;
    @Column(nullable = false, unique = true,  length = 100)
    private String correo;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "cliente")
    private List<Alquiler> alquileres;


}
