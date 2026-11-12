package com.prueba.ProyectoAlquiler.security;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.*;
@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {
 private final IUserRepository repo;
 public UserDetails loadUserByUsername(String username) {
  var u=repo.findOneByUsername(username);
  if(u==null || u.getRol()==null) throw new UsernameNotFoundException("Credenciales invalidas");
  String role=u.getRol().getName().toUpperCase(java.util.Locale.ROOT);
  if(!role.startsWith("ROLE_")) role="ROLE_"+role;
  return User.withUsername(u.getUsername()).password(u.getPassword()).disabled(!u.isEnabled()).authorities(role).build();
 }
}
