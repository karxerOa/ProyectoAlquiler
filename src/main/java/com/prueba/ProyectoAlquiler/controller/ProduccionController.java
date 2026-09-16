package com.prueba.ProyectoAlquiler.controller;

import com.prueba.ProyectoAlquiler.model.Produccion;
import com.prueba.ProyectoAlquiler.service.interfaz.IProduccionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/producciones")
@RequiredArgsConstructor
public class ProduccionController {

    private final IProduccionService service;

    @GetMapping
    public ResponseEntity<List<Produccion>> findAll() throws Exception {
        List<Produccion> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produccion> findById(@PathVariable Integer id) throws Exception {
        Produccion obj = service.findById(id);
        return ResponseEntity.ok(obj);
    }

    @PostMapping
    public ResponseEntity<Produccion> save(@RequestBody Produccion produccion) throws Exception {
        Produccion obj = service.save(produccion);
        return new ResponseEntity<>(obj, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produccion> update(@PathVariable Integer id, @RequestBody Produccion produccion) throws Exception {
        Produccion obj = service.update(id, produccion);
        return ResponseEntity.ok(obj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}