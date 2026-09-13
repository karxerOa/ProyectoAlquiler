package com.prueba.ProyectoAlquiler.service.implementations;

import com.prueba.ProyectoAlquiler.model.Empleado;
import com.prueba.ProyectoAlquiler.repository.IEmpleadoRepository;
import com.prueba.ProyectoAlquiler.repository.IGenericRepository;
import com.prueba.ProyectoAlquiler.service.interfaz.IEmpleadoService;
import com.prueba.ProyectoAlquiler.service.interfaz.IGenericService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class EmpleadoService extends GenericService<Empleado, Integer> implements IEmpleadoService {
    private final IEmpleadoRepository repo;

    @Override
    protected IGenericRepository<Empleado, Integer> getRepo() {
        return repo;
    }
}
