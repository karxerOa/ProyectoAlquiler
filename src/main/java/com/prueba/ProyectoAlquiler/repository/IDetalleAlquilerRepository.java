package com.prueba.ProyectoAlquiler.repository;

import com.prueba.ProyectoAlquiler.model.DetalleAlquiler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDetalleAlquilerRepository extends JpaRepository<DetalleAlquiler, Integer> {
}