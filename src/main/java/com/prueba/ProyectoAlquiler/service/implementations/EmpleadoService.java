package com.prueba.ProyectoAlquiler.service.implementations;

import com.prueba.ProyectoAlquiler.model.Empleado;
import com.prueba.ProyectoAlquiler.repository.IEmpleadoRepository;
import com.prueba.ProyectoAlquiler.repository.IGenericRepository;
import com.prueba.ProyectoAlquiler.service.interfaz.IEmpleadoService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class EmpleadoService extends GenericService<Empleado, Integer> implements IEmpleadoService {
    private final IEmpleadoRepository repo;

    @Override
    protected IGenericRepository<Empleado, Integer> getRepo() {
        return repo;
    }
}
