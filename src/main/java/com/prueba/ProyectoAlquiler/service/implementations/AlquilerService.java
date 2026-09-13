package com.prueba.ProyectoAlquiler.service.implementations;
import com.prueba.ProyectoAlquiler.model.Alquiler;
import com.prueba.ProyectoAlquiler.repository.IAlquilerRepository;
import com.prueba.ProyectoAlquiler.service.interfaz.IAlquilerService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlquilerService implements IAlquilerService {

    private final IAlquilerRepository repo;
    @Override
    public Alquiler save(Alquiler alquiler) throws Exception {
        return repo.save(alquiler);
    }
    @Override
    public Alquiler update(Integer id, Alquiler alquiler) throws Exception {
        return repo.save(alquiler);
    }
    @Override
    public List<Alquiler> findAll() throws Exception {
        return repo.findAll();
    }
    @Override
    public Alquiler findById(Integer id) throws Exception {
        return repo.findById(id).orElse(new Alquiler());
    }
    @Override
    public void delete(Integer id) throws Exception {
        repo.deleteById(id);
    }
}