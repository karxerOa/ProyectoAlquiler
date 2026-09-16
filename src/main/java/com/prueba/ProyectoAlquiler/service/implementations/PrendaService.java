package com.prueba.ProyectoAlquiler.service.implementations;

import com.prueba.ProyectoAlquiler.model.Prenda;
import com.prueba.ProyectoAlquiler.repository.IGenericRepository;
import com.prueba.ProyectoAlquiler.repository.IPrendaRepository;
import com.prueba.ProyectoAlquiler.service.interfaz.IPrendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrendaService extends GenericService<Prenda, Integer> implements IPrendaService {
    private final IPrendaRepository repo;

    @Override
    protected IGenericRepository<Prenda, Integer> getRepo() {
        return repo;
    }
}
