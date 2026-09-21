package com.prueba.ProyectoAlquiler.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlquilerDto {
    private Integer idAlquiler;
    private LocalDate fechaAlquiler;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String estado;
    private BigDecimal subtotal;
    private BigDecimal total;
    private Integer empleadoId;
    private Integer clienteId;
}
