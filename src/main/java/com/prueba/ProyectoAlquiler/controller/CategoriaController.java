package com.prueba.ProyectoAlquiler.controller;

import com.prueba.ProyectoAlquiler.dto.CategoriaDto;
import com.prueba.ProyectoAlquiler.model.Categoria;
import com.prueba.ProyectoAlquiler.service.interfaz.ICategoriaService;
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
@RequestMapping("/v1/categorias")
public class CategoriaController {
    private final ICategoriaService service;
    private final ModelMapper modelMapper;

    public CategoriaController(ICategoriaService service,
        @Qualifier("categoriaMapper") ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDto>> findAll() throws Exception {
        return ResponseEntity.ok(service.findAll().stream()
            .map(entity -> modelMapper.map(entity, CategoriaDto.class))
            .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDto> findById(@PathVariable Integer id) throws Exception {
        return ResponseEntity.ok(modelMapper.map(service.findById(id), CategoriaDto.class));
    }

    @PostMapping
    public ResponseEntity<CategoriaDto> save(@RequestBody CategoriaDto categoria) throws Exception {
        Categoria entity = modelMapper.map(categoria, Categoria.class);
        return new ResponseEntity<>(modelMapper.map(service.save(entity), CategoriaDto.class), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDto> update(@PathVariable Integer id, @RequestBody CategoriaDto categoria) throws Exception {
        categoria.setIdCategoria(id);
        Categoria entity = modelMapper.map(categoria, Categoria.class);
        return ResponseEntity.ok(modelMapper.map(service.update(id, entity), CategoriaDto.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
