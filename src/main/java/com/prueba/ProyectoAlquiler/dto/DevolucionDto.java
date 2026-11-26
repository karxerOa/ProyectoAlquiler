package com.prueba.ProyectoAlquiler.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DevolucionDto {

    private Integer idDevolucion;
    private LocalDateTime fechaDevolucion;
    private String estadoPrenda;
    private String observacion;

    private Integer alquilerId;
    private Integer empleadoId;

}
