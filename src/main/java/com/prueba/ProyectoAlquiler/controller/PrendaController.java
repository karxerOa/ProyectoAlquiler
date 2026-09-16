package com.prueba.ProyectoAlquiler.controller;

import com.prueba.ProyectoAlquiler.model.Prenda;
import com.prueba.ProyectoAlquiler.service.interfaz.IPrendaService;
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
@RequestMapping("/v1/prendas")
@RequiredArgsConstructor
public class PrendaController {
    private final IPrendaService service;

    @GetMapping
    public ResponseEntity<List<Prenda>> findAll() throws Exception {
        List<Prenda> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prenda> findById(@PathVariable Integer id) throws Exception {
        Prenda obj = service.findById(id);
        return ResponseEntity.ok(obj);
    }

    @PostMapping
    public ResponseEntity<Prenda> save(@RequestBody Prenda prenda) throws Exception {
        Prenda obj = service.save(prenda);
        return new ResponseEntity<>(obj, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prenda> update(@PathVariable Integer id, @RequestBody Prenda prenda) throws Exception {
        Prenda obj = service.update(id, prenda);
        return ResponseEntity.ok(obj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
