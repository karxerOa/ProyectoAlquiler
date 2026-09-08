package com.prueba.ProyectoAlquiler.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Rol {
    @Id
    @EqualsAndHashCode.Include
    private Integer idRole;

    @Column(nullable = false, unique = true,  length = 20)
    private String name;

    @Column(nullable = false, unique = true,  length = 100)
    private String description;
}
