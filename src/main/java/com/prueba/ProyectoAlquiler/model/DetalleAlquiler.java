package com.prueba.ProyectoAlquiler.model;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DetalleAlquiler {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idDetalle;

    @Column(nullable = false)
    private int cantidad;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precioUnitario;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal subtotal;

    // relaciones
    @ManyToOne
    @JoinColumn(name = "id_alquiler", nullable = false,
            foreignKey = @ForeignKey(name = "FK_alquiler_detalle"))
    private Alquiler alquiler;

    @ManyToOne
    @JoinColumn(name = "id_prenda", nullable = false,
            foreignKey = @ForeignKey(name = "FK_prenda_detalle"))
    private Prenda prenda;

}