package com.prueba.ProyectoAlquiler.model;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Alquiler {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idAlquiler;

    private LocalDate fechaAlquiler;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String estado;
    private BigDecimal subtotal;
    private BigDecimal total;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "alquiler")
    private List<DetalleAlquiler> detalles;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "alquiler")
    private List<Pago> pagos;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "alquiler")
    private List<Devolucion> devoluciones;

    @ManyToOne
    @JoinColumn(name = "id_empleado", nullable = false)
    private Empleado empleado;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;
}