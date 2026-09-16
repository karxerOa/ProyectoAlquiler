package com.prueba.ProyectoAlquiler.service.implementations;

import com.prueba.ProyectoAlquiler.model.Produccion;
import com.prueba.ProyectoAlquiler.repository.IProduccionRepository;
import com.prueba.ProyectoAlquiler.service.interfaz.IProduccionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProduccionService implements IProduccionService {

    private final IProduccionRepository repo;

    @Override
    public Produccion save(Produccion produccion) throws Exception {
        return repo.save(produccion);
    }

    @Override
    public Produccion update(Integer id, Produccion produccion) throws Exception {
        return repo.save(produccion);
    }

    @Override
    public List<Produccion> findAll() throws Exception {
        return repo.findAll();
    }

    @Override
    public Produccion findById(Integer id) throws Exception {
        return repo.findById(id).orElse(new Produccion());
    }

    @Override
    public void delete(Integer id) throws Exception {
        repo.deleteById(id);
    }
}