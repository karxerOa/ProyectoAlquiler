package com.prueba.ProyectoAlquiler.controller;

import com.prueba.ProyectoAlquiler.dto.EmpleadoDto;
import com.prueba.ProyectoAlquiler.model.Empleado;
import com.prueba.ProyectoAlquiler.service.interfaz.IEmpleadoService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/v1/empleados")
@RequiredArgsConstructor

public class EmpleadoController {

    private final IEmpleadoService service;
    @Qualifier("empleadoMapper")
    private final ModelMapper modelMapper;

    @GetMapping // 200 -> ok
    public ResponseEntity<List<EmpleadoDto>> findAll() throws Exception{
        return ResponseEntity.ok(service.findAll().stream().map(entity -> modelMapper.map(entity, EmpleadoDto.class)).toList());
    }

    @GetMapping("/{id}") // 200 -> ok
    public ResponseEntity<EmpleadoDto> findById(@PathVariable Integer id) throws Exception{
        return ResponseEntity.ok(modelMapper.map(service.findById(id), EmpleadoDto.class));
    }

    @PostMapping // 201 --> Created
    public ResponseEntity<EmpleadoDto> save(@RequestBody EmpleadoDto empleado) throws Exception{
        Empleado obj = service.save(modelMapper.map(empleado, Empleado.class));
        return new ResponseEntity<>(modelMapper.map(obj, EmpleadoDto.class), HttpStatus.CREATED);
    }

    @PutMapping("/{id}") // 200 ok
    public ResponseEntity<EmpleadoDto> update(@PathVariable Integer id, @RequestBody EmpleadoDto empleado) throws Exception{
        empleado.setIdEmpleado(id);
        Empleado obj = service.update(id, modelMapper.map(empleado, Empleado.class));
        return ResponseEntity.ok(modelMapper.map(obj, EmpleadoDto.class));
    }

    @DeleteMapping("/{id}") // 204 no content
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) throws Exception{
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
