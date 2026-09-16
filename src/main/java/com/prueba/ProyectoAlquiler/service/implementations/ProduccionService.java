package com.prueba.ProyectoAlquiler.service.implementations;

import com.prueba.ProyectoAlquiler.model.Produccion;
import com.prueba.ProyectoAlquiler.repository.IProduccionRepository;
import com.prueba.ProyectoAlquiler.repository.IGenericRepository;
import com.prueba.ProyectoAlquiler.service.interfaz.IProduccionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProduccionService extends GenericService<Produccion, Integer> implements IProduccionService {
    private final IProduccionRepository repo;

    @Override
    protected IGenericRepository<Produccion, Integer> getRepo() {
        return repo;
    }
}
