package com.prueba.ProyectoAlquiler.config;
import com.prueba.ProyectoAlquiler.model.*;
import com.prueba.ProyectoAlquiler.repository.IRolRepository;
import com.prueba.ProyectoAlquiler.security.IUserRepository;
import com.prueba.ProyectoAlquiler.service.interfaz.IUsuarioService;
import org.springframework.context.annotation.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
@Configuration
public class BootstrapConfig {
 @Bean CommandLineRunner bootstrap(IRolRepository roles,IUserRepository repo,IUsuarioService users,
  @Value("${app.bootstrap.admin-password:}") String password,
  @Value("${app.bootstrap.admin-username:admin}") String username){
  return args->{
   if(password.isBlank()) return;
   for(String name:java.util.List.of("ADMIN","EMPLEADO")) {
    if(roles.findAll().stream().noneMatch(r->name.equals(r.getName()))) {
     Rol role=new Rol();role.setName(name);role.setDescription(name);roles.save(role);
    }
   }
   if(repo.findOneByUsername(username)==null){
    Usuario u=new Usuario();u.setUsername(username);u.setPassword(password);u.setEnabled(true);
    u.setRol(roles.findAll().stream().filter(r->"ADMIN".equals(r.getName())).findFirst().orElseThrow());users.save(u);
   }
  };
 }
}
