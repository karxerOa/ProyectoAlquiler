package com.prueba.ProyectoAlquiler.repository;

import com.prueba.ProyectoAlquiler.model.Alquiler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAlquilerRepository extends JpaRepository<Alquiler, Integer> {
}