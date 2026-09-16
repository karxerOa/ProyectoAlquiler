package com.prueba.ProyectoAlquiler.service.implementations;

import com.prueba.ProyectoAlquiler.model.Categoria;
import com.prueba.ProyectoAlquiler.repository.ICategoriaRepository;
import com.prueba.ProyectoAlquiler.service.interfaz.ICategoriaService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService implements ICategoriaService {

    private final ICategoriaRepository repo;

    @Override
    public Categoria save(Categoria categoria) throws Exception {
        return repo.save(categoria);
    }

    @Override
    public Categoria update(Integer id, Categoria categoria) throws Exception {
        return repo.save(categoria);
    }

    @Override
    public List<Categoria> findAll() throws Exception {
        return repo.findAll();
    }

    @Override
    public Categoria findById(Integer id) throws Exception {
        return repo.findById(id).orElse(new Categoria());
    }

    @Override
    public void delete(Integer id) throws Exception {
        repo.deleteById(id);
    }
}