package com.prueba.ProyectoAlquiler.service.implementations;

import com.prueba.ProyectoAlquiler.model.Empleado;
import com.prueba.ProyectoAlquiler.model.Rol;
import com.prueba.ProyectoAlquiler.model.Usuario;
import com.prueba.ProyectoAlquiler.repository.IEmpleadoRepository;
import com.prueba.ProyectoAlquiler.repository.IGenericRepository;
import com.prueba.ProyectoAlquiler.repository.IRolRepository;
import com.prueba.ProyectoAlquiler.security.IUserRepository;
import com.prueba.ProyectoAlquiler.security.StaffRole;
import com.prueba.ProyectoAlquiler.service.interfaz.IEmpleadoService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EmpleadoService extends GenericService<Empleado, Integer> implements IEmpleadoService {
    private final IEmpleadoRepository repo;
    private final IUserRepository users;
    private final PasswordEncoder passwordEncoder;
    private final IRolRepository roles;

    @Override
    protected IGenericRepository<Empleado, Integer> getRepo() {
        return repo;
    }

    @Override
    @Transactional
    public Empleado createWithAccount(Empleado empleado, String username, String password) throws Exception {
        validateUsername(username);
        if (users.existsByUsername(username)) {
            throw new IllegalArgumentException("El username ya está en uso");
        }
        validatePassword(password);

        Usuario usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setPassword(passwordEncoder.encode(password));
        usuario.setEnabled(true);
        usuario.setRol(findEmployeeRole());
        usuario = users.save(usuario);

        empleado.setUsuario(usuario);
        return repo.save(empleado);
    }

    @Override
    @Transactional
    public Empleado updateWithAccount(Integer id, Empleado empleado, String username, String password) throws Exception {
        Empleado existing = findById(id);
        Usuario usuario = existing.getUsuario();

        if (usuario == null) {
            if (username != null && !username.isBlank()) {
                validateUsername(username);
                if (users.existsByUsername(username)) {
                    throw new IllegalArgumentException("El username ya está en uso");
                }
                validatePassword(password);
                usuario = new Usuario();
                usuario.setUsername(username);
                usuario.setPassword(passwordEncoder.encode(password));
                usuario.setEnabled(true);
                usuario.setRol(findEmployeeRole());
                usuario = users.save(usuario);
            } else if (password != null && !password.isBlank()) {
                throw new IllegalArgumentException("Debe indicar un username para crear la cuenta");
            }
        } else {
            if (username != null && !username.isBlank() && !username.equals(usuario.getUsername())) {
                validateUsername(username);
                Usuario other = users.findByUsername(username);
                if (other != null && !Objects.equals(other.getIdUser(), usuario.getIdUser())) {
                    throw new IllegalArgumentException("El username ya está en uso");
                }
                usuario.setUsername(username);
            }
            if (password != null && !password.isBlank()) {
                validatePassword(password);
                usuario.setPassword(passwordEncoder.encode(password));
            }
            users.save(usuario);
        }

        existing.setNombres(empleado.getNombres());
        existing.setApellidos(empleado.getApellidos());
        existing.setDni(empleado.getDni());
        existing.setTelefono(empleado.getTelefono());
        existing.setCargo(empleado.getCargo());
        existing.setEstado(empleado.getEstado());
        existing.setUsuario(usuario);
        return repo.save(existing);
    }

    @Override
    @Transactional(readOnly = true)
    public Empleado findByUsuarioUsername(String username) {
        return repo.findByUsuarioUsername(username)
                .orElseThrow(() -> new com.prueba.ProyectoAlquiler.exception.ModelNotFoundException(
                        "No existe un empleado vinculado a esta cuenta"));
    }

    private Rol findEmployeeRole() {
        return roles.findAll().stream()
                .filter(role -> StaffRole.authority(role.getName())
                        .filter("ROLE_EMPLEADO"::equals)
                        .isPresent())
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("El rol EMPLEADO no está configurado"));
    }

    private void validateUsername(String username) {
        if (username == null || username.isBlank() || username.length() > 20) {
            throw new IllegalArgumentException("El username es obligatorio y no puede superar 20 caracteres");
        }
    }

    private void validatePassword(String password) {
        if (password == null || password.length() < 8
                || password.getBytes(StandardCharsets.UTF_8).length > 72) {
            throw new IllegalArgumentException("La clave debe tener al menos 8 caracteres y como máximo 72 bytes");
        }
    }
}






































































































