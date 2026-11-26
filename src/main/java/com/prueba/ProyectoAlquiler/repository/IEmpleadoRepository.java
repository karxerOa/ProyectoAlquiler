package com.prueba.ProyectoAlquiler.repository;

import com.prueba.ProyectoAlquiler.model.Empleado;

import java.util.Optional;

public interface IEmpleadoRepository extends IGenericRepository<Empleado, Integer> {
    Optional<Empleado> findByUsuarioUsername(String username);
}
