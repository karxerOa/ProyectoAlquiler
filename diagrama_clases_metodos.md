# Diagrama de Clases y Métodos - Sistema de Alquiler de Prendas

Diagrama generado a partir del análisis del proyecto real (Spring Boot, arquitectura en capas).

```mermaid
classDiagram
    direction LR

    %% ==================== CAPA MODELO (Entidades JPA) ====================
    namespace model {
        class Categoria {
            <<Entity>>
            -Integer idCategoria
            -String nombre
            -String descripcion
            -String estado
            +List~Prenda~ prendas
        }

        class Prenda {
            <<Entity>>
            -Integer idPrenda
            -String codigoPrenda
            -String nombre
            -String descripcion
            -String talla
            -String color
            -BigDecimal precioAlquiler
            -String estado
            +Categoria categoria
            +List~Produccion~ producciones
            +List~Mantenimiento~ mantenimientos
            +List~DetalleAlquiler~ detalles
        }

        class Produccion {
            <<Entity>>
            -Integer idProduccion
            -LocalDate fechaProduccion
            -int cantidad
            -BigDecimal costoProduccion
            -String descripcion
            +Prenda prenda
            +Empleado empleado
        }

        class Mantenimiento {
            <<Entity>>
            -Integer idMantenimiento
            -LocalDate fechaInicio
            -LocalDate fechaFin
            -String tipoMantenimiento
            -String descripcion
            -BigDecimal costo
            -String estado
            +Prenda prenda
            +Empleado empleado
        }

        class Empleado {
            <<Entity>>
            -Integer idEmpleado
            -String nombres
            -String apellidos
            -int dni
            -int telefono
            -String cargo
            -String estado
            +List~Produccion~ producciones
            +List~Mantenimiento~ mantenimientos
            +List~Alquiler~ alquileres
            +List~Devolucion~ devoluciones
        }

        class Cliente {
            <<Entity>>
            -Integer idCliente
            -String nombre
            -String apellidos
            -int dni
            -int telefono
            -String direccion
            -String correo
            +List~Alquiler~ alquileres
        }

        class Alquiler {
            <<Entity>>
            -Integer idAlquiler
            -LocalDate fechaAlquiler
            -LocalDate fechaInicio
            -LocalDate fechaFin
            -String estado
            -BigDecimal subtotal
            -BigDecimal total
            +Cliente cliente
            +Empleado trabajador
            +List~DetalleAlquiler~ detalles
            +List~Pago~ pagos
            +List~Devolucion~ devoluciones
        }

        class DetalleAlquiler {
            <<Entity>>
            -Integer idDetalle
            -int cantidad
            -BigDecimal precioUnitario
            -BigDecimal subtotal
            +Alquiler alquiler
            +Prenda prenda
        }

        class Pago {
            <<Entity>>
            -Integer idPago
            -LocalDate fechaPago
            -BigDecimal monto
            -String metodoPago
            -String estado
            +Alquiler alquiler
        }

        class Devolucion {
            <<Entity>>
            -Integer idDevolucion
            -Date fechaDevolucion
            -String estadoprenda
            -String observacion
            +Alquiler alquiler
            +Empleado empleado
            +List~Penalidad~ penalidades
        }

        class Penalidad {
            <<Entity>>
            -Integer idPenalidad
            -String tipo_penalidad
            -String descripcion
            -Integer monto
            -Date fecha_penalidad
            -Boolean estado
            +Devolucion devolucion
        }

        class Usuario {
            <<Entity>>
            -Integer idUser
            -String username
            -String password
            -boolean enabled
            +Rol rol
        }

        class Rol {
            <<Entity>>
            -Integer idRol
            -String name
            -String description
            +List~Usuario~ usuarios
        }
    }

    %% ==================== CAPA DTO ====================
    namespace dto {
        class AlquilerDto {
            -Integer idAlquiler
            -LocalDate fechaAlquiler
            -LocalDate fechaInicio
            -LocalDate fechaFin
            -String estado
            -BigDecimal subtotal
            -BigDecimal total
            -Integer empleadoId
            -Integer clienteId
            -String nombreEmpleado
            -String nombreCliente
            -String apellidoCliente
        }

        class CategoriaDto {
            -Integer idCategoria
            -String nombre
            -String descripcion
            -String estado
        }

        class ClienteDto {
            -Integer idCliente
            -String nombre
            -String apellidos
            -Integer dni
            -Integer telefono
            -String direccion
            -String correo
        }

        class DevolucionDto {
            -Integer idDevolucion
            -Date fechaDevolucion
            -String estadoPrenda
            -String observacion
            -Integer alquilerId
            -Integer empleadoId
        }

        class EmpleadoDto {
            -Integer idEmpleado
            -String nombres
            -String apellidos
            -Integer dni
            -Integer telefono
            -String cargo
            -String estado
        }

        class MantenimientoDto {
            -Integer idMantenimiento
            -LocalDate fechaInicio
            -LocalDate fechaFin
            -String tipoMantenimiento
            -String descripcion
            -BigDecimal costo
            -String estado
            -Integer prendaId
            -Integer empleadoId
        }

        class PagoDto {
            -LocalDate fechaPago
            -BigDecimal monto
            -String metodoPago
            -String estado
            -Integer alquilerId
        }

        class PenalidadDto {
            -Integer idPenalidad
            -String tipo_penalidad
            -String descripcion
            -Integer monto
            -Date fecha_penalidad
            -Boolean estado
            -Integer devolucionId
        }

        class PrendaDto {
            -Integer idPrenda
            -String nombre
            -String descripcion
            -String talla
            -String color
            -BigDecimal precioAlquiler
            -String estado
            -String categoriaNombre
            -Integer categoriaId
        }

        class ProduccionDto {
            -Integer idProduccion
            -LocalDate fechaProduccion
            -int cantidad
            -BigDecimal costoProduccion
            -String descripcion
            -Integer idPrenda
            -Integer idEmpleado
        }

        class RolDto {
            -Integer idRol
            -String name
            -String description
        }

        class UsuarioDto {
            -Integer idUser
            -String username
            -String password
            -boolean enabled
            -Integer rolId
            -String name
        }
    }

    %% ==================== CAPA REPOSITORIO ====================
    namespace repository {
        class IGenericRepository {
            <<interface, T, ID>>
        }

        class IAlquilerRepository { <<interface>> }
        class ICategoriaRepository { <<interface>> }
        class IClienteRepository { <<interface>> }
        class IDetalleAlquilerRepository { <<interface>> }
        class IDevolucionRepository { <<interface>> }
        class IEmpleadoRepository { <<interface>> }
        class IMantenimientoRepository { <<interface>> }
        class IPagoRepository { <<interface>> }
        class IPenalidadRepository { <<interface>> }
        class IPrendaRepository { <<interface>> }
        class IProduccionRepository { <<interface>> }
        class IRolRepository { <<interface>> }
        class IUsuarioRepository { <<interface>> }
    }

    %% ==================== CAPA SERVICIO (interfaces) ====================
    namespace service_interfaces {
        class IGenericService {
            <<interface, T, ID>>
            +T save(T t) throws Exception
            +T update(ID id, T t) throws Exception
            +List~T~ findAll() throws Exception
            +T findById(ID id) throws Exception
            +void delete(ID id) throws Exception
        }

        class IAlquilerService {
            <<interface>>
            +Alquiler save(Alquiler alquiler) throws Exception
            +Alquiler update(Integer id, Alquiler alquiler) throws Exception
            +List~Alquiler~ findAll() throws Exception
            +Alquiler findById(Integer id) throws Exception
            +void delete(Integer id) throws Exception
        }

        class ICategoriaService { <<interface>> }
        class IClienteService { <<interface>> }
        class IDetalleAlquilerService {
            <<interface>>
            +DetalleAlquiler save(DetalleAlquiler d) throws Exception
            +DetalleAlquiler update(Integer id, DetalleAlquiler d) throws Exception
            +List~DetalleAlquiler~ findAll() throws Exception
            +DetalleAlquiler findById(Integer id) throws Exception
            +void delete(Integer id) throws Exception
        }
        class IDevolucionService { <<interface>> }
        class IEmpleadoService { <<interface>> }
        class IMantenimientoService { <<interface>> }
        class IPagoService { <<interface>> }
        class IPenalidadService { <<interface>> }
        class IPrendaService { <<interface>> }
        class IProduccionService { <<interface>> }
        class IRolService { <<interface>> }
        class IUsuarioService { <<interface>> }
    }

    %% ==================== CAPA SERVICIO (implementaciones) ====================
    namespace service_impl {
        class GenericService {
            <<abstract, T, ID>>
            #IGenericRepository~T, ID~ getRepo()
            +T save(T t) throws Exception
            +T update(ID id, T t) throws Exception
            +List~T~ findAll() throws Exception
            +T findById(ID id) throws Exception
            +void delete(ID id) throws Exception
        }

        class AlquilerService {
            -IAlquilerRepository repo
            +Alquiler save(Alquiler a) throws Exception
            +Alquiler update(Integer id, Alquiler a) throws Exception
            +List~Alquiler~ findAll() throws Exception
            +Alquiler findById(Integer id) throws Exception
            +void delete(Integer id) throws Exception
        }

        class DetalleAlquilerService {
            -IDetalleAlquilerRepository repo
            +DetalleAlquiler save(DetalleAlquiler d) throws Exception
            +DetalleAlquiler update(Integer id, DetalleAlquiler d) throws Exception
            +List~DetalleAlquiler~ findAll() throws Exception
            +DetalleAlquiler findById(Integer id) throws Exception
            +void delete(Integer id) throws Exception
        }

        class CategoriaService {
            -ICategoriaRepository repo
            #IGenericRepository~Categoria, Integer~ getRepo()
        }
        class ClienteService {
            -IClienteRepository repo
            #IGenericRepository~Cliente, Integer~ getRepo()
        }
        class DevolucionService {
            -IDevolucionRepository repo
            #IGenericRepository~Devolucion, Integer~ getRepo()
        }
        class EmpleadoService {
            -IEmpleadoRepository repo
            #IGenericRepository~Empleado, Integer~ getRepo()
        }
        class MantenimientoService {
            -IMantenimientoRepository repo
            #IGenericRepository~Mantenimiento, Integer~ getRepo()
        }
        class PagoService {
            -IPagoRepository repo
            #IGenericRepository~Pago, Integer~ getRepo()
        }
        class PenalidadService {
            -IPenalidadRepository repo
            #IGenericRepository~Penalidad, Integer~ getRepo()
        }
        class PrendaService {
            -IPrendaRepository repo
            #IGenericRepository~Prenda, Integer~ getRepo()
        }
        class ProduccionService {
            -IProduccionRepository repo
            #IGenericRepository~Produccion, Integer~ getRepo()
        }
        class RolService {
            -IRolRepository repo
            #IGenericRepository~Rol, Integer~ getRepo()
        }
        class UsuarioService {
            -IUsuarioRepository repo
            #IGenericRepository~Usuario, Integer~ getRepo()
        }
    }

    %% ==================== CAPA CONTROLADOR (REST) ====================
    namespace controller {
        class AlquilerController {
            <<RestController, /v1/alquileres>>
            -IAlquilerService service
            -ModelMapper alquilerMapper
            +ResponseEntity~List~AlquilerDto~~ findAll()
            +ResponseEntity~AlquilerDto~ findById(Integer id)
            +ResponseEntity~AlquilerDto~ save(AlquilerDto dto)
            +ResponseEntity~AlquilerDto~ update(Integer id, AlquilerDto dto)
            +ResponseEntity~Void~ deleteById(Integer id)
        }

        class CategoriaController {
            <<RestController, /v1/categorias>>
            -ICategoriaService service
            -ModelMapper categoriaMapper
            +ResponseEntity~List~CategoriaDto~~ findAll()
            +ResponseEntity~CategoriaDto~ findById(Integer id)
            +ResponseEntity~CategoriaDto~ save(CategoriaDto dto)
            +ResponseEntity~CategoriaDto~ update(Integer id, CategoriaDto dto)
            +ResponseEntity~Void~ deleteById(Integer id)
        }

        class ClienteController {
            <<RestController, /v1/clientes>>
            -IClienteService service
            -ModelMapper clienteMapper
            +ResponseEntity~List~ClienteDto~~ findAll()
            +ResponseEntity~ClienteDto~ findById(Integer id)
            +ResponseEntity~ClienteDto~ save(ClienteDto dto)
            +ResponseEntity~ClienteDto~ update(Integer id, ClienteDto dto)
            +ResponseEntity~Void~ deleteById(Integer id)
        }

        class DevolucionController {
            <<RestController, /v1/devoluciones>>
            -IDevolucionService service
            -ModelMapper devolucionMapper
            +ResponseEntity~List~DevolucionDto~~ findAll()
            +ResponseEntity~DevolucionDto~ findById(Integer id)
            +ResponseEntity~DevolucionDto~ save(DevolucionDto dto)
            +ResponseEntity~DevolucionDto~ update(Integer id, DevolucionDto dto)
            +ResponseEntity~Void~ deleteById(Integer id)
        }

        class EmpleadoController {
            <<RestController, /v1/empleados>>
            -IEmpleadoService service
            -ModelMapper empleadoMapper
            +ResponseEntity~List~EmpleadoDto~~ findAll()
            +ResponseEntity~EmpleadoDto~ findById(Integer id)
            +ResponseEntity~EmpleadoDto~ save(EmpleadoDto dto)
            +ResponseEntity~EmpleadoDto~ update(Integer id, EmpleadoDto dto)
            +ResponseEntity~Void~ deleteById(Integer id)
        }

        class MantenimientoController {
            <<RestController, /v1/mantenimientos>>
            -IMantenimientoService service
            -ModelMapper mantenimientoMapper
            +ResponseEntity~List~MantenimientoDto~~ findAll()
            +ResponseEntity~MantenimientoDto~ findById(Integer id)
            +ResponseEntity~MantenimientoDto~ save(MantenimientoDto dto)
            +ResponseEntity~MantenimientoDto~ update(Integer id, MantenimientoDto dto)
            +ResponseEntity~Void~ deleteById(Integer id)
        }

        class PagoController {
            <<RestController, /v1/pagos>>
            -IPagoService service
            -ModelMapper pagoMapper
            +ResponseEntity~List~PagoDto~~ findAll()
            +ResponseEntity~PagoDto~ findById(Integer id)
            +ResponseEntity~PagoDto~ save(PagoDto dto)
            +ResponseEntity~PagoDto~ update(Integer id, PagoDto dto)
            +ResponseEntity~Void~ deleteById(Integer id)
        }

        class PenalidadController {
            <<RestController, /v1/penalidades>>
            -IPenalidadService service
            -ModelMapper penalidadMapper
            +ResponseEntity~List~PenalidadDto~~ findAll()
            +ResponseEntity~PenalidadDto~ findById(Integer id)
            +ResponseEntity~PenalidadDto~ save(PenalidadDto dto)
            +ResponseEntity~PenalidadDto~ update(Integer id, PenalidadDto dto)
            +ResponseEntity~Void~ deleteById(Integer id)
        }

        class PrendaController {
            <<RestController, /v1/prendas>>
            -IPrendaService service
            -ModelMapper prendaMapper
            +ResponseEntity~List~PrendaDto~~ findAll()
            +ResponseEntity~PrendaDto~ findById(Integer id)
            +ResponseEntity~PrendaDto~ save(PrendaDto dto)
            +ResponseEntity~PrendaDto~ update(Integer id, PrendaDto dto)
            +ResponseEntity~Void~ deleteById(Integer id)
        }

        class ProduccionController {
            <<RestController, /v1/producciones>>
            -IProduccionService service
            -ModelMapper produccionMapper
            +ResponseEntity~List~ProduccionDto~~ findAll()
            +ResponseEntity~ProduccionDto~ findById(Integer id)
            +ResponseEntity~ProduccionDto~ save(ProduccionDto dto)
            +ResponseEntity~ProduccionDto~ update(Integer id, ProduccionDto dto)
            +ResponseEntity~Void~ deleteById(Integer id)
        }

        class RolController {
            <<RestController, /v1/roles>>
            -IRolService service
            -ModelMapper rolMapper
            +ResponseEntity~List~RolDto~~ findAll()
            +ResponseEntity~RolDto~ findById(Integer id)
            +ResponseEntity~RolDto~ save(RolDto dto)
            +ResponseEntity~RolDto~ update(Integer id, RolDto dto)
            +ResponseEntity~Void~ deleteById(Integer id)
        }

        class UsuarioController {
            <<RestController, /v1/usuarios>>
            -IUsuarioService service
            -ModelMapper usuarioMapper
            +ResponseEntity~List~UsuarioDto~~ findAll()
            +ResponseEntity~UsuarioDto~ findById(Integer id)
            +ResponseEntity~UsuarioDto~ save(UsuarioDto dto)
            +ResponseEntity~UsuarioDto~ update(Integer id, UsuarioDto dto)
            +ResponseEntity~Void~ deleteById(Integer id)
        }
    }

    %% ==================== CAPA CONFIG ====================
    namespace config {
        class MapperConfig {
            <<Configuration>>
            +ModelMapper defaultMapper()
            +ModelMapper alquilerMapper()
            +ModelMapper categoriaMapper()
            +ModelMapper clienteMapper()
            +ModelMapper devolucionMapper()
            +ModelMapper empleadoMapper()
            +ModelMapper mantenimientoMapper()
            +ModelMapper pagoMapper()
            +ModelMapper penalidadMapper()
            +ModelMapper prendaMapper()
            +ModelMapper produccionMapper()
            +ModelMapper rolMapper()
            +ModelMapper usuarioMapper()
        }
    }

    %% ==================== RELACIONES DE LAS ENTIDADES ====================
    Categoria "1" --> "0..*" Prenda : clasifica
    Prenda "1" --> "0..*" Produccion : se_produce
    Prenda "1" --> "0..*" Mantenimiento : recibe
    Prenda "1" --> "0..*" DetalleAlquiler : incluida_en
    Empleado "1" --> "0..*" Produccion : registra
    Empleado "1" --> "0..*" Mantenimiento : realiza
    Empleado "1" --> "0..*" Alquiler : atiende
    Empleado "1" --> "0..*" Devolucion : gestiona
    Cliente "1" --> "0..*" Alquiler : realiza
    Alquiler "1" --> "1..*" DetalleAlquiler : contiene
    Alquiler "1" --> "0..*" Pago : genera
    Alquiler "1" --> "0..*" Devolucion : origina
    Devolucion "1" --> "0..*" Penalidad : puede_generar
    Usuario "*" --> "1" Rol : pertenece
    Rol "1" --> "0..*" Usuario : agrupa

    %% ==================== RELACIONES DE LA ARQUITECTURA ====================
    IGenericRepository <|-- IAlquilerRepository
    IGenericRepository <|-- ICategoriaRepository
    IGenericRepository <|-- IClienteRepository
    IGenericRepository <|-- IDetalleAlquilerRepository
    IGenericRepository <|-- IDevolucionRepository
    IGenericRepository <|-- IEmpleadoRepository
    IGenericRepository <|-- IMantenimientoRepository
    IGenericRepository <|-- IPagoRepository
    IGenericRepository <|-- IPenalidadRepository
    IGenericRepository <|-- IPrendaRepository
    IGenericRepository <|-- IProduccionRepository
    IGenericRepository <|-- IRolRepository
    IGenericRepository <|-- IUsuarioRepository

    IGenericService <|-- ICategoriaService
    IGenericService <|-- IClienteService
    IGenericService <|-- IDevolucionService
    IGenericService <|-- IEmpleadoService
    IGenericService <|-- IMantenimientoService
    IGenericService <|-- IPagoService
    IGenericService <|-- IPenalidadService
    IGenericService <|-- IPrendaService
    IGenericService <|-- IProduccionService
    IGenericService <|-- IRolService
    IGenericService <|-- IUsuarioService

    GenericService ..|> IGenericService : implements
    GenericService <|-- CategoriaService
    GenericService <|-- ClienteService
    GenericService <|-- DevolucionService
    GenericService <|-- EmpleadoService
    GenericService <|-- MantenimientoService
    GenericService <|-- PagoService
    GenericService <|-- PenalidadService
    GenericService <|-- PrendaService
    GenericService <|-- ProduccionService
    GenericService <|-- RolService
    GenericService <|-- UsuarioService

    AlquilerService ..|> IAlquilerService : implements
    DetalleAlquilerService ..|> IDetalleAlquilerService : implements
    CategoriaService ..|> ICategoriaService : implements
    ClienteService ..|> IClienteService : implements
    DevolucionService ..|> IDevolucionService : implements
    EmpleadoService ..|> IEmpleadoService : implements
    MantenimientoService ..|> IMantenimientoService : implements
    PagoService ..|> IPagoService : implements
    PenalidadService ..|> IPenalidadService : implements
    PrendaService ..|> IPrendaService : implements
    ProduccionService ..|> IProduccionService : implements
    RolService ..|> IRolService : implements
    UsuarioService ..|> IUsuarioService : implements

    AlquilerController --> IAlquilerService : usa
    CategoriaController --> ICategoriaService : usa
    ClienteController --> IClienteService : usa
    DevolucionController --> IDevolucionService : usa
    EmpleadoController --> IEmpleadoService : usa
    MantenimientoController --> IMantenimientoService : usa
    PagoController --> IPagoService : usa
    PenalidadController --> IPenalidadService : usa
    PrendaController --> IPrendaService : usa
    ProduccionController --> IProduccionService : usa
    RolController --> IRolService : usa
    UsuarioController --> IUsuarioService : usa

    AlquilerController --> AlquilerDto : mapea
    CategoriaController --> CategoriaDto : mapea
    ClienteController --> ClienteDto : mapea
    DevolucionController --> DevolucionDto : mapea
    EmpleadoController --> EmpleadoDto : mapea
    MantenimientoController --> MantenimientoDto : mapea
    PagoController --> PagoDto : mapea
    PenalidadController --> PenalidadDto : mapea
    PrendaController --> PrendaDto : mapea
    ProduccionController --> ProduccionDto : mapea
    RolController --> RolDto : mapea
    UsuarioController --> UsuarioDto : mapea

    MapperConfig ..> alquilerMapper : crea
    MapperConfig ..> categoriaMapper : crea
    MapperConfig ..> clienteMapper : crea
    MapperConfig ..> devolucionMapper : crea
    MapperConfig ..> empleadoMapper : crea
    MapperConfig ..> mantenimientoMapper : crea
    MapperConfig ..> pagoMapper : crea
    MapperConfig ..> penalidadMapper : crea
    MapperConfig ..> prendaMapper : crea
    MapperConfig ..> produccionMapper : crea
    MapperConfig ..> rolMapper : crea
    MapperConfig ..> usuarioMapper : crea

    AlquilerService --> IAlquilerRepository : inyecta
    CategoriaService --> ICategoriaRepository : inyecta
    ClienteService --> IClienteRepository : inyecta
    DetalleAlquilerService --> IDetalleAlquilerRepository : inyecta
    DevolucionService --> IDevolucionRepository : inyecta
    EmpleadoService --> IEmpleadoRepository : inyecta
    MantenimientoService --> IMantenimientoRepository : inyecta
    PagoService --> IPagoRepository : inyecta
    PenalidadService --> IPenalidadRepository : inyecta
    PrendaService --> IPrendaRepository : inyecta
    ProduccionService --> IProduccionRepository : inyecta
    RolService --> IRolRepository : inyecta
    UsuarioService --> IUsuarioRepository : inyecta
```