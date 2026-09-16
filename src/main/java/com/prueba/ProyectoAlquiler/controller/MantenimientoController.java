package com.prueba.ProyectoAlquiler.controller;

import com.prueba.ProyectoAlquiler.model.Mantenimiento;
import com.prueba.ProyectoAlquiler.service.interfaz.IMantenimientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/mantenimientos")
@RequiredArgsConstructor
public class MantenimientoController {
    private final IMantenimientoService service;

    @GetMapping
    public ResponseEntity<List<Mantenimiento>> findAll() throws Exception {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mantenimiento> findById(@PathVariable Integer id) throws Exception {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Mantenimiento> save(@RequestBody Mantenimiento mantenimiento) throws Exception {
        Mantenimiento obj = service.save(mantenimiento);
        return new ResponseEntity<>(obj, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mantenimiento> update(@PathVariable Integer id, @RequestBody Mantenimiento mantenimiento) throws Exception {
        return ResponseEntity.ok(service.update(id, mantenimiento));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
