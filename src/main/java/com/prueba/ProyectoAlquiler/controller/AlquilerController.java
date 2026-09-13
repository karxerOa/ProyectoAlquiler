package com.prueba.ProyectoAlquiler.controller;
import com.prueba.ProyectoAlquiler.model.Alquiler;
import com.prueba.ProyectoAlquiler.service.interfaz.IAlquilerService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/alquileres")
@RequiredArgsConstructor
public class AlquilerController {
    private final IAlquilerService service;
    @GetMapping
    public ResponseEntity<List<Alquiler>> findAll() throws Exception {
        List<Alquiler> list = service.findAll();
        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Alquiler> findById(@PathVariable Integer id) throws Exception {
        Alquiler obj = service.findById(id);
        return ResponseEntity.ok(obj);
    }
    @PostMapping
    public ResponseEntity<Alquiler> save(@RequestBody Alquiler alquiler) throws Exception {
        Alquiler obj = service.save(alquiler);
        return new ResponseEntity<>(obj, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Alquiler> update(@PathVariable Integer id, @RequestBody Alquiler alquiler) throws Exception {
        Alquiler obj = service.update(id, alquiler);
        return ResponseEntity.ok(obj);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
