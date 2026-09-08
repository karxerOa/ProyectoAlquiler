package com.prueba.ProyectoAlquiler.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idPago;

    @Column(nullable = false)
    private LocalDate fechaPago;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal monto;

    @Column(nullable = false, length = 50)
    private String metodoPago;

    @Column(nullable = false, length = 50)
    private String estado;

    // relaciones
    @ManyToOne
    @JoinColumn(name = "id_alquiler", nullable = false,
            foreignKey = @ForeignKey(name = "FK_alquiler_pago"))
    private Alquiler alquiler;
}