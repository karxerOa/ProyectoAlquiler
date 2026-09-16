package com.prueba.ProyectoAlquiler.service.implementations;

import com.prueba.ProyectoAlquiler.model.Cliente;
import com.prueba.ProyectoAlquiler.model.Devolucion;
import com.prueba.ProyectoAlquiler.model.Penalidad;
import com.prueba.ProyectoAlquiler.repository.IDevolucionRepository;
import com.prueba.ProyectoAlquiler.repository.IGenericRepository;
import com.prueba.ProyectoAlquiler.repository.IPenalidadRepository;
import com.prueba.ProyectoAlquiler.service.interfaz.IClienteService;
import com.prueba.ProyectoAlquiler.service.interfaz.IDevolucionService;
import com.prueba.ProyectoAlquiler.service.interfaz.IPenalidadService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class PenalidadService extends GenericService<Penalidad,Integer> implements IPenalidadService {
    private  final IPenalidadRepository repo;
    @Override
    protected IGenericRepository<Penalidad, Integer> getRepo() {return repo;  }
}