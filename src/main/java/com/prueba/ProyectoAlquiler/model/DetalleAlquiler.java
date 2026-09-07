package com.prueba.ProyectoAlquiler.model;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleAlquiler {

    private int idDetalle;
    private int idAlquiler;
    private int idPrenda;
    private int cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal subtotal;

    // relaciones
    private Alquiler alquiler;
    private Prenda prenda;


    public void calcularSubtotal() {
        if (this.precioUnitario != null) {
            this.subtotal = this.precioUnitario.multiply(BigDecimal.valueOf(this.cantidad));
        }
    }
}