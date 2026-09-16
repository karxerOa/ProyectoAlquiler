package com.prueba.ProyectoAlquiler.service.implementations;

import com.prueba.ProyectoAlquiler.model.Rol;
import com.prueba.ProyectoAlquiler.repository.IRolRepository;
import com.prueba.ProyectoAlquiler.repository.IGenericRepository;
import com.prueba.ProyectoAlquiler.service.interfaz.IRolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RolService extends GenericService<Rol, Integer> implements IRolService {
    private final IRolRepository repo;

    @Override
    protected IGenericRepository<Rol, Integer> getRepo() {
        return repo;
    }
}
