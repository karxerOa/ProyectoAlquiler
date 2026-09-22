package com.prueba.ProyectoAlquiler.controller;
import com.prueba.ProyectoAlquiler.dto.AlquilerDto;
import com.prueba.ProyectoAlquiler.model.Alquiler;
import com.prueba.ProyectoAlquiler.service.interfaz.IAlquilerService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/alquileres")
public class AlquilerController {
    private final IAlquilerService service;

    private final ModelMapper modelMapper;

    public AlquilerController(IAlquilerService service,
        @Qualifier("alquilerMapper") ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<AlquilerDto>> findAll() throws Exception {
        return ResponseEntity.ok(service.findAll().stream()
            .map(entity -> modelMapper.map(entity, AlquilerDto.class))
            .toList());
    }
    @GetMapping("/{id}")
    public ResponseEntity<AlquilerDto> findById(@PathVariable Integer id) throws Exception {
        return ResponseEntity.ok(modelMapper.map(service.findById(id), AlquilerDto.class));
    }
    @PostMapping
    public ResponseEntity<AlquilerDto> save(@RequestBody AlquilerDto alquiler) throws Exception {
        Alquiler entity = modelMapper.map(alquiler, Alquiler.class);
        Alquiler obj = service.save(entity);
        return new ResponseEntity<>(modelMapper.map(obj, AlquilerDto.class), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<AlquilerDto> update(@PathVariable Integer id, @RequestBody AlquilerDto alquiler) throws Exception {
        alquiler.setIdAlquiler(id);
        Alquiler entity = modelMapper.map(alquiler, Alquiler.class);
        Alquiler obj = service.update(id, entity);
        return ResponseEntity.ok(modelMapper.map(obj, AlquilerDto.class));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
