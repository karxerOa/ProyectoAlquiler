package com.prueba.ProyectoAlquiler.service.implementations;
import com.prueba.ProyectoAlquiler.model.DetalleAlquiler;
import com.prueba.ProyectoAlquiler.repository.IDetalleAlquilerRepository;
import com.prueba.ProyectoAlquiler.repository.IGenericRepository;
import com.prueba.ProyectoAlquiler.service.interfaz.IDetalleAlquilerService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DetalleAlquilerService extends GenericService<DetalleAlquiler, Integer>
    implements IDetalleAlquilerService {

    private final IDetalleAlquilerRepository repo;

    @Override
    protected IGenericRepository<DetalleAlquiler, Integer> getRepo() {
        return repo;
    }
}