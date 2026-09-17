package com.prueba.ProyectoAlquiler.controller;

import com.prueba.ProyectoAlquiler.model.Penalidad;
import com.prueba.ProyectoAlquiler.service.interfaz.IPenalidadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequiredArgsConstructor
public class PenalidadController {
    private final IPenalidadService service;

    @GetMapping
    public ResponseEntity<List<Penalidad>> findAll() throws Exception {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Penalidad> findById(@PathVariable Integer id) throws Exception {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Penalidad> save(@RequestBody Penalidad penalidad) throws Exception {
        return new ResponseEntity<>(service.save(penalidad), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Penalidad> update(@PathVariable Integer id, @RequestBody Penalidad penalidad) throws Exception {
        return ResponseEntity.ok(service.update(id, penalidad));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
