package com.prueba.ProyectoAlquiler.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProduccionDto {
    private Integer idProduccion;
    private LocalDate fechaProduccion;
    private int cantidad;
    private BigDecimal costoProduccion;
    private String descripcion;
    private Integer idPrenda;
    private Integer idEmpleado;
}
