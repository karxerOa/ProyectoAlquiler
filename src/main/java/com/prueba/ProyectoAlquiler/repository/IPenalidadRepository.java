package com.prueba.ProyectoAlquiler.repository;

import com.prueba.ProyectoAlquiler.model.Cliente;
import com.prueba.ProyectoAlquiler.model.Penalidad;
import org.springframework.stereotype.Repository;

@Repository
public interface IPenalidadRepository extends IGenericRepository<Penalidad, Integer> {
}

