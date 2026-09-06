package com.prueba.ProyectoAlquiler.model;
import java.math.BigDecimal;

public class DetalleAlquiler {
    private int idDetalle;
    private int idAlquiler;
    private int idPrenda;
    private int cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal subtotal;

    public DetalleAlquiler() {}

    public DetalleAlquiler(int idDetalle, int idAlquiler, int idPrenda,
                           int cantidad, BigDecimal precioUnitario, BigDecimal subtotal) {
        this.idDetalle      = idDetalle;
        this.idAlquiler     = idAlquiler;
        this.idPrenda       = idPrenda;
        this.cantidad       = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal       = subtotal;
    }

    public int getIdDetalle() { return idDetalle; }
    public void setIdDetalle(int idDetalle) { this.idDetalle = idDetalle; }

    public int getIdAlquiler() { return idAlquiler; }
    public void setIdAlquiler(int idAlquiler) { this.idAlquiler = idAlquiler; }

    public int getIdPrenda() { return idPrenda; }
    public void setIdPrenda(int idPrenda) { this.idPrenda = idPrenda; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public BigDecimal getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(BigDecimal precioUnitario) { this.precioUnitario = precioUnitario; }

    public BigDecimal getSubtotal() { return subtotal; }
    public void setSubtotal(BigDecimal subtotal) { this.subtotal = subtotal; }

    @Override
    public String toString() {
        return "DetalleAlquiler{" +
                "idDetalle=" + idDetalle +
                ", idAlquiler=" + idAlquiler +
                ", idPrenda=" + idPrenda +
                ", cantidad=" + cantidad +
                ", precioUnitario=" + precioUnitario +
                ", subtotal=" + subtotal +
                '}';
    }
}
