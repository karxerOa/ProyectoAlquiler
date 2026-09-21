package com.prueba.ProyectoAlquiler.controller;

import com.prueba.ProyectoAlquiler.dto.MantenimientoDto;
import com.prueba.ProyectoAlquiler.model.Mantenimiento;
import com.prueba.ProyectoAlquiler.service.interfaz.IMantenimientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
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
@RequestMapping("/v1/mantenimientos")
@RequiredArgsConstructor
public class MantenimientoController {
    private final IMantenimientoService service;
    @Qualifier("mantenimientoMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<MantenimientoDto>> findAll() throws Exception {
        return ResponseEntity.ok(service.findAll().stream().map(entity -> modelMapper.map(entity, MantenimientoDto.class)).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MantenimientoDto> findById(@PathVariable Integer id) throws Exception {
        return ResponseEntity.ok(modelMapper.map(service.findById(id), MantenimientoDto.class));
    }

    @PostMapping
    public ResponseEntity<MantenimientoDto> save(@RequestBody MantenimientoDto mantenimiento) throws Exception {
        Mantenimiento entity = modelMapper.map(mantenimiento, Mantenimiento.class);
        return new ResponseEntity<>(modelMapper.map(service.save(entity), MantenimientoDto.class), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MantenimientoDto> update(@PathVariable Integer id, @RequestBody MantenimientoDto mantenimiento) throws Exception {
        mantenimiento.setIdMantenimiento(id);
        Mantenimiento entity = modelMapper.map(mantenimiento, Mantenimiento.class);
        return ResponseEntity.ok(modelMapper.map(service.update(id, entity), MantenimientoDto.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
