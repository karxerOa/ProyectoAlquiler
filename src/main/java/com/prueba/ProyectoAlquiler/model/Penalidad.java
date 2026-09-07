package com.prueba.ProyectoAlquiler.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.el.parser.BooleanNode;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Penalidad {
    @Id
    @EqualsAndHashCode.Include
    private Integer idPenalidad;
    @Column(nullable = false, unique = true,  length = 50)
    private String tipo_penalidad;
    @Column(nullable = false, unique = true,  length = 50)
    private String descripcion;
    @Column(nullable = false)
    private Integer monto;
    private Date fecha_penalidad;
    private Boolean estado;

    //relaciones
    @ManyToOne
    @JoinColumn(name="iddevolucion", nullable = false,foreignKey = @ForeignKey(name="FK_devolucion_penalidad"))
    private  Devolucion devolucion;
}