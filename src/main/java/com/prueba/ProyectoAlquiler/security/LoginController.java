package com.prueba.ProyectoAlquiler.security;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {
    private final AuthenticationManager auth;
    private final JwtTokenUtil tokens;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
        var result = auth.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(), request.getPassword()));
        return ResponseEntity.ok(new JwtResponse(tokens.generateToken((UserDetails) result.getPrincipal())));
    }
}
