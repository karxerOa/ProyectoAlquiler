package com.prueba.ProyectoAlquiler.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadoDto {
    private Integer idEmpleado;
    private Integer idUser;
    private String username;
    @com.fasterxml.jackson.annotation.JsonProperty(access = com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY)
    @lombok.ToString.Exclude
    private String password;
    private String nombres;
    private String apellidos;
    private String dni;
    private String telefono;
    private String cargo;
    private String estado;
}
