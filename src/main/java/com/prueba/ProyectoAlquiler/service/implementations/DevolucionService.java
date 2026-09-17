package com.prueba.ProyectoAlquiler.service.implementations;

import com.prueba.ProyectoAlquiler.model.Devolucion;
import com.prueba.ProyectoAlquiler.repository.IDevolucionRepository;
import com.prueba.ProyectoAlquiler.repository.IGenericRepository;
import com.prueba.ProyectoAlquiler.service.interfaz.IDevolucionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DevolucionService extends GenericService<Devolucion, Integer> implements IDevolucionService {
    private final IDevolucionRepository repo;

    @Override
    protected IGenericRepository<Devolucion, Integer> getRepo() {
        return repo;
    }
}
