package com.prueba.ProyectoAlquiler.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class Cliente {
    private Integer idCliente;
    private String nombre;
    private String apellidos;
    private int dni;
    private int telefono;
    private String direccion;
    private String correo;
}
