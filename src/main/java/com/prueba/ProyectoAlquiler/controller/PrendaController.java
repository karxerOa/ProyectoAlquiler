package com.prueba.ProyectoAlquiler.controller;

import com.prueba.ProyectoAlquiler.model.Prenda;
import com.prueba.ProyectoAlquiler.service.interfaz.IPrendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/prendas")
@RequiredArgsConstructor
public class PrendaController {
    private final IPrendaService service;

    @GetMapping
    public ResponseEntity<List<Prenda>> findAll() throws Exception {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prenda> findById(@PathVariable Integer id) throws Exception {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Prenda> save(@RequestBody Prenda prenda) throws Exception {
        Prenda obj = service.save(prenda);
        return new ResponseEntity<>(obj, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prenda> update(@PathVariable Integer id, @RequestBody Prenda prenda) throws Exception {
        return ResponseEntity.ok(service.update(id, prenda));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
