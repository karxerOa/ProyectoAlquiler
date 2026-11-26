package com.prueba.ProyectoAlquiler;

import com.prueba.ProyectoAlquiler.model.Rol;
import com.prueba.ProyectoAlquiler.model.Usuario;
import com.prueba.ProyectoAlquiler.security.IUserRepository;
import com.prueba.ProyectoAlquiler.security.JwtUserDetailsService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import java.lang.reflect.Proxy;

class StaffAccessTest {
    private JwtUserDetailsService detailsFor(String role) {

        var account = new Usuario();
        var assigned = new Rol();
        assigned.setName(role);
        account.setRol(assigned);
        account.setEnabled(true);
        account.setUsername("prueba");
        account.setPassword("hash");
        var repository = (IUserRepository) Proxy.newProxyInstance(
                IUserRepository.class.getClassLoader(), new Class<?>[]{IUserRepository.class},
                (proxy, method, args) -> {
                    if (method.getName().equals("findOneByUsername")) return account;
                    throw new UnsupportedOperationException(method.getName());
                });
        return new JwtUserDetailsService(repository);
    }

    @ParameterizedTest
    @ValueSource(strings = {"USER", "ROLE_USER", "AUDITOR"})
    void rejectsNonStaffAccounts(String role) {
        assertThrows(UsernameNotFoundException.class,
                () -> detailsFor(role).loadUserByUsername("prueba"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"ADMIN", "ROLE_ADMIN", "EMPLEADO", "ROLE_EMPLEADO"})
    void loadsStaffAuthorities(String role) {
        var user = detailsFor(role).loadUserByUsername("prueba");
        String expected = role.startsWith("ROLE_") ? role : "ROLE_" + role;
        assertEquals(expected, user.getAuthorities().iterator().next().getAuthority());
    }
}
