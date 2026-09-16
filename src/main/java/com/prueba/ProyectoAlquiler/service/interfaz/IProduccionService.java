package com.prueba.ProyectoAlquiler.service.interfaz;

import com.prueba.ProyectoAlquiler.model.Produccion;
import java.util.List;

public interface IProduccionService {
    Produccion save(Produccion produccion) throws Exception;
    Produccion update(Integer id, Produccion produccion) throws Exception;
    List<Produccion> findAll() throws Exception;
    Produccion findById(Integer id) throws Exception;
    void delete(Integer id) throws Exception;
}
