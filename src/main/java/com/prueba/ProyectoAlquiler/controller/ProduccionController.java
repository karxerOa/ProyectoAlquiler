package com.prueba.ProyectoAlquiler.controller;

import com.prueba.ProyectoAlquiler.model.Produccion;
import com.prueba.ProyectoAlquiler.service.interfaz.IProduccionService;
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
@RequestMapping("/v1/producciones")
@RequiredArgsConstructor
public class ProduccionController {
    private final IProduccionService service;

    @GetMapping
    public ResponseEntity<List<Produccion>> findAll() throws Exception {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produccion> findById(@PathVariable Integer id) throws Exception {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Produccion> save(@RequestBody Produccion produccion) throws Exception {
        return new ResponseEntity<>(service.save(produccion), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produccion> update(@PathVariable Integer id, @RequestBody Produccion produccion) throws Exception {
        return ResponseEntity.ok(service.update(id, produccion));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
