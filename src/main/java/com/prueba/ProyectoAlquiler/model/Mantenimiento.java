package com.prueba.ProyectoAlquiler.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Mantenimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id_mantenimiento", nullable = false)
    private Integer idMantenimiento;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    @Column(name = "tipo_mantenimiento", nullable = false, length = 80)
    private String tipoMantenimiento;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "costo", nullable = false, precision = 10, scale = 2)
    private BigDecimal costo;

    @Column(name = "estado", nullable = false, length = 20)
    private String estado;

    //relaciones
    @ManyToOne
    @JoinColumn(name = "idPrenda", nullable = false, foreignKey = @ForeignKey(name = "FK_prenda_mantenimiento"))
    private Prenda prenda;

    @ManyToOne
    @JoinColumn(name = "idEmpeado", nullable = false, foreignKey = @ForeignKey(name = "FK_trabajador_mantenimiento"))
    private Empleado empleado;
}
