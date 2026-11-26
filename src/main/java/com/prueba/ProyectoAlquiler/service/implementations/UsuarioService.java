package com.prueba.ProyectoAlquiler.service.implementations;

import com.prueba.ProyectoAlquiler.model.Usuario;
import com.prueba.ProyectoAlquiler.repository.IUsuarioRepository;
import com.prueba.ProyectoAlquiler.repository.IGenericRepository;
import com.prueba.ProyectoAlquiler.service.interfaz.IUsuarioService;
import com.prueba.ProyectoAlquiler.repository.IRolRepository;
import com.prueba.ProyectoAlquiler.security.StaffRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService extends GenericService<Usuario, Integer> implements IUsuarioService {
    private final IUsuarioRepository repo;
    private final org.springframework.security.crypto.password.PasswordEncoder encoder;
    private final IRolRepository roles;

    @Override public Usuario save(Usuario u) throws Exception {
        requireStaffRole(u);
        validatePassword(u.getPassword());
        u.setPassword(encoder.encode(u.getPassword()));
        return super.save(u);
    }
    @Override public Usuario update(Integer id, Usuario u) throws Exception {
        Usuario old=findById(id);
        requireStaffRole(u);
        u.setIdUser(id);
        if(u.getPassword()==null || u.getPassword().isBlank()) u.setPassword(old.getPassword());
        else {validatePassword(u.getPassword());u.setPassword(encoder.encode(u.getPassword()));}
        return super.update(id,u);
    }
    private void requireStaffRole(Usuario usuario) {
        String message = "Seleccione el rol ADMIN o EMPLEADO";
        if (usuario.getRol() == null || usuario.getRol().getIdRol() == null)
            throw new IllegalArgumentException(message);
        var rol = roles.findById(usuario.getRol().getIdRol())
                .filter(r -> StaffRole.authority(r.getName()).isPresent())
                .orElseThrow(() -> new IllegalArgumentException(message));
        usuario.setRol(rol);
    }
    private void validatePassword(String password){
        if(password==null || password.length()<8 || password.getBytes(java.nio.charset.StandardCharsets.UTF_8).length>72)
            throw new IllegalArgumentException("La clave debe tener al menos 8 caracteres y como maximo 72 bytes");
    }

    @Override
    protected IGenericRepository<Usuario, Integer> getRepo() {
        return repo;
    }
}
