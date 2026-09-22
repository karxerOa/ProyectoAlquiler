package com.prueba.ProyectoAlquiler.controller;
import com.prueba.ProyectoAlquiler.dto.ClienteDto;
import com.prueba.ProyectoAlquiler.model.Cliente;

import com.prueba.ProyectoAlquiler.service.interfaz.IClienteService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/v1/clientes")
public class ClienteController {
    private final IClienteService service;
    private final ModelMapper modelMapper;

    public ClienteController(IClienteService service,
        @Qualifier("clienteMapper") ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }
    @GetMapping // 200 -> ok
    public ResponseEntity<List<ClienteDto>> findAll() throws Exception{
        return ResponseEntity.ok(service.findAll().stream()
            .map(entity -> modelMapper.map(entity, ClienteDto.class))
            .toList());
    }

    @GetMapping("/{id}") // 200 -> ok
    public ResponseEntity<ClienteDto> findById(@PathVariable Integer id) throws Exception{
        return ResponseEntity.ok(modelMapper.map(service.findById(id), ClienteDto.class));
    }

    @PostMapping // 201 --> Created
    public ResponseEntity<ClienteDto> save(@RequestBody ClienteDto cliente) throws Exception{
        Cliente entity = modelMapper.map(cliente, Cliente.class);
        Cliente obj = service.save(entity);
        return new ResponseEntity<>(modelMapper.map(obj, ClienteDto.class), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDto> update(@PathVariable Integer id, @RequestBody ClienteDto cliente) throws Exception{
        cliente.setIdCliente(id);
        Cliente entity = modelMapper.map(cliente, Cliente.class);
        Cliente obj = service.update(id, entity);
        return ResponseEntity.ok(modelMapper.map(obj, ClienteDto.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) throws Exception{
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
