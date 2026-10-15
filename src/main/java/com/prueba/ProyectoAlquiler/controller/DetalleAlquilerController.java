package com.prueba.ProyectoAlquiler.controller;

import com.prueba.ProyectoAlquiler.dto.DetalleAlquilerDto;
import com.prueba.ProyectoAlquiler.model.DetalleAlquiler;
import com.prueba.ProyectoAlquiler.service.interfaz.IDetalleAlquilerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/detalles-alquiler")
public class DetalleAlquilerController {

    private final IDetalleAlquilerService service;
    private final ModelMapper modelMapper;

    public DetalleAlquilerController(IDetalleAlquilerService service,
        @Qualifier("detalleAlquilerMapper") ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<DetalleAlquilerDto>> findAll() throws Exception {
        return ResponseEntity.ok(service.findAll().stream()
            .map(entity -> modelMapper.map(entity, DetalleAlquilerDto.class))
            .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleAlquilerDto> findById(@PathVariable Integer id) throws Exception {
        return ResponseEntity.ok(modelMapper.map(service.findById(id), DetalleAlquilerDto.class));
    }

    @PostMapping
    public ResponseEntity<DetalleAlquilerDto> save(@RequestBody DetalleAlquilerDto detalleAlquiler)
        throws Exception {
        DetalleAlquiler entity = modelMapper.map(detalleAlquiler, DetalleAlquiler.class);
        return new ResponseEntity<>(
            modelMapper.map(service.save(entity), DetalleAlquilerDto.class),
            HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleAlquilerDto> update(@PathVariable Integer id,
        @RequestBody DetalleAlquilerDto detalleAlquiler) throws Exception {
        detalleAlquiler.setIdDetalle(id);
        DetalleAlquiler entity = modelMapper.map(detalleAlquiler, DetalleAlquiler.class);
        return ResponseEntity.ok(
            modelMapper.map(service.update(id, entity), DetalleAlquilerDto.class)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
