package com.prueba.ProyectoAlquiler.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Cliente {
    @Id
    @EqualsAndHashCode.Include
    private Integer idCliente;
    @Column(nullable = false, unique = true,  length = 50)
    private String nombre;
    @Column(nullable = false, unique = true,  length = 50)
    private String apellidos;
    @Column(nullable = false)
    private int dni;
    @Column(nullable = false)
    private int telefono;

    @Column(nullable = false, unique = true,  length = 100)
    private String direccion;
    @Column(nullable = false, unique = true,  length = 100)
    private String correo;
}
