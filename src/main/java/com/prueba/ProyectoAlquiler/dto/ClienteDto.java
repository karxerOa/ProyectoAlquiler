package com.prueba.ProyectoAlquiler.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDto {
    private Integer idCliente;
    private String nombre;
    private String apellidos;
    private Integer dni;
    private Integer telefono;
    private String direccion;
    private String correo;
}
