package com.prueba.ProyectoAlquiler.security;
import org.springframework.stereotype.Component;
import org.springframework.security.core.context.SecurityContextHolder;
@Component
public class AuthorizeLogic {
 public boolean hasAccess(String method){
  var auth=SecurityContextHolder.getContext().getAuthentication();
  if(auth==null || !auth.isAuthenticated()) return false;
  boolean read=java.util.Set.of("findAll","findById","getById").contains(method);
  return auth.getAuthorities().stream().anyMatch(a->a.getAuthority().equals("ROLE_ADMIN") || a.getAuthority().equals("ROLE_EMPLEADO") || (read && a.getAuthority().equals("ROLE_USER")));
 }
}
