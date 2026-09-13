package com.prueba.ProyectoAlquiler.service.implementations;

import com.prueba.ProyectoAlquiler.model.Cliente;
import com.prueba.ProyectoAlquiler.repository.IClienteRepository;
import com.prueba.ProyectoAlquiler.repository.IGenericRepository;
import com.prueba.ProyectoAlquiler.service.interfaz.IClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteService extends GenericService<Cliente, Integer> implements IClienteService {
    private final IClienteRepository repo;

    @Override
    protected IGenericRepository<Cliente, Integer> getRepo() {
        return repo;
    }
}
