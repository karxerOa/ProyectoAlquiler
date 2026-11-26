package com.prueba.ProyectoAlquiler.security;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.http.HttpMethod;
@Configuration
@EnableMethodSecurity
public class WebSecurityConfig {
 @Bean public PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}
 @Bean public AuthenticationManager authenticationManager(JwtUserDetailsService users,PasswordEncoder encoder){
  var provider=new DaoAuthenticationProvider(users);provider.setPasswordEncoder(encoder);return new ProviderManager(provider);
 }
 @Bean public SecurityFilterChain filterChain(HttpSecurity http,JwtRequestFilter filter,JwtAuthenticationEntryPoint entry) throws Exception {
  return http.csrf(AbstractHttpConfigurer::disable).formLogin(AbstractHttpConfigurer::disable).httpBasic(AbstractHttpConfigurer::disable)
   .cors(org.springframework.security.config.Customizer.withDefaults())
   .sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
   .authorizeHttpRequests(r->r.requestMatchers(HttpMethod.OPTIONS,"/**").permitAll()
    .requestMatchers("/login","/registro","/v3/api-docs/**","/swagger-ui/**","/swagger-ui.html","/error").permitAll()
    .requestMatchers("/v1/usuarios/**","/v1/roles/**").hasRole("ADMIN")
    .requestMatchers(HttpMethod.GET,"/v1/mi-cuenta/prendas").hasRole("USER")
    .requestMatchers("/v1/**").hasAnyRole("ADMIN","EMPLEADO").anyRequest().authenticated())
   .exceptionHandling(e->e.authenticationEntryPoint(entry).accessDeniedHandler((req,res,ex)->{res.setStatus(403);res.setContentType("application/json");res.getWriter().write("{\"message\":\"Acceso denegado\"}");}))
   .addFilterBefore(filter,UsernamePasswordAuthenticationFilter.class).build();
 }
}
