package com.prueba.ProyectoAlquiler.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Penalidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idPenalidad;
    @Column(nullable = false, length = 50)
    private String tipo_penalidad;
    @Column(nullable = false, length = 50)
    private String descripcion;
    @Column(nullable = false, precision = 10, scale = 2)
    private java.math.BigDecimal monto;
    private LocalDateTime fecha_penalidad;
    private Boolean estado;

    // relaciones
    @ManyToOne
    @JoinColumn(name = "id_devolucion", nullable = false,
    foreignKey = @ForeignKey(name = "FK_devolucion_penalidad"))
    private Devolucion devolucion;
}
