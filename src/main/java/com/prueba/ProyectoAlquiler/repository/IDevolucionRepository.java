package com.prueba.ProyectoAlquiler.repository;

import com.prueba.ProyectoAlquiler.model.Alquiler;
import com.prueba.ProyectoAlquiler.model.Cliente;
import com.prueba.ProyectoAlquiler.model.Devolucion;
import org.springframework.stereotype.Repository;

@Repository
public interface IDevolucionRepository extends IGenericRepository<Devolucion, Integer> {
}

