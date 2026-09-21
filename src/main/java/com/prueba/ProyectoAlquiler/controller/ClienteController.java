package com.prueba.ProyectoAlquiler.controller;
import com.prueba.ProyectoAlquiler.dto.ClienteDTO;
import com.prueba.ProyectoAlquiler.model.Cliente;

import com.prueba.ProyectoAlquiler.service.interfaz.IClienteService;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/v1/clientes")
@RequiredArgsConstructor
public class ClienteController {
    private final IClienteService service;

    private ModelMapper modelMapper;

    @GetMapping // 200 -> ok
    public ResponseEntity<List<ClienteDTO>> findAll() throws Exception{
        List<ClienteDTO>list = service.findAll().stream()
                .map(e-> modelMapper.map(e,ClienteDTO.class))
                .toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}") // 200 -> ok
    public ResponseEntity<Cliente> findById(@PathVariable Integer id) throws Exception{
        Cliente obj = service.findById(id);
        return ResponseEntity.ok(obj);
    }

    @PostMapping // 201 --> Created
    public ResponseEntity<Cliente> save(@RequestBody Cliente cliente) throws Exception{
        Cliente obj = service.save(cliente);
        return new ResponseEntity<>(obj, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> update(@PathVariable Integer id, @RequestBody Cliente cliente) throws Exception{
        Cliente obj = service.update(id, cliente);
        return ResponseEntity.ok(obj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) throws Exception{
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
