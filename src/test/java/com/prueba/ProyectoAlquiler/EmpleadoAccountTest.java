package com.prueba.ProyectoAlquiler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prueba.ProyectoAlquiler.config.MapperConfig;
import com.prueba.ProyectoAlquiler.dto.EmpleadoDto;
import com.prueba.ProyectoAlquiler.model.Empleado;
import com.prueba.ProyectoAlquiler.model.Rol;
import com.prueba.ProyectoAlquiler.model.Usuario;
import com.prueba.ProyectoAlquiler.repository.IEmpleadoRepository;
import com.prueba.ProyectoAlquiler.repository.IRolRepository;
import com.prueba.ProyectoAlquiler.repository.IUsuarioRepository;
import com.prueba.ProyectoAlquiler.security.IUserRepository;
import com.prueba.ProyectoAlquiler.service.implementations.EmpleadoService;
import com.prueba.ProyectoAlquiler.service.implementations.UsuarioService;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EmpleadoAccountTest {
    @Test
    void createsEmployeeAndEnabledEmployeeAccountWithEncodedPassword() throws Exception {
        IEmpleadoRepository employees = mock(IEmpleadoRepository.class);
        IUserRepository users = mock(IUserRepository.class);
        PasswordEncoder encoder = mock(PasswordEncoder.class);
        IRolRepository roles = mock(IRolRepository.class);
        Rol employeeRole = new Rol();
        employeeRole.setName("EMPLEADO");
        when(roles.findAll()).thenReturn(List.of(employeeRole));
        when(encoder.encode("claveSegura1")).thenReturn("bcrypt-hash");
        when(users.save(any(Usuario.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(employees.save(any(Empleado.class))).thenAnswer(invocation -> invocation.getArgument(0));

        EmpleadoService service = new EmpleadoService(employees, users, encoder, roles);
        Empleado created = service.createWithAccount(new Empleado(), "empleado1", "claveSegura1");

        assertNotNull(created.getUsuario());
        assertEquals("empleado1", created.getUsuario().getUsername());
        assertEquals("bcrypt-hash", created.getUsuario().getPassword());
        assertTrue(created.getUsuario().isEnabled());
        assertSame(employeeRole, created.getUsuario().getRol());
        verify(users).save(any(Usuario.class));
        verify(employees).save(created);
    }

    @Test
    void rejectsDuplicateUsernameBeforeSavingEitherRecord() {
        IEmpleadoRepository employees = mock(IEmpleadoRepository.class);
        IUserRepository users = mock(IUserRepository.class);
        IRolRepository roles = mock(IRolRepository.class);
        when(users.existsByUsername("duplicado")).thenReturn(true);
        EmpleadoService service = new EmpleadoService(
                employees, users, mock(PasswordEncoder.class), roles);

        IllegalArgumentException error = assertThrows(IllegalArgumentException.class,
                () -> service.createWithAccount(new Empleado(), "duplicado", "claveSegura1"));

        assertEquals("El username ya está en uso", error.getMessage());
        verify(users, never()).save(any(Usuario.class));
        verify(employees, never()).save(any(Empleado.class));
    }

    @Test
    void emptyPasswordOnUpdateKeepsExistingHashAndMapperNeverReturnsPassword() throws Exception {
        IEmpleadoRepository employees = mock(IEmpleadoRepository.class);
        IUserRepository users = mock(IUserRepository.class);
        PasswordEncoder encoder = mock(PasswordEncoder.class);
        Usuario linkedUser = new Usuario();
        linkedUser.setIdUser(7);
        linkedUser.setUsername("empleado1");
        linkedUser.setPassword("existing-hash");
        Empleado existing = new Empleado();
        existing.setIdEmpleado(4);
        existing.setUsuario(linkedUser);
        when(employees.findById(4)).thenReturn(Optional.of(existing));
        when(employees.save(any(Empleado.class))).thenAnswer(invocation -> invocation.getArgument(0));

        EmpleadoService service = new EmpleadoService(
                employees, users, encoder, mock(IRolRepository.class));
        Empleado updated = service.updateWithAccount(4, new Empleado(), "empleado1", "");

        assertEquals("existing-hash", updated.getUsuario().getPassword());
        verify(encoder, never()).encode(any());

        EmpleadoDto dto = new MapperConfig().empleadoMapper().map(updated, EmpleadoDto.class);
        assertEquals(7, dto.getIdUser());
        assertEquals("empleado1", dto.getUsername());
        assertFalse(new ObjectMapper().writeValueAsString(dto).contains("existing-hash"));
    }

    @Test
    void userCrudRejectsEmployeeRole() {
        IUsuarioRepository users = mock(IUsuarioRepository.class);
        IRolRepository roles = mock(IRolRepository.class);
        Rol employeeRole = new Rol();
        employeeRole.setIdRol(2);
        employeeRole.setName("EMPLEADO");
        when(roles.findById(2)).thenReturn(Optional.of(employeeRole));
        UsuarioService service = new UsuarioService(users, mock(PasswordEncoder.class), roles);
        Usuario usuario = new Usuario();
        Rol selectedRole = new Rol();
        selectedRole.setIdRol(2);
        usuario.setRol(selectedRole);

        IllegalArgumentException error = assertThrows(IllegalArgumentException.class,
                () -> service.save(usuario));

        assertTrue(error.getMessage().contains("solo permite el rol ADMIN"));
        verify(users, never()).save(any(Usuario.class));
    }

    @Test
    void userCrudCannotEditAnEmployeeAccountEvenIfChangingItsRole() throws Exception {
        IUsuarioRepository users = mock(IUsuarioRepository.class);
        Usuario existing = new Usuario();
        existing.setIdUser(9);
        Rol employeeRole = new Rol();
        employeeRole.setName("EMPLEADO");
        existing.setRol(employeeRole);
        when(users.findById(9)).thenReturn(Optional.of(existing));
        UsuarioService service = new UsuarioService(
                users, mock(PasswordEncoder.class), mock(IRolRepository.class));
        Usuario update = new Usuario();
        Rol adminRole = new Rol();
        adminRole.setIdRol(1);
        update.setRol(adminRole);

        IllegalArgumentException error = assertThrows(IllegalArgumentException.class,
                () -> service.update(9, update));

        assertEquals("Las cuentas EMPLEADO solo se pueden editar desde la ficha de empleados",
                error.getMessage());
        verify(users, never()).save(any(Usuario.class));
    }
}
