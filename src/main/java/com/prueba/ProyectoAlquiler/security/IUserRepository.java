package com.prueba.ProyectoAlquiler.security;

import com.prueba.ProyectoAlquiler.model.Usuario;
import com.prueba.ProyectoAlquiler.repository.IGenericRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface IUserRepository extends IGenericRepository<Usuario, Integer> {
    //@Query("FROM User u WHERE u.username = ?);
    //Queries derivados
    Usuario findOneByUsername(String username);
    Usuario findByUsername(String username);
    boolean existsByUsername(String username);

    @Transactional
    @Modifying
    @Query("UPDATE Usuario u SET u.password = :password WHERE u.username = :username") //JQPL
    void changePassword(@Param("username") String username, @Param("password") String newPassword);
}
