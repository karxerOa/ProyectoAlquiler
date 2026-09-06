package com.prueba.ProyectoAlquiler.model;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

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

    // Constructor vacío
    public Alquiler() {}

    // Constructor completo
    public Alquiler(int idAlquiler, LocalDate fechaAlquiler, LocalDate fechaInicio,
                    LocalDate fechaFin, String estado, BigDecimal subtotal,
                    BigDecimal total, int idCliente, int idTrabajador) {
        this.idAlquiler    = idAlquiler;
        this.fechaAlquiler = fechaAlquiler;
        this.fechaInicio   = fechaInicio;
        this.fechaFin      = fechaFin;
        this.estado        = estado;
        this.subtotal      = subtotal;
        this.total         = total;
        this.idCliente     = idCliente;
        this.idTrabajador  = idTrabajador;
    }

    // Getters y Setters
    public int getIdAlquiler() { return idAlquiler; }
    public void setIdAlquiler(int idAlquiler) { this.idAlquiler = idAlquiler; }

    public LocalDate getFechaAlquiler() { return fechaAlquiler; }
    public void setFechaAlquiler(LocalDate fechaAlquiler) { this.fechaAlquiler = fechaAlquiler; }

    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }

    public LocalDate getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDate fechaFin) { this.fechaFin = fechaFin; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public BigDecimal getSubtotal() { return subtotal; }
    public void setSubtotal(BigDecimal subtotal) { this.subtotal = subtotal; }

    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }

    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    public int getIdTrabajador() { return idTrabajador; }
    public void setIdTrabajador(int idTrabajador) { this.idTrabajador = idTrabajador; }

    @Override
    public String toString() {
        return "Alquiler{" +
                "idAlquiler=" + idAlquiler +
                ", fechaAlquiler=" + fechaAlquiler +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", estado='" + estado + '\'' +
                ", subtotal=" + subtotal +
                ", total=" + total +
                ", idCliente=" + idCliente +
                ", idTrabajador=" + idTrabajador +
                '}';
    }
}
