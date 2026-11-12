package com.prueba.ProyectoAlquiler.security;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.authentication.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {
 private final JwtTokenUtil jwtTokenUtil;
 private final JwtUserDetailsService jwtUserDetailsService;
 private final JwtAuthenticationEntryPoint entryPoint;
 protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response,FilterChain chain) throws ServletException,IOException {
  String header=request.getHeader("Authorization");
  if(header!=null && header.startsWith("Bearer ") && SecurityContextHolder.getContext().getAuthentication()==null){
   try {
    String token=header.substring(7);
    var user=jwtUserDetailsService.loadUserByUsername(jwtTokenUtil.getUsernameFromToken(token));
    if(!user.isEnabled() || !jwtTokenUtil.validateToken(token,user)) throw new BadCredentialsException("Token invalido");
    var auth=new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
    auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    SecurityContextHolder.getContext().setAuthentication(auth);
   } catch(io.jsonwebtoken.JwtException | IllegalArgumentException | AuthenticationException ex){
    SecurityContextHolder.clearContext();
    entryPoint.commence(request,response,new BadCredentialsException("Token invalido o expirado"));
    return;
   }
  }
  chain.doFilter(request,response);
 }
}
