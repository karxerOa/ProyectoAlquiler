package com.prueba.ProyectoAlquiler.service.implementations;
import com.prueba.ProyectoAlquiler.model.DetalleAlquiler;
import com.prueba.ProyectoAlquiler.repository.IDetalleAlquilerRepository;
import com.prueba.ProyectoAlquiler.service.interfaz.IDetalleAlquilerService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DetalleAlquilerService implements IDetalleAlquilerService {

    private final IDetalleAlquilerRepository repo;
    @Override
    public DetalleAlquiler save(DetalleAlquiler detalleAlquiler) throws Exception {
        return repo.save(detalleAlquiler);
    }
    @Override
    public DetalleAlquiler update(Integer id, DetalleAlquiler detalleAlquiler) throws Exception {
        return repo.save(detalleAlquiler);
    }
    @Override
    public List<DetalleAlquiler> findAll() throws Exception {
        return repo.findAll();
    }
    @Override
    public DetalleAlquiler findById(Integer id) throws Exception {
        return repo.findById(id).orElse(new DetalleAlquiler());
    }
    @Override
    public void delete(Integer id) throws Exception {
        repo.deleteById(id);
    }
}