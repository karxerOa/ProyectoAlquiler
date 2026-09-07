package com.prueba.ProyectoAlquiler.model;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Alquiler {

    private int idAlquiler;
    private LocalDate fechaAlquiler;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String estado;
    private BigDecimal subtotal;
    private BigDecimal total;
    private int idCliente;
    private int idTrabajador;

    // relaciones
    private Cliente cliente;
    private Empleado trabajador;
    private List<DetalleAlquiler> detalles;
    private Pago pago;
    private Devolucion devolucion;

    public BigDecimal calcularTotal() {
        if (detalles == null || detalles.isEmpty()) return BigDecimal.ZERO;
        return detalles.stream()
                .map(DetalleAlquiler::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}