package com.prueba.ProyectoAlquiler.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Mantenimiento {
    private int id_mantenimiento;
    private LocalDate fecha_inicio;
    private LocalDate fecha_fin;
    private String tipo_mantenimiento;
    private String descripcion;
    private BigDecimal costo;
    private String estado;
    private int id_prenda;
    private int id_trabajador;

    public Mantenimiento() {
    }

    public Mantenimiento(int id_mantenimiento, LocalDate fecha_inicio, LocalDate fecha_fin,
                                String tipo_mantenimiento, String descripcion, BigDecimal costo,
                                                String estado, int id_prenda, int id_trabajador) {
        this.id_mantenimiento = id_mantenimiento;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.tipo_mantenimiento = tipo_mantenimiento;
        this.descripcion = descripcion;
        this.costo = costo;
        this.estado = estado;
        this.id_prenda = id_prenda;
        this.id_trabajador = id_trabajador;
    }

    public int getId_mantenimiento() {
        return id_mantenimiento;
    }

    public void setId_mantenimiento(int id_mantenimiento) {
        this.id_mantenimiento = id_mantenimiento;
    }

    public LocalDate getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(LocalDate fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public LocalDate getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(LocalDate fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public String getTipo_mantenimiento() {
        return tipo_mantenimiento;
    }

    public void setTipo_mantenimiento(String tipo_mantenimiento) {
        this.tipo_mantenimiento = tipo_mantenimiento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getId_prenda() {
        return id_prenda;
    }

    public void setId_prenda(int id_prenda) {
        this.id_prenda = id_prenda;
    }

    public int getId_trabajador() {
        return id_trabajador;
    }

    public void setId_trabajador(int id_trabajador) {
        this.id_trabajador = id_trabajador;
    }
}
