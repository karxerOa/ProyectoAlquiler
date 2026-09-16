package com.prueba.ProyectoAlquiler.controller;

import com.prueba.ProyectoAlquiler.model.Pago;
import com.prueba.ProyectoAlquiler.service.interfaz.IPagoService;
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
@RequestMapping("/v1/pagos")
@RequiredArgsConstructor
public class PagoController {
    private final IPagoService service;

    @GetMapping
    public ResponseEntity<List<Pago>> findAll() throws Exception {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pago> findById(@PathVariable Integer id) throws Exception {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Pago> save(@RequestBody Pago pago) throws Exception {
        return new ResponseEntity<>(service.save(pago), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pago> update(@PathVariable Integer id, @RequestBody Pago pago) throws Exception {
        return ResponseEntity.ok(service.update(id, pago));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
