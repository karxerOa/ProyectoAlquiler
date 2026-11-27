package com.prueba.ProyectoAlquiler.util;
import org.springframework.context.annotation.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.cors.*;
import java.util.List;
@Configuration
public class CORS {
 @Bean public CorsConfigurationSource corsConfigurationSource(@Value("${app.cors.origins:http://localhost:4200, https://proyecto-alquiler-frontend.vercel.app/}") List<String> origins){
  var c=new CorsConfiguration();c.setAllowedOrigins(origins);c.setAllowedMethods(List.of("GET","POST","PUT","DELETE","OPTIONS"));c.setAllowedHeaders(List.of("Authorization","Content-Type"));c.setMaxAge(3600L);
  var source=new UrlBasedCorsConfigurationSource();source.registerCorsConfiguration("/**",c);return source;
 }
}
