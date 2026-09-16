package com.prueba.ProyectoAlquiler.service.interfaz;

import com.prueba.ProyectoAlquiler.model.Categoria;
import java.util.List;

public interface ICategoriaService {
    Categoria save(Categoria categoria) throws Exception;
    Categoria update(Integer id, Categoria categoria) throws Exception;
    List<Categoria> findAll() throws Exception;
    Categoria findById(Integer id) throws Exception;
    void delete(Integer id) throws Exception;
}