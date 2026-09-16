package com.prueba.ProyectoAlquiler.controller;
import com.prueba.ProyectoAlquiler.model.DetalleAlquiler;
import com.prueba.ProyectoAlquiler.service.interfaz.IDetalleAlquilerService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/detalles-alquiler")
@RequiredArgsConstructor
public class DetalleAlquilerController {
    private final IDetalleAlquilerService service;

    @GetMapping
    public ResponseEntity<List<DetalleAlquiler>> findAll() throws Exception {
        List<DetalleAlquiler> list = service.findAll();
        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<DetalleAlquiler> findById(@PathVariable Integer id) throws Exception {
        DetalleAlquiler obj = service.findById(id);
        return ResponseEntity.ok(obj);
    }
    @PostMapping
    public ResponseEntity<DetalleAlquiler> save(@RequestBody DetalleAlquiler detalleAlquiler) throws Exception {
        DetalleAlquiler obj = service.save(detalleAlquiler);
        return new ResponseEntity<>(obj, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<DetalleAlquiler> update(@PathVariable Integer id, @RequestBody DetalleAlquiler detalleAlquiler) throws Exception {
        DetalleAlquiler obj = service.update(id, detalleAlquiler);
        return ResponseEntity.ok(obj);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
