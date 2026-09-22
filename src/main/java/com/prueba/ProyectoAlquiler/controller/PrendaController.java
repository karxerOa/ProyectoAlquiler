package com.prueba.ProyectoAlquiler.controller;

import com.prueba.ProyectoAlquiler.dto.PrendaDto;
import com.prueba.ProyectoAlquiler.model.Prenda;
import com.prueba.ProyectoAlquiler.service.interfaz.IPrendaService;
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
@RequestMapping("/v1/prendas")
public class PrendaController {
    private final IPrendaService service;
    private final ModelMapper modelMapper;

    public PrendaController(IPrendaService service,
        @Qualifier("prendaMapper") ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<PrendaDto>> findAll() throws Exception {
        return ResponseEntity.ok(service.findAll().stream().map(entity -> modelMapper.map(entity, PrendaDto.class)).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrendaDto> findById(@PathVariable Integer id) throws Exception {
        return ResponseEntity.ok(modelMapper.map(service.findById(id), PrendaDto.class));
    }

    @PostMapping
    public ResponseEntity<PrendaDto> save(@RequestBody PrendaDto prenda) throws Exception {
        Prenda obj = service.save(modelMapper.map(prenda, Prenda.class));
        return new ResponseEntity<>(modelMapper.map(obj, PrendaDto.class), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PrendaDto> update(@PathVariable Integer id, @RequestBody PrendaDto prenda) throws Exception {
        prenda.setIdPrenda(id);
        Prenda obj = service.update(id, modelMapper.map(prenda, Prenda.class));
        return ResponseEntity.ok(modelMapper.map(obj, PrendaDto.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
