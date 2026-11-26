package com.prueba.ProyectoAlquiler.controller;

import com.prueba.ProyectoAlquiler.dto.PrendaDto;
import com.prueba.ProyectoAlquiler.model.Prenda;
import com.prueba.ProyectoAlquiler.service.interfaz.IPrendaService;
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
import java.util.Base64;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.imageio.stream.MemoryCacheImageInputStream;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/v1/prendas")
public class PrendaController {
    private final IPrendaService service;
    private final ModelMapper modelMapper;

    public PrendaController(IPrendaService service,
        @Qualifier("prendaMapper") ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<PrendaDto>> findAll() throws Exception {
        return ResponseEntity.ok(service.findAll().stream().map(entity -> modelMapper.map(entity, PrendaDto.class)).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrendaDto> findById(@PathVariable Integer id) throws Exception {
        return ResponseEntity.ok(modelMapper.map(service.findById(id), PrendaDto.class));
    }

    @PostMapping
    @PreAuthorize("#p0.imagen == null or hasRole('ADMIN')")
    public ResponseEntity<PrendaDto> save(@RequestBody PrendaDto prenda) throws Exception {
        validarImagen(prenda.getImagen());
        prenda.setIdPrenda(null);
        Prenda obj = service.save(modelMapper.map(prenda, Prenda.class));
        return new ResponseEntity<>(modelMapper.map(obj, PrendaDto.class), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("#p1.imagen == null or hasRole('ADMIN')")
    public ResponseEntity<PrendaDto> update(@PathVariable Integer id, @RequestBody PrendaDto prenda) throws Exception {
        validarImagen(prenda.getImagen());
        prenda.setIdPrenda(id);
        Prenda obj = service.update(id, modelMapper.map(prenda, Prenda.class));
        return ResponseEntity.ok(modelMapper.map(obj, PrendaDto.class));
    }

    private static void validarImagen(String imagen) {
        if (imagen == null) return;
        int separador = imagen.indexOf(',');
        if (separador < 0 || imagen.length() > 1_398_130) {
            throw new IllegalArgumentException("Usa una imagen JPG o PNG de hasta 1 MiB.");
        }
        String formato = switch (imagen.substring(0, separador)) {
            case "data:image/jpeg;base64" -> "JPEG";
            case "data:image/png;base64" -> "PNG";
            default -> throw new IllegalArgumentException("Formato de imagen no permitido.");
        };
        byte[] bytes = Base64.getDecoder().decode(imagen.substring(separador + 1));
        if (bytes.length > 1_048_576) throw new IllegalArgumentException("La imagen supera 1 MiB.");
        try (var entrada = new MemoryCacheImageInputStream(new ByteArrayInputStream(bytes))) {
            var lectores = ImageIO.getImageReaders(entrada);
            if (!lectores.hasNext()) throw new IllegalArgumentException("Imagen no reconocida.");
            var lector = lectores.next();
            try {
                lector.setInput(entrada);
                if (!formato.equalsIgnoreCase(lector.getFormatName())
                        || (long) lector.getWidth(0) * lector.getHeight(0) > 16_000_000) {
                    throw new IllegalArgumentException("Revisa el formato y el limite de 16 megapixeles.");
                }
                lector.read(0);
            } finally {
                lector.dispose();
            }
        } catch (IOException ex) {
            throw new IllegalArgumentException("No se pudo leer la imagen.", ex);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
