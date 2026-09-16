package com.prueba.ProyectoAlquiler.service.implementations;

import com.prueba.ProyectoAlquiler.model.Categoria;
import com.prueba.ProyectoAlquiler.repository.ICategoriaRepository;
import com.prueba.ProyectoAlquiler.repository.IGenericRepository;
import com.prueba.ProyectoAlquiler.service.interfaz.ICategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoriaService extends GenericService<Categoria, Integer> implements ICategoriaService {
    private final ICategoriaRepository repo;

    @Override
    protected IGenericRepository<Categoria, Integer> getRepo() {
        return repo;
    }
}
