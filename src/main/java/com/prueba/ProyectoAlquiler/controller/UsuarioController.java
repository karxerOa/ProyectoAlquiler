package com.prueba.ProyectoAlquiler.controller;

import com.prueba.ProyectoAlquiler.dto.UsuarioDto;
import com.prueba.ProyectoAlquiler.model.Usuario;
import com.prueba.ProyectoAlquiler.service.interfaz.IUsuarioService;
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
@RequestMapping("/v1/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
    private final IUsuarioService service;
    @Qualifier("usuarioMapper")
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<UsuarioDto>> findAll() throws Exception {
        return ResponseEntity.ok(service.findAll().stream().map(entity -> modelMapper.map(entity, UsuarioDto.class)).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> findById(@PathVariable Integer id) throws Exception {
        return ResponseEntity.ok(modelMapper.map(service.findById(id), UsuarioDto.class));
    }

    @PostMapping
    public ResponseEntity<UsuarioDto> save(@RequestBody UsuarioDto usuario) throws Exception {
        Usuario entity = modelMapper.map(usuario, Usuario.class);
        return new ResponseEntity<>(modelMapper.map(service.save(entity), UsuarioDto.class), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDto> update(@PathVariable Integer id, @RequestBody UsuarioDto usuario) throws Exception {
        usuario.setIdUser(id);
        Usuario entity = modelMapper.map(usuario, Usuario.class);
        return ResponseEntity.ok(modelMapper.map(service.update(id, entity), UsuarioDto.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
