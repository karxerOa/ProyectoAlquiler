package com.prueba.ProyectoAlquiler.security;
import com.prueba.ProyectoAlquiler.dto.UsuarioDto;
import com.prueba.ProyectoAlquiler.model.Usuario;
import com.prueba.ProyectoAlquiler.repository.IRolRepository;
import com.prueba.ProyectoAlquiler.service.interfaz.IUsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
@RestController
public class LoginController {
 private final AuthenticationManager auth; private final JwtTokenUtil tokens; private final IUsuarioService users; private final IRolRepository roles; private final ModelMapper mapper;
 public LoginController(AuthenticationManager auth,JwtTokenUtil tokens,IUsuarioService users,IRolRepository roles,@Qualifier("usuarioMapper") ModelMapper mapper){this.auth=auth;this.tokens=tokens;this.users=users;this.roles=roles;this.mapper=mapper;}
 @PostMapping("/login") public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request){
  var result=auth.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
  return ResponseEntity.ok(new JwtResponse(tokens.generateToken((UserDetails) result.getPrincipal())));
 }
 @PostMapping("/registro") public ResponseEntity<UsuarioDto> register(@RequestBody UsuarioDto dto) throws Exception {
  if(dto.getUsername()==null || dto.getUsername().isBlank()) throw new IllegalArgumentException("Username obligatorio");
  var role=roles.findAll().stream().filter(r->"USER".equals(r.getName()) || "ROLE_USER".equals(r.getName())).findFirst().orElseThrow(()->new IllegalStateException("Configurar rol USER antes del registro"));
  Usuario user=mapper.map(dto,Usuario.class);user.setIdUser(null);user.setEnabled(true);user.setRol(role);
  return ResponseEntity.status(201).body(mapper.map(users.save(user),UsuarioDto.class));
 }
}
