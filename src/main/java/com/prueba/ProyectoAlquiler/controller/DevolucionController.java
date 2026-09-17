package com.prueba.ProyectoAlquiler.controller;

import com.prueba.ProyectoAlquiler.model.Devolucion;
import com.prueba.ProyectoAlquiler.service.interfaz.IDevolucionService;
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
@RequestMapping("/v1/devoluciones")
@RequiredArgsConstructor
public class DevolucionController {
    private final IDevolucionService service;

    @GetMapping
    public ResponseEntity<List<Devolucion>> findAll() throws Exception {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Devolucion> findById(@PathVariable Integer id) throws Exception {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Devolucion> save(@RequestBody Devolucion devolucion) throws Exception {
        return new ResponseEntity<>(service.save(devolucion), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Devolucion> update(@PathVariable Integer id, @RequestBody Devolucion devolucion) throws Exception {
        return ResponseEntity.ok(service.update(id, devolucion));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
