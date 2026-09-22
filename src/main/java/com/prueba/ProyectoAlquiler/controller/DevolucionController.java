package com.prueba.ProyectoAlquiler.controller;

import com.prueba.ProyectoAlquiler.dto.DevolucionDto;
import com.prueba.ProyectoAlquiler.model.Devolucion;
import com.prueba.ProyectoAlquiler.service.interfaz.IDevolucionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
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
@RequestMapping("/v1/devoluciones")
public class DevolucionController {
    private final IDevolucionService service;
    private final ModelMapper modelMapper;

    public DevolucionController(IDevolucionService service,
        @Qualifier("devolucionMapper") ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<DevolucionDto>> findAll() throws Exception {
        return ResponseEntity.ok(service.findAll().stream()
            .map(entity -> modelMapper.map(entity, DevolucionDto.class))
            .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DevolucionDto> findById(@PathVariable Integer id) throws Exception {
        return ResponseEntity.ok(modelMapper.map(service.findById(id), DevolucionDto.class));
    }

    @PostMapping
    public ResponseEntity<DevolucionDto> save(@RequestBody DevolucionDto devolucion) throws Exception {
        Devolucion entity = modelMapper.map(devolucion, Devolucion.class);
        return new ResponseEntity<>(modelMapper.map(service.save(entity), DevolucionDto.class), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DevolucionDto> update(@PathVariable Integer id, @RequestBody DevolucionDto devolucion) throws Exception {
        devolucion.setIdDevolucion(id);
        Devolucion entity = modelMapper.map(devolucion, Devolucion.class);
        return ResponseEntity.ok(modelMapper.map(service.update(id, entity), DevolucionDto.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
