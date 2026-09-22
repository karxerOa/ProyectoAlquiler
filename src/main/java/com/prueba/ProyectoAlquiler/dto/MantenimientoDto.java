package com.prueba.ProyectoAlquiler.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MantenimientoDto {
    private Integer idMantenimiento;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String tipoMantenimiento;
    private String descripcion;
    private BigDecimal costo;
    private String estado;
    private Integer prendaId;
    private Integer empleadoId;
}
