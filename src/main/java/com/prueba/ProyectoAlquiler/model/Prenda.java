package com.prueba.ProyectoAlquiler.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "prenda")
public class Prenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prenda")
    private int id_prenda;

    @Column(name = "codigo_prenda", length = 80, nullable = false)
    private String codigo_prenda;

    @Column(name = "nombre", length = 80, nullable = false)
    private String nombre;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "talla", length = 80)
    private String talla;

    @Column(name = "color", length = 80)
    private String color;

    @Column(name = "precio_alquiler", precision = 10, scale = 2)
    private BigDecimal precio_alquiler;

    @Column(name = "estado", length = 80)
    private String estado;

    @Column(name = "id_categoria")
    private int id_categoria;

    public Prenda() {
    }

    public Prenda(int id_prenda, String codigo_prenda, String nombre, String descripcion,
                  String talla, String color, BigDecimal precio_alquiler, String estado, int id_categoria) {
        this.id_prenda = id_prenda;
        this.codigo_prenda = codigo_prenda;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.talla = talla;
        this.color = color;
        this.precio_alquiler = precio_alquiler;
        this.estado = estado;
        this.id_categoria = id_categoria;
    }

    public int getId_prenda() {
        return id_prenda;
    }

    public void setId_prenda(int id_prenda) {
        this.id_prenda = id_prenda;
    }

    public String getCodigo_prenda() {
        return codigo_prenda;
    }

    public void setCodigo_prenda(String codigo_prenda) {
        this.codigo_prenda = codigo_prenda;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public BigDecimal getPrecio_alquiler() {
        return precio_alquiler;
    }

    public void setPrecio_alquiler(BigDecimal precio_alquiler) {
        this.precio_alquiler = precio_alquiler;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }
}
