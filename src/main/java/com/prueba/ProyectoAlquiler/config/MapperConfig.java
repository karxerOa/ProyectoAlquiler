package com.prueba.ProyectoAlquiler.config;

import com.prueba.ProyectoAlquiler.dto.*;
import com.prueba.ProyectoAlquiler.model.*;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper defaultMapper() {
        return new ModelMapper();
    }

    @Bean
    public ModelMapper alquilerMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.createTypeMap(Alquiler.class, AlquilerDto.class)
            .addMapping(Alquiler::getIdAlquiler, AlquilerDto::setIdAlquiler)
            .addMapping(Alquiler::getFechaAlquiler, AlquilerDto::setFechaAlquiler)
            .addMapping(Alquiler::getFechaInicio, AlquilerDto::setFechaInicio)
            .addMapping(Alquiler::getFechaFin, AlquilerDto::setFechaFin)
            .addMapping(Alquiler::getEstado, AlquilerDto::setEstado)
            .addMapping(Alquiler::getSubtotal, AlquilerDto::setSubtotal)
            .addMapping(Alquiler::getTotal, AlquilerDto::setTotal)
            .addMappings(mapping -> {
                    mapping.map(src -> src.getEmpleado() == null ? null : src.getEmpleado().getIdEmpleado(), AlquilerDto::setEmpleadoId);
                    mapping.map(src -> src.getCliente() == null ? null : src.getCliente().getIdCliente(), AlquilerDto::setClienteId);
                });
        mapper.createTypeMap(AlquilerDto.class, Alquiler.class)
            .addMapping(AlquilerDto::getIdAlquiler, Alquiler::setIdAlquiler)
            .addMapping(AlquilerDto::getFechaAlquiler, Alquiler::setFechaAlquiler)
            .addMapping(AlquilerDto::getFechaInicio, Alquiler::setFechaInicio)
            .addMapping(AlquilerDto::getFechaFin, Alquiler::setFechaFin)
            .addMapping(AlquilerDto::getEstado, Alquiler::setEstado)
            .addMapping(AlquilerDto::getSubtotal, Alquiler::setSubtotal)
            .addMapping(AlquilerDto::getTotal, Alquiler::setTotal)
            .addMappings(mapping -> {
                    mapping.using(reference(Empleado::new, Empleado::setIdEmpleado)).map(AlquilerDto::getEmpleadoId, Alquiler::setEmpleado);
                    mapping.using(reference(Cliente::new, Cliente::setIdCliente)).map(AlquilerDto::getClienteId, Alquiler::setCliente);
                });
        return mapper;
    }

    @Bean
    public ModelMapper categoriaMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.createTypeMap(Categoria.class, CategoriaDto.class)
                .addMapping(Categoria::getIdCategoria, CategoriaDto::setIdCategoria)
                .addMapping(Categoria::getNombre, CategoriaDto::setNombre)
                .addMapping(Categoria::getDescripcion, CategoriaDto::setDescripcion)
                .addMapping(Categoria::getEstado, CategoriaDto::setEstado);
        mapper.createTypeMap(CategoriaDto.class, Categoria.class)
                .addMapping(CategoriaDto::getIdCategoria, Categoria::setIdCategoria)
                .addMapping(CategoriaDto::getNombre, Categoria::setNombre)
                .addMapping(CategoriaDto::getDescripcion, Categoria::setDescripcion)
                .addMapping(CategoriaDto::getEstado, Categoria::setEstado);
        return mapper;
    }

    @Bean
    public ModelMapper clienteMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.createTypeMap(Cliente.class, ClienteDto.class)
                .addMapping(Cliente::getIdCliente, ClienteDto::setIdCliente)
                .addMapping(Cliente::getNombre, ClienteDto::setNombre)
                .addMapping(Cliente::getApellidos, ClienteDto::setApellidos)
                .addMapping(Cliente::getDni, ClienteDto::setDni)
                .addMapping(Cliente::getTelefono, ClienteDto::setTelefono)
                .addMapping(Cliente::getDireccion, ClienteDto::setDireccion)
                .addMapping(Cliente::getCorreo, ClienteDto::setCorreo);
        mapper.createTypeMap(ClienteDto.class, Cliente.class)
                .addMapping(ClienteDto::getIdCliente, Cliente::setIdCliente)
                .addMapping(ClienteDto::getNombre, Cliente::setNombre)
                .addMapping(ClienteDto::getApellidos, Cliente::setApellidos)
                .addMapping(ClienteDto::getDni, Cliente::setDni)
                .addMapping(ClienteDto::getTelefono, Cliente::setTelefono)
                .addMapping(ClienteDto::getDireccion, Cliente::setDireccion)
                .addMapping(ClienteDto::getCorreo, Cliente::setCorreo);
        return mapper;
    }

    @Bean
    public ModelMapper detalleAlquilerMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.createTypeMap(DetalleAlquiler.class, DetalleAlquilerDto.class)
            .addMapping(DetalleAlquiler::getIdDetalle, DetalleAlquilerDto::setIdDetalle)
            .addMapping(DetalleAlquiler::getCantidad, DetalleAlquilerDto::setCantidad)
            .addMapping(DetalleAlquiler::getPrecioUnitario, DetalleAlquilerDto::setPrecioUnitario)
            .addMapping(DetalleAlquiler::getSubtotal, DetalleAlquilerDto::setSubtotal)
            .addMappings(mapping -> {
                    mapping.map(src -> src.getAlquiler() == null ? null : src.getAlquiler().getIdAlquiler(), DetalleAlquilerDto::setAlquilerId);
                    mapping.map(src -> src.getPrenda() == null ? null : src.getPrenda().getIdPrenda(), DetalleAlquilerDto::setPrendaId);
                });
        mapper.createTypeMap(DetalleAlquilerDto.class, DetalleAlquiler.class)
            .addMapping(DetalleAlquilerDto::getIdDetalle, DetalleAlquiler::setIdDetalle)
            .addMapping(DetalleAlquilerDto::getCantidad, DetalleAlquiler::setCantidad)
            .addMapping(DetalleAlquilerDto::getPrecioUnitario, DetalleAlquiler::setPrecioUnitario)
            .addMapping(DetalleAlquilerDto::getSubtotal, DetalleAlquiler::setSubtotal)
            .addMappings(mapping -> {
                    mapping.using(reference(Alquiler::new, Alquiler::setIdAlquiler)).map(DetalleAlquilerDto::getAlquilerId, DetalleAlquiler::setAlquiler);
                    mapping.using(reference(Prenda::new, Prenda::setIdPrenda)).map(DetalleAlquilerDto::getPrendaId, DetalleAlquiler::setPrenda);
                });
        return mapper;
    }

    @Bean
    public ModelMapper devolucionMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.createTypeMap(Devolucion.class, DevolucionDto.class)
            .addMapping(Devolucion::getIdDevolucion, DevolucionDto::setIdDevolucion)
            .addMapping(Devolucion::getFechaDevolucion, DevolucionDto::setFechaDevolucion)
            .addMapping(Devolucion::getEstadoPrenda, DevolucionDto::setEstadoPrenda)
            .addMapping(Devolucion::getObservacion, DevolucionDto::setObservacion)
            .addMappings(mapping -> {
                    mapping.map(src -> src.getAlquiler() == null ? null : src.getAlquiler().getIdAlquiler(), DevolucionDto::setAlquilerId);
                    mapping.map(src -> src.getEmpleado() == null ? null : src.getEmpleado().getIdEmpleado(), DevolucionDto::setEmpleadoId);
                });
        mapper.createTypeMap(DevolucionDto.class, Devolucion.class)
            .addMapping(DevolucionDto::getIdDevolucion, Devolucion::setIdDevolucion)
            .addMapping(DevolucionDto::getFechaDevolucion, Devolucion::setFechaDevolucion)
            .addMapping(DevolucionDto::getEstadoPrenda, Devolucion::setEstadoPrenda)
            .addMapping(DevolucionDto::getObservacion, Devolucion::setObservacion)
            .addMappings(mapping -> {
                    mapping.using(reference(Alquiler::new, Alquiler::setIdAlquiler)).map(DevolucionDto::getAlquilerId, Devolucion::setAlquiler);
                    mapping.using(reference(Empleado::new, Empleado::setIdEmpleado)).map(DevolucionDto::getEmpleadoId, Devolucion::setEmpleado);
                });
        return mapper;
    }

    @Bean
    public ModelMapper empleadoMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.createTypeMap(Empleado.class, EmpleadoDto.class)
                .addMapping(Empleado::getIdEmpleado, EmpleadoDto::setIdEmpleado)
                .addMapping(Empleado::getNombres, EmpleadoDto::setNombres)
                .addMapping(Empleado::getApellidos, EmpleadoDto::setApellidos)
                .addMapping(Empleado::getDni, EmpleadoDto::setDni)
                .addMapping(Empleado::getTelefono, EmpleadoDto::setTelefono)
                .addMapping(Empleado::getCargo, EmpleadoDto::setCargo)
                .addMapping(Empleado::getEstado, EmpleadoDto::setEstado);
        mapper.createTypeMap(EmpleadoDto.class, Empleado.class)
                .addMapping(EmpleadoDto::getIdEmpleado, Empleado::setIdEmpleado)
                .addMapping(EmpleadoDto::getNombres, Empleado::setNombres)
                .addMapping(EmpleadoDto::getApellidos, Empleado::setApellidos)
                .addMapping(EmpleadoDto::getDni, Empleado::setDni)
                .addMapping(EmpleadoDto::getTelefono, Empleado::setTelefono)
                .addMapping(EmpleadoDto::getCargo, Empleado::setCargo)
                .addMapping(EmpleadoDto::getEstado, Empleado::setEstado);
        return mapper;
    }

    @Bean
    public ModelMapper mantenimientoMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.createTypeMap(Mantenimiento.class, MantenimientoDto.class)
            .addMapping(Mantenimiento::getIdMantenimiento, MantenimientoDto::setIdMantenimiento)
            .addMapping(Mantenimiento::getFechaInicio, MantenimientoDto::setFechaInicio)
            .addMapping(Mantenimiento::getFechaFin, MantenimientoDto::setFechaFin)
            .addMapping(Mantenimiento::getTipoMantenimiento, MantenimientoDto::setTipoMantenimiento)
            .addMapping(Mantenimiento::getDescripcion, MantenimientoDto::setDescripcion)
            .addMapping(Mantenimiento::getCosto, MantenimientoDto::setCosto)
            .addMapping(Mantenimiento::getEstado, MantenimientoDto::setEstado)
            .addMappings(mapping -> {
                    mapping.map(src -> src.getPrenda() == null ? null : src.getPrenda().getIdPrenda(), MantenimientoDto::setPrendaId);
                    mapping.map(src -> src.getEmpleado() == null ? null : src.getEmpleado().getIdEmpleado(), MantenimientoDto::setEmpleadoId);
                });
        mapper.createTypeMap(MantenimientoDto.class, Mantenimiento.class)
            .addMapping(MantenimientoDto::getIdMantenimiento, Mantenimiento::setIdMantenimiento)
            .addMapping(MantenimientoDto::getFechaInicio, Mantenimiento::setFechaInicio)
            .addMapping(MantenimientoDto::getFechaFin, Mantenimiento::setFechaFin)
            .addMapping(MantenimientoDto::getTipoMantenimiento, Mantenimiento::setTipoMantenimiento)
            .addMapping(MantenimientoDto::getDescripcion, Mantenimiento::setDescripcion)
            .addMapping(MantenimientoDto::getCosto, Mantenimiento::setCosto)
            .addMapping(MantenimientoDto::getEstado, Mantenimiento::setEstado)
            .addMappings(mapping -> {
                    mapping.using(reference(Prenda::new, Prenda::setIdPrenda)).map(MantenimientoDto::getPrendaId, Mantenimiento::setPrenda);
                    mapping.using(reference(Empleado::new, Empleado::setIdEmpleado)).map(MantenimientoDto::getEmpleadoId, Mantenimiento::setEmpleado);
                });
        return mapper;
    }

    @Bean
    public ModelMapper pagoMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.createTypeMap(Pago.class, PagoDto.class)
            .addMapping(Pago::getIdPago, PagoDto::setIdPago)
            .addMapping(Pago::getFechaPago, PagoDto::setFechaPago)
            .addMapping(Pago::getMonto, PagoDto::setMonto)
            .addMapping(Pago::getMetodoPago, PagoDto::setMetodoPago)
            .addMapping(Pago::getEstado, PagoDto::setEstado)
            .addMappings(mapping -> {
                    mapping.map(src -> src.getAlquiler() == null ? null : src.getAlquiler().getIdAlquiler(), PagoDto::setAlquilerId);
                });
        mapper.createTypeMap(PagoDto.class, Pago.class)
            .addMapping(PagoDto::getIdPago, Pago::setIdPago)
            .addMapping(PagoDto::getFechaPago, Pago::setFechaPago)
            .addMapping(PagoDto::getMonto, Pago::setMonto)
            .addMapping(PagoDto::getMetodoPago, Pago::setMetodoPago)
            .addMapping(PagoDto::getEstado, Pago::setEstado)
            .addMappings(mapping -> {
                    mapping.using(reference(Alquiler::new, Alquiler::setIdAlquiler)).map(PagoDto::getAlquilerId, Pago::setAlquiler);
                });
        return mapper;
    }

    @Bean
    public ModelMapper penalidadMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.createTypeMap(Penalidad.class, PenalidadDto.class)
            .addMapping(Penalidad::getIdPenalidad, PenalidadDto::setIdPenalidad)
            .addMapping(Penalidad::getTipo_penalidad, PenalidadDto::setTipo_penalidad)
            .addMapping(Penalidad::getDescripcion, PenalidadDto::setDescripcion)
            .addMapping(Penalidad::getMonto, PenalidadDto::setMonto)
            .addMapping(Penalidad::getFecha_penalidad, PenalidadDto::setFecha_penalidad)
            .addMapping(Penalidad::getEstado, PenalidadDto::setEstado)
            .addMappings(mapping -> {
                    mapping.map(src -> src.getDevolucion() == null ? null : src.getDevolucion().getIdDevolucion(), PenalidadDto::setDevolucionId);
                });
        mapper.createTypeMap(PenalidadDto.class, Penalidad.class)
            .addMapping(PenalidadDto::getIdPenalidad, Penalidad::setIdPenalidad)
            .addMapping(PenalidadDto::getTipo_penalidad, Penalidad::setTipo_penalidad)
            .addMapping(PenalidadDto::getDescripcion, Penalidad::setDescripcion)
            .addMapping(PenalidadDto::getMonto, Penalidad::setMonto)
            .addMapping(PenalidadDto::getFecha_penalidad, Penalidad::setFecha_penalidad)
            .addMapping(PenalidadDto::getEstado, Penalidad::setEstado)
            .addMappings(mapping -> {
                    mapping.using(reference(Devolucion::new, Devolucion::setIdDevolucion)).map(PenalidadDto::getDevolucionId, Penalidad::setDevolucion);
                });
        return mapper;
    }

    @Bean
    public ModelMapper prendaMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.createTypeMap(Prenda.class, PrendaDto.class)
            .addMapping(Prenda::getIdPrenda, PrendaDto::setIdPrenda)
            .addMapping(Prenda::getCodigoPrenda, PrendaDto::setCodigoPrenda)
            .addMapping(Prenda::getNombre, PrendaDto::setNombre)
            .addMapping(Prenda::getDescripcion, PrendaDto::setDescripcion)
            .addMapping(Prenda::getTalla, PrendaDto::setTalla)
            .addMapping(Prenda::getColor, PrendaDto::setColor)
            .addMapping(Prenda::getPrecioAlquiler, PrendaDto::setPrecioAlquiler)
            .addMapping(Prenda::getEstado, PrendaDto::setEstado)
            .addMappings(mapping -> {
                    mapping.map(src -> src.getCategoria() == null ? null : src.getCategoria().getIdCategoria(), PrendaDto::setCategoriaId);
                });
        mapper.createTypeMap(PrendaDto.class, Prenda.class)
            .addMapping(PrendaDto::getIdPrenda, Prenda::setIdPrenda)
            .addMapping(PrendaDto::getCodigoPrenda, Prenda::setCodigoPrenda)
            .addMapping(PrendaDto::getNombre, Prenda::setNombre)
            .addMapping(PrendaDto::getDescripcion, Prenda::setDescripcion)
            .addMapping(PrendaDto::getTalla, Prenda::setTalla)
            .addMapping(PrendaDto::getColor, Prenda::setColor)
            .addMapping(PrendaDto::getPrecioAlquiler, Prenda::setPrecioAlquiler)
            .addMapping(PrendaDto::getEstado, Prenda::setEstado)
            .addMappings(mapping -> {
                    mapping.using(reference(Categoria::new, Categoria::setIdCategoria)).map(PrendaDto::getCategoriaId, Prenda::setCategoria);
                });
        return mapper;
    }

    @Bean
    public ModelMapper produccionMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.createTypeMap(Produccion.class, ProduccionDto.class)
            .addMapping(Produccion::getIdProduccion, ProduccionDto::setIdProduccion)
            .addMapping(Produccion::getFechaProduccion, ProduccionDto::setFechaProduccion)
            .addMapping(Produccion::getCantidad, ProduccionDto::setCantidad)
            .addMapping(Produccion::getCostoProduccion, ProduccionDto::setCostoProduccion)
            .addMapping(Produccion::getDescripcion, ProduccionDto::setDescripcion)
            .addMappings(mapping -> {
                    mapping.map(src -> src.getPrenda() == null ? null : src.getPrenda().getIdPrenda(), ProduccionDto::setPrendaId);
                    mapping.map(src -> src.getEmpleado() == null ? null : src.getEmpleado().getIdEmpleado(), ProduccionDto::setEmpleadoId);
                });
        mapper.createTypeMap(ProduccionDto.class, Produccion.class)
            .addMapping(ProduccionDto::getIdProduccion, Produccion::setIdProduccion)
            .addMapping(ProduccionDto::getFechaProduccion, Produccion::setFechaProduccion)
            .addMapping(ProduccionDto::getCantidad, Produccion::setCantidad)
            .addMapping(ProduccionDto::getCostoProduccion, Produccion::setCostoProduccion)
            .addMapping(ProduccionDto::getDescripcion, Produccion::setDescripcion)
            .addMappings(mapping -> {
                    mapping.using(reference(Prenda::new, Prenda::setIdPrenda)).map(ProduccionDto::getPrendaId, Produccion::setPrenda);
                    mapping.using(reference(Empleado::new, Empleado::setIdEmpleado)).map(ProduccionDto::getEmpleadoId, Produccion::setEmpleado);
                });
        return mapper;
    }

    @Bean
    public ModelMapper rolMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.createTypeMap(Rol.class, RolDto.class)
                .addMapping(Rol::getIdRol, RolDto::setIdRol)
                .addMapping(Rol::getName, RolDto::setName)
                .addMapping(Rol::getDescription, RolDto::setDescription);
        mapper.createTypeMap(RolDto.class, Rol.class)
                .addMapping(RolDto::getIdRol, Rol::setIdRol)
                .addMapping(RolDto::getName, Rol::setName)
                .addMapping(RolDto::getDescription, Rol::setDescription);
        return mapper;
    }

    @Bean
    public ModelMapper usuarioMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.createTypeMap(Usuario.class, UsuarioDto.class)
            .addMapping(Usuario::getIdUser, UsuarioDto::setIdUser)
            .addMapping(Usuario::getUsername, UsuarioDto::setUsername)
            .addMapping(Usuario::isEnabled, UsuarioDto::setEnabled)
            .addMappings(mapping -> {
                    mapping.map(src -> src.getRol() == null ? null : src.getRol().getIdRol(), UsuarioDto::setRolId);
                });
        mapper.createTypeMap(UsuarioDto.class, Usuario.class)
            .addMapping(UsuarioDto::getIdUser, Usuario::setIdUser)
            .addMapping(UsuarioDto::getUsername, Usuario::setUsername)
            .addMapping(UsuarioDto::getPassword, Usuario::setPassword)
            .addMapping(UsuarioDto::isEnabled, Usuario::setEnabled)
            .addMappings(mapping -> {
                    mapping.using(reference(Rol::new, Rol::setIdRol)).map(UsuarioDto::getRolId, Usuario::setRol);
                });
        return mapper;
    }

    private <E, I> Converter<I, E> reference(java.util.function.Supplier<E> constructor,
                                              java.util.function.BiConsumer<E, I> setter) {
        return context -> {
            I id = context.getSource();
            if (id == null) {
                return null;
            }
            E entity = constructor.get();
            setter.accept(entity, id);
            return entity;
        };
    }
}
