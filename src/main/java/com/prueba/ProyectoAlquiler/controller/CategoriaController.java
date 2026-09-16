package com.prueba.ProyectoAlquiler.controller;

import com.prueba.ProyectoAlquiler.model.Categoria;
import com.prueba.ProyectoAlquiler.service.interfaz.ICategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final ICategoriaService service;

    @GetMapping
    public ResponseEntity<List<Categoria>> findAll() throws Exception {
        List<Categoria> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> findById(@PathVariable Integer id) throws Exception {
        Categoria obj = service.findById(id);
        return ResponseEntity.ok(obj);
    }

    @PostMapping
    public ResponseEntity<Categoria> save(@RequestBody Categoria categoria) throws Exception {
        Categoria obj = service.save(categoria);
        return new ResponseEntity<>(obj, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> update(@PathVariable Integer id, @RequestBody Categoria categoria) throws Exception {
        Categoria obj = service.update(id, categoria);
        return ResponseEntity.ok(obj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}