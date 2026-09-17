package com.prueba.ProyectoAlquiler.controller;

import com.prueba.ProyectoAlquiler.model.Empleado;
import com.prueba.ProyectoAlquiler.service.interfaz.IEmpleadoService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/v1/empleados")
@RequiredArgsConstructor

public class EmpleadoController {

    private final IEmpleadoService service;

    @GetMapping // 200 -> ok
    public ResponseEntity<List<Empleado>> findAll() throws Exception{
        List<Empleado> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}") // 200 -> ok
    public ResponseEntity<Empleado> findById(@PathVariable Integer id) throws Exception{
        Empleado obj = service.findById(id);
        return ResponseEntity.ok(obj);
    }

    @PostMapping // 201 --> Created
    public ResponseEntity<Empleado> save(@RequestBody Empleado empleado) throws Exception{
        Empleado obj = service.save(empleado);
        return new ResponseEntity<>(obj, HttpStatus.CREATED);
    }

    @PutMapping("/{id}") // 200 ok
    public ResponseEntity<Empleado> update(@PathVariable Integer id, @RequestBody Empleado empleado) throws Exception{
        Empleado obj = service.update(id, empleado);
        return ResponseEntity.ok(obj);
    }

    @DeleteMapping("/{id}") // 204 no content
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) throws Exception{
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
