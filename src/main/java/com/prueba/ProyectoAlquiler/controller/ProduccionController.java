package com.prueba.ProyectoAlquiler.controller;

import com.prueba.ProyectoAlquiler.dto.ProduccionDto;
import com.prueba.ProyectoAlquiler.model.Produccion;
import com.prueba.ProyectoAlquiler.service.interfaz.IProduccionService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/producciones")
public class ProduccionController {

    private final IProduccionService service;
    private final ModelMapper modelMapper;

    public ProduccionController(IProduccionService service,
                                @Qualifier("produccionMapper") ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<ProduccionDto>> findAll() throws Exception {
        return ResponseEntity.ok(service.findAll().stream()
                .map(entity -> modelMapper.map(entity, ProduccionDto.class))
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProduccionDto> findById(@PathVariable Integer id) throws Exception {
        return ResponseEntity.ok(modelMapper.map(service.findById(id), ProduccionDto.class));
    }

    @PostMapping
    public ResponseEntity<ProduccionDto> save(@RequestBody ProduccionDto produccion) throws Exception {
        Produccion entity = modelMapper.map(produccion, Produccion.class);
        Produccion obj = service.save(entity);
        return new ResponseEntity<>(modelMapper.map(obj, ProduccionDto.class), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProduccionDto> update(@PathVariable Integer id, @RequestBody ProduccionDto produccion) throws Exception {
        produccion.setIdProduccion(id);
        Produccion entity = modelMapper.map(produccion, Produccion.class);
        Produccion obj = service.update(id, entity);
        return ResponseEntity.ok(modelMapper.map(obj, ProduccionDto.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}