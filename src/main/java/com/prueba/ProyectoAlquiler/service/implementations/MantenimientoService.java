package com.prueba.ProyectoAlquiler.service.implementations;

import com.prueba.ProyectoAlquiler.model.Mantenimiento;
import com.prueba.ProyectoAlquiler.repository.IMantenimientoRepository;
import com.prueba.ProyectoAlquiler.repository.IGenericRepository;
import com.prueba.ProyectoAlquiler.service.interfaz.IMantenimientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MantenimientoService extends GenericService<Mantenimiento, Integer> implements IMantenimientoService {
    private final IMantenimientoRepository repo;

    @Override
    protected IGenericRepository<Mantenimiento, Integer> getRepo() {
        return repo;
    }
}
