package com.prueba.ProyectoAlquiler.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name="user_data")
public class Usuario {
    @Id
    @EqualsAndHashCode.Include
    private Integer idUser;

    @Column(nullable = false, unique = true,  length = 20)
    private String  username;

    @Column(nullable = false, unique = true,  length = 100)
    private String password; // bcrypt

    @Column(nullable = false)
    private boolean enabled;

    @ManyToOne
    @JoinColumn(name="idRol", nullable = false,foreignKey = @ForeignKey(name="FK_rol_usuario"))
    private Rol rol;
}