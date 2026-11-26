package com.prueba.ProyectoAlquiler.security;


import com.prueba.ProyectoAlquiler.model.Usuario;
import com.prueba.ProyectoAlquiler.service.interfaz.IGenericService;

public interface IUserService extends IGenericService<Usuario, Integer> {
    Usuario findOneByUsername(String username);
    void changePassword(String username, String password);
}
