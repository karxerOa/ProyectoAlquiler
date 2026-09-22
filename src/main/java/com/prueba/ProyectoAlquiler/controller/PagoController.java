package com.prueba.ProyectoAlquiler.controller;

import com.prueba.ProyectoAlquiler.dto.PagoDto;
import com.prueba.ProyectoAlquiler.model.Pago;
import com.prueba.ProyectoAlquiler.service.interfaz.IPagoService;
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
@RequestMapping("/v1/pagos")
public class PagoController {
    private final IPagoService service;
    private final ModelMapper modelMapper;

    public PagoController(IPagoService service,
        @Qualifier("pagoMapper") ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<PagoDto>> findAll() throws Exception {
        return ResponseEntity.ok(service.findAll().stream().map(entity -> modelMapper.map(entity, PagoDto.class)).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagoDto> findById(@PathVariable Integer id) throws Exception {
        return ResponseEntity.ok(modelMapper.map(service.findById(id), PagoDto.class));
    }

    @PostMapping
    public ResponseEntity<PagoDto> save(@RequestBody PagoDto pago) throws Exception {
        Pago entity = modelMapper.map(pago, Pago.class);
        return new ResponseEntity<>(modelMapper.map(service.save(entity), PagoDto.class), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagoDto> update(@PathVariable Integer id, @RequestBody PagoDto pago) throws Exception {
        
        Pago entity = modelMapper.map(pago, Pago.class);
        return ResponseEntity.ok(modelMapper.map(service.update(id, entity), PagoDto.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
