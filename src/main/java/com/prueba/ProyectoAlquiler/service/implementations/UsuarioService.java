package com.prueba.ProyectoAlquiler.service.implementations;

import com.prueba.ProyectoAlquiler.model.Usuario;
import com.prueba.ProyectoAlquiler.repository.IUsuarioRepository;
import com.prueba.ProyectoAlquiler.repository.IGenericRepository;
import com.prueba.ProyectoAlquiler.service.interfaz.IUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService extends GenericService<Usuario, Integer> implements IUsuarioService {
    private final IUsuarioRepository repo;

    @Override
    protected IGenericRepository<Usuario, Integer> getRepo() {
        return repo;
    }
}
