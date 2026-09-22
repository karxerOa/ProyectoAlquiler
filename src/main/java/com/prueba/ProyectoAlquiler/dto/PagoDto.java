package com.prueba.ProyectoAlquiler.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagoDto {
    private LocalDate fechaPago;
    private BigDecimal monto;
    private String metodoPago;
    private String estado;
    private Integer alquilerId;
}
