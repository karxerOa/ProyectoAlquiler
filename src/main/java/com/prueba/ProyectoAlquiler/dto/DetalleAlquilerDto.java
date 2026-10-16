package com.prueba.ProyectoAlquiler.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleAlquilerDto {
    private Integer idDetalle;
    private int cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal subtotal;
    private Integer alquilerId;
    private Integer prendaId;
}
