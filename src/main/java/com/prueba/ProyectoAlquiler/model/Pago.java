package com.prueba.ProyectoAlquiler.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Pago {
    @Id
    @EqualsAndHashCode.Include
    private Integer idPago;
    @Column(nullable = false)
    private Date fechaPago;
    @Column(nullable = false, length = 50)
    private BigDecimal monto;
    @Column(nullable = false, length = 50)
    private String metodoPago;
    @Column(nullable = false, length = 50)
    private String estado;

    //relaciones
    @ManyToOne
    @JoinColumn(name = "idAlquiler", nullable = false, foreignKey = @ForeignKey(name = "FK_Alquiler_Pago"))
    private Alquiler alquiler;
}