package com.prueba.ProyectoAlquiler.controller;

import com.prueba.ProyectoAlquiler.dto.PenalidadDto;
import com.prueba.ProyectoAlquiler.model.Penalidad;
import com.prueba.ProyectoAlquiler.service.interfaz.IPenalidadService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/penalidades")
public class PenalidadController {
    private final IPenalidadService service;
    private final ModelMapper modelMapper;

    public PenalidadController(IPenalidadService service,
        @Qualifier("penalidadMapper") ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<PenalidadDto>> findAll() throws Exception {
        return ResponseEntity.ok(service.findAll().stream().map(entity -> modelMapper.map(entity, PenalidadDto.class)).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PenalidadDto> findById(@PathVariable Integer id) throws Exception {
        return ResponseEntity.ok(modelMapper.map(service.findById(id), PenalidadDto.class));
    }

    @PostMapping
    public ResponseEntity<PenalidadDto> save(@RequestBody PenalidadDto penalidad) throws Exception {
        Penalidad entity = modelMapper.map(penalidad, Penalidad.class);
        return new ResponseEntity<>(modelMapper.map(service.save(entity), PenalidadDto.class), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PenalidadDto> update(@PathVariable Integer id, @RequestBody PenalidadDto penalidad) throws Exception {
        Penalidad entity = modelMapper.map(penalidad, Penalidad.class);
        return ResponseEntity.ok(modelMapper.map(service.update(id, entity), PenalidadDto.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
