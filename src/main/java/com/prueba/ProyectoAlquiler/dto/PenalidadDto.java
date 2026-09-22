package com.prueba.ProyectoAlquiler.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PenalidadDto {
    private Integer idPenalidad;
    private String tipo_penalidad;
    private String descripcion;
    private Integer monto;
    private Date fecha_penalidad;
    private Boolean estado;

    private Integer devolucionId;
}
