package com.prueba.ProyectoAlquiler.service.interfaz;

import com.prueba.ProyectoAlquiler.model.Empleado;

public interface IEmpleadoService extends IGenericService<Empleado, Integer> {
    Empleado createWithAccount(Empleado empleado, String username, String password) throws Exception;
    Empleado updateWithAccount(Integer id, Empleado empleado, String username, String password) throws Exception;
    Empleado findByUsuarioUsername(String username) throws Exception;
}
