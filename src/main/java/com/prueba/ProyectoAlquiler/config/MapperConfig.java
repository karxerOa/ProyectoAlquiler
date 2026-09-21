package com.prueba.ProyectoAlquiler.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean
    public ModelMapper defaulMapper() {
        return new ModelMapper();
    }
}
