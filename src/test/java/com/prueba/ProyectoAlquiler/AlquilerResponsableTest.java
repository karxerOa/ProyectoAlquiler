package com.prueba.ProyectoAlquiler;

import com.prueba.ProyectoAlquiler.config.MapperConfig;
import com.prueba.ProyectoAlquiler.controller.AlquilerController;
import com.prueba.ProyectoAlquiler.dto.AlquilerDto;
import com.prueba.ProyectoAlquiler.model.Alquiler;
import com.prueba.ProyectoAlquiler.model.Empleado;
import com.prueba.ProyectoAlquiler.repository.IEmpleadoRepository;
import com.prueba.ProyectoAlquiler.service.interfaz.IAlquilerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AlquilerResponsableTest {
    private IAlquilerService service;
    private IEmpleadoRepository employees;
    private AlquilerController controller;

    @BeforeEach
    void setUp() throws Exception {
        service = mock(IAlquilerService.class);
        employees = mock(IEmpleadoRepository.class);
        when(service.save(any(Alquiler.class))).thenAnswer(invocation -> invocation.getArgument(0));
        controller = new AlquilerController(
                service, new MapperConfig().alquilerMapper(), employees);
    }

    @Test
    void linkedEmployeeOverridesEmployeeIdFromRequest() throws Exception {
        Empleado linked = new Empleado();
        linked.setIdEmpleado(8);
        when(employees.findByUsuarioUsername("staff")).thenReturn(Optional.of(linked));
        AlquilerDto request = new AlquilerDto();
        request.setEmpleadoId(99);

        controller.save(request, authentication("staff", "ROLE_EMPLEADO"));

        org.mockito.ArgumentCaptor<Alquiler> captor = org.mockito.ArgumentCaptor.forClass(Alquiler.class);
        verify(service).save(captor.capture());
        assertSame(linked, captor.getValue().getEmpleado());
    }

    @Test
    void rejectsEmployeeAccountWithoutLinkedEmployee() throws Exception {
        when(employees.findByUsuarioUsername("staff")).thenReturn(Optional.empty());

        IllegalArgumentException error = assertThrows(IllegalArgumentException.class,
                () -> controller.save(new AlquilerDto(),
                        authentication("staff", "ROLE_EMPLEADO")));

        assertEquals("Tu cuenta no está vinculada a un empleado", error.getMessage());
        verify(service, never()).save(any(Alquiler.class));
    }

    @Test
    void adminMustSelectEmployeeButCanUseSelectedEmployee() throws Exception {
        when(employees.findByUsuarioUsername("admin")).thenReturn(Optional.empty());
        var admin = authentication("admin", "ROLE_ADMIN");

        IllegalArgumentException error = assertThrows(IllegalArgumentException.class,
                () -> controller.save(new AlquilerDto(), admin));
        assertEquals("Debe seleccionar un empleado responsable", error.getMessage());

        AlquilerDto request = new AlquilerDto();
        request.setEmpleadoId(12);
        controller.save(request, admin);
        org.mockito.ArgumentCaptor<Alquiler> captor = org.mockito.ArgumentCaptor.forClass(Alquiler.class);
        verify(service).save(captor.capture());
        assertEquals(12, captor.getValue().getEmpleado().getIdEmpleado());
    }

    private UsernamePasswordAuthenticationToken authentication(String username, String role) {
        return new UsernamePasswordAuthenticationToken(
                username, null, List.of(new SimpleGrantedAuthority(role)));
    }
}
