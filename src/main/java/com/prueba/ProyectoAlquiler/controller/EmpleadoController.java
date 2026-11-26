package com.prueba.ProyectoAlquiler.controller;

import com.prueba.ProyectoAlquiler.dto.EmpleadoDto;
import com.prueba.ProyectoAlquiler.model.Empleado;
import com.prueba.ProyectoAlquiler.service.interfaz.IEmpleadoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/empleados")
public class EmpleadoController {

    private final IEmpleadoService service;
    private final ModelMapper modelMapper;

    public EmpleadoController(IEmpleadoService service,
        @Qualifier("empleadoMapper") ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @GetMapping // 200 -> ok
    public ResponseEntity<List<EmpleadoDto>> findAll() throws Exception{
        return ResponseEntity.ok(service.findAll().stream().map(entity -> modelMapper.map(entity, EmpleadoDto.class)).toList());
    }

    @GetMapping("/{id}") // 200 -> ok
    public ResponseEntity<EmpleadoDto> findById(@PathVariable Integer id) throws Exception{
        return ResponseEntity.ok(modelMapper.map(service.findById(id), EmpleadoDto.class));
    }

    @GetMapping("/me")
    public ResponseEntity<EmpleadoDto> findCurrentEmployee(Authentication authentication) throws Exception {
        return ResponseEntity.ok(modelMapper.map(
                service.findByUsuarioUsername(authentication.getName()), EmpleadoDto.class));
    }

    @PostMapping // 201 --> Created
    public ResponseEntity<EmpleadoDto> save(@RequestBody EmpleadoDto empleado) throws Exception{
        empleado.setIdEmpleado(null);
        Empleado obj = service.createWithAccount(
                modelMapper.map(empleado, Empleado.class),
                empleado.getUsername(),
                empleado.getPassword());
        return new ResponseEntity<>(modelMapper.map(obj, EmpleadoDto.class), HttpStatus.CREATED);
    }

    @PutMapping("/{id}") // 200 ok
    public ResponseEntity<EmpleadoDto> update(@PathVariable Integer id, @RequestBody EmpleadoDto empleado) throws Exception{
        empleado.setIdEmpleado(id);
        Empleado obj = service.updateWithAccount(
                id,
                modelMapper.map(empleado, Empleado.class),
                empleado.getUsername(),
                empleado.getPassword());
        return ResponseEntity.ok(modelMapper.map(obj, EmpleadoDto.class));
    }

    @DeleteMapping("/{id}") // 204 no content
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) throws Exception{
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
