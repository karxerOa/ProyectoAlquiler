package com.prueba.ProyectoAlquiler.controller;
import com.prueba.ProyectoAlquiler.dto.AlquilerDto;
import com.prueba.ProyectoAlquiler.model.Alquiler;
import com.prueba.ProyectoAlquiler.repository.IEmpleadoRepository;
import com.prueba.ProyectoAlquiler.service.interfaz.IAlquilerService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/alquileres")
public class AlquilerController {
    private final IAlquilerService service;
    private final ModelMapper modelMapper;
    private final IEmpleadoRepository empleadoRepository;

    public AlquilerController(IAlquilerService service,
        @Qualifier("alquilerMapper") ModelMapper modelMapper,
        IEmpleadoRepository empleadoRepository) {
        this.service = service;
        this.modelMapper = modelMapper;
        this.empleadoRepository = empleadoRepository;
    }

    @GetMapping
    public ResponseEntity<List<AlquilerDto>> findAll() throws Exception {
        return ResponseEntity.ok(service.findAll().stream()
            .map(entity -> modelMapper.map(entity, AlquilerDto.class))
            .toList());
    }
    @GetMapping("/{id}")
    public ResponseEntity<AlquilerDto> findById(@PathVariable Integer id) throws Exception {
        return ResponseEntity.ok(modelMapper.map(service.findById(id), AlquilerDto.class));
    }
    @PostMapping
    public ResponseEntity<AlquilerDto> save(@RequestBody AlquilerDto alquiler,
                                            Authentication authentication) throws Exception {
        alquiler.setIdAlquiler(null);
        Alquiler entity = modelMapper.map(alquiler, Alquiler.class);
        var empleado = empleadoRepository.findByUsuarioUsername(authentication.getName());
        if (empleado.isPresent()) {
            entity.setEmpleado(empleado.get());
        } else if (authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_EMPLEADO"))) {
            throw new IllegalArgumentException("Tu cuenta no está vinculada a un empleado");
        } else if (alquiler.getEmpleadoId() == null) {
            throw new IllegalArgumentException("Debe seleccionar un empleado responsable");
        }
        Alquiler obj = service.save(entity);
        return new ResponseEntity<>(modelMapper.map(obj, AlquilerDto.class), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<AlquilerDto> update(@PathVariable Integer id, @RequestBody AlquilerDto alquiler) throws Exception {
        alquiler.setIdAlquiler(id);
        Alquiler entity = modelMapper.map(alquiler, Alquiler.class);
        Alquiler obj = service.update(id, entity);
        return ResponseEntity.ok(modelMapper.map(obj, AlquilerDto.class));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
