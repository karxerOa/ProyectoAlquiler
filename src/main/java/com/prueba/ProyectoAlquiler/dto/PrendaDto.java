package com.prueba.ProyectoAlquiler.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrendaDto {
    private Integer idPrenda;
    private String nombre;
    private String descripcion;
    private String talla;
    private String color;
    private BigDecimal precioAlquiler;
    private String estado;

    private String categoriaNombre;
    private Integer categoriaId;
}
