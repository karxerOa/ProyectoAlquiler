package com.prueba.ProyectoAlquiler.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idRol;

    @Column(nullable = false, unique = true, length = 20)
    private String name;

    @Column(nullable = false, length = 100)
    private String description;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "rol")
    private List<Usuario> usuarios;

}
