package com.prueba.ProyectoAlquiler.security;

import com.prueba.ProyectoAlquiler.model.Usuario;
import com.prueba.ProyectoAlquiler.repository.IGenericRepository;

import com.prueba.ProyectoAlquiler.service.implementations.GenericService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService extends GenericService<Usuario, Integer> implements IUserService{
    private final IUserRepository repo;
    private final PasswordEncoder bcrypt;
    private final com.prueba.ProyectoAlquiler.service.interfaz.IUsuarioService usuarios;
    @Override public Usuario save(Usuario u) throws Exception {return usuarios.save(u);}
    @Override public Usuario update(Integer id,Usuario u) throws Exception {return usuarios.update(id,u);}

    @Override
    protected IGenericRepository<Usuario, Integer> getRepo() {
        return repo;
    }

    @Override
    public Usuario findOneByUsername(String username) {
        return repo.findOneByUsername(username);
    }

    @Override
    public void changePassword(String username, String newPassword) {
        if(newPassword==null || newPassword.length()<8 || newPassword.getBytes(java.nio.charset.StandardCharsets.UTF_8).length>72)
            throw new IllegalArgumentException("Clave invalida: entre 8 caracteres y 72 bytes");
        if(repo.findOneByUsername(username)==null) throw new com.prueba.ProyectoAlquiler.exception.ModelNotFoundException("Usuario no encontrado");
        repo.changePassword(username, bcrypt.encode(newPassword));
    }
}
