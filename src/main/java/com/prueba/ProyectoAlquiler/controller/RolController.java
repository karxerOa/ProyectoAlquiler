package com.prueba.ProyectoAlquiler.controller;

import com.prueba.ProyectoAlquiler.dto.RolDto;
import com.prueba.ProyectoAlquiler.model.Rol;
import com.prueba.ProyectoAlquiler.service.interfaz.IRolService;
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
@RequestMapping("/v1/roles")
public class RolController {
    private final IRolService service;
    private final ModelMapper modelMapper;

    public RolController(IRolService service,
    @Qualifier("rolMapper") ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<RolDto>> findAll() throws Exception {
        return ResponseEntity.ok(service.findAll().stream().map(entity -> modelMapper.map(entity, RolDto.class)).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RolDto> findById(@PathVariable Integer id) throws Exception {
        return ResponseEntity.ok(modelMapper.map(service.findById(id), RolDto.class));
    }

    @PostMapping
    public ResponseEntity<RolDto> save(@RequestBody RolDto rol) throws Exception {
        Rol entity = modelMapper.map(rol, Rol.class);
        return new ResponseEntity<>(modelMapper.map(service.save(entity), RolDto.class), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RolDto> update(@PathVariable Integer id, @RequestBody RolDto rol) throws Exception {
        rol.setIdRol(id);
        Rol entity = modelMapper.map(rol, Rol.class);
        return ResponseEntity.ok(modelMapper.map(service.update(id, entity), RolDto.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
