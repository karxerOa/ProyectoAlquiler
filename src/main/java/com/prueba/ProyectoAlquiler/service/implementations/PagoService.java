package com.prueba.ProyectoAlquiler.service.implementations;

import com.prueba.ProyectoAlquiler.model.Pago;
import com.prueba.ProyectoAlquiler.repository.IPagoRepository;
import com.prueba.ProyectoAlquiler.repository.IGenericRepository;
import com.prueba.ProyectoAlquiler.service.interfaz.IPagoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PagoService extends GenericService<Pago, Integer> implements IPagoService {
    private final IPagoRepository repo;

    @Override
    protected IGenericRepository<Pago, Integer> getRepo() {
        return repo;
    }
}
