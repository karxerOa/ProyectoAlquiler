package com.prueba.ProyectoAlquiler.service.interfaz;

import com.prueba.ProyectoAlquiler.model.Alquiler;
import java.util.List;

public interface IAlquilerService {
    Alquiler save(Alquiler alquiler) throws Exception;
    Alquiler update(Integer id, Alquiler alquiler) throws Exception;
    List<Alquiler> findAll() throws Exception;
    Alquiler findById(Integer id) throws Exception;
    void delete(Integer id) throws Exception;
}