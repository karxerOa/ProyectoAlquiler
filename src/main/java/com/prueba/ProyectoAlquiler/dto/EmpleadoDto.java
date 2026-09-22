package com.prueba.ProyectoAlquiler.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadoDto {
    private Integer idEmpleado;
    private String nombres;
    private String apellidos;
    private Integer dni;
    private Integer telefono;
    private String cargo;
    private String estado;
}
