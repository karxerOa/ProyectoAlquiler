package com.prueba.ProyectoAlquiler.service.implementations;
import com.prueba.ProyectoAlquiler.model.Alquiler;
import com.prueba.ProyectoAlquiler.repository.*;
import com.prueba.ProyectoAlquiler.service.interfaz.IAlquilerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class AlquilerService extends GenericService<Alquiler,Integer> implements IAlquilerService {
 private final IAlquilerRepository repo;
 protected IGenericRepository<Alquiler,Integer> getRepo(){ return repo; }
}
