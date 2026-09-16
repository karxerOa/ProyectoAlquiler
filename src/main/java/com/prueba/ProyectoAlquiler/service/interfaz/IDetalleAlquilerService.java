package com.prueba.ProyectoAlquiler.service.interfaz;

import com.prueba.ProyectoAlquiler.model.DetalleAlquiler;
import java.util.List;

public interface IDetalleAlquilerService {
    DetalleAlquiler save(DetalleAlquiler detalleAlquiler) throws Exception;
    DetalleAlquiler update(Integer id, DetalleAlquiler detalleAlquiler) throws Exception;
    List<DetalleAlquiler> findAll() throws Exception;
    DetalleAlquiler findById(Integer id) throws Exception;
    void delete(Integer id) throws Exception;
}