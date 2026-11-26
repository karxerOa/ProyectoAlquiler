# ProyectoAlquiler: correcciones y pruebas

Rama: `feature/fix-proyecto-completo`, creada desde develop e incorporando los cambios locales existentes. No se realizó commit ni push.

## Ejecucion local desde el IDE

Se carga automaticamente `config/local-secrets.properties` desde el directorio del proyecto. Contiene JWT_SECRET y DB_PASSWORD y esta excluido de Git. Usar ProyectoSolucionesWeb como directorio de trabajo de la configuracion Run. No copiar este archivo al repositorio ni al JAR. En otra computadora, crear el archivo con sus propias claves o definir las variables de entorno descritas abajo. Las variables de entorno tienen prioridad.

## Arranque

Java 26 y Maven Wrapper del proyecto. PostgreSQL debe tener la base creada.
En PowerShell, dentro de ProyectoSolucionesWeb:

```powershell
$env:DB_URL='jdbc:postgresql://localhost:5432/ProyectoAlquiler'
$env:DB_USERNAME='postgres'
$env:DB_PASSWORD='<tu clave PostgreSQL>'
$env:JWT_SECRET='<secreto aleatorio privado de al menos 32 bytes>'
# Solo para crear el primer administrador y los roles si aún no existen:
$env:BOOTSTRAP_ADMIN_USERNAME='admin'
$env:BOOTSTRAP_ADMIN_PASSWORD='<clave nueva de 8 caracteres como mínimo y 72 bytes como máximo>'
./mvnw.cmd spring-boot:run
```

Tras el primer arranque, quitar BOOTSTRAP_ADMIN_PASSWORD. El inicializador no cambia claves de usuarios existentes. Las contraseñas antiguas en texto plano no se convierten automáticamente: deben restablecerse mediante un administrador. JWT_SECRET es obligatorio y se valida al arrancar; no hay secreto compartido predeterminado.

## Contrato y permisos

- Login: POST `/login`, JSON `{"username":"admin","password":"tu clave"}`. Respuesta: `{"access_token":"..."}`.
- El administrador crea cuentas de personal mediante POST `/v1/usuarios`. Las fichas de clientes se gestionan en `/v1/clientes`.
- Cabecera protegida: `Authorization: Bearer <access_token>`.
- ADMIN gestiona todos los módulos e imágenes. EMPLEADO gestiona los módulos de negocio. Solo estos dos roles pueden iniciar sesión, con o sin el prefijo ROLE_.
- Swagger: `/swagger-ui/index.html`; especificación `/v3/api-docs`.
- GET 200; POST 201 con DTO; PUT 200 con DTO; DELETE 204 sin cuerpo. Recurso inexistente 404, credenciales/token 401, permisos 403, JSON incorrecto 400, restricciones de datos 409.
- PUT reemplaza los campos del DTO; enviar el cuerpo completo. En Usuario, password ausente/vacío conserva la clave. Ninguna respuesta incluye password/hash.
- Los IDs de POST se descartan. PUT utiliza el ID de la URL, incluidos pagos y penalidades.
- CORS centralizado en Spring Security, origen predeterminado http://localhost:4200; configurar CORS_ORIGINS para otros clientes. Postman no aplica CORS.
- JWT es stateless: cerrar sesión en Postman consiste en borrar el token; no existe revocación inmediata de tokens individuales. Deshabilitar el usuario impide usar sus tokens.

## Modelo y compatibilidad de datos

Devolucion.penalidades ahora usa mappedBy=devolucion. Se eliminaron las restricciones únicas de fechaDevolucion/estadoPrenda y tipo_penalidad/descripcion. Penalidad.monto es BigDecimal; fechas de devolución y penalidad son LocalDateTime (fecha y hora); DNI/teléfono son String para conservar ceros y prefijos. Los nombres JSON tipo_penalidad y fecha_penalidad se conservan.

La base real ProyectoAlquiler fue respaldada y migrada el 11/11/2026. Se renombro mantenimiento.id_empeado a id_empleado preservando la clave foranea. Se retiro devolucion_penalidades despues de verificar que estaba vacia; penalidad.id_devolucion conserva la relacion. Se retiro la unicidad del telefono de clientes y empleados, permitiendo numeros compartidos. DNI y correo mantienen sus restricciones unicas.

Se conservaron las horas existentes; enviar fechaDevolucion y fecha_penalidad como `2026-11-13T18:00:00`. El script adjunto corresponde a la migracion ya aplicada: no ejecutarlo otra vez sobre esta base.

DDL_AUTO=update crea un esquema nuevo, pero no sustituye las migraciones. Para comprobar la base migrada se usa validate, sin escrituras de prueba en ella. Las pruebas CRUD siguen usando la base temporal independiente. Los ceros de DNI perdidos cuando era entero no se pueden reconstruir automaticamente.

## Postman

Importar ProyectoAlquiler.postman_collection.json. Configurar baseUrl, username y password; ejecutar Login ADMIN. La colección captura token e IDs y contiene cuerpos de los 13 DTOs, con relaciones encadenadas. Ejecutar los módulos en su orden. La carpeta final elimina exclusivamente las filas cuyos IDs capturó la colección. Usar una base de pruebas para ejecutar la colección completa.

Ejemplo: POST http://localhost:9494/v1/categorias, Authorization: Bearer <token>, Content-Type: application/json:

```json
{"nombre":"Trajes","descripcion":"Vestimenta formal","estado":"ACTIVO"}
```

## Verificación automatizada

Crear una base vacía llamada proyecto_alquiler_codex_test. La prueba levanta HTTP real en puerto aleatorio y PostgreSQL real; crea y elimina sus tablas con create-drop. Nunca apuntar TEST_DB_URL a una base con datos que se quieran conservar.

```powershell
$env:DB_PASSWORD='<clave PostgreSQL>'
./mvnw.cmd clean compile test
```

Comprueba BCrypt, login, registro público cerrado y rechazo de cuentas con roles antiguos, token malformado/expirado/firma incorrecta, usuario deshabilitado, 401/403/404/400, Swagger, preflight CORS y los 13 CRUD con IDs estables al actualizar y eliminación 204.

## Archivos principales

security: imports y JPQL corregidos; login simplificado; filtro controlado; AuthenticationManager/BCrypt; política de roles y sesiones sin estado; configuración externa del secreto. UserService mantiene la herencia genérica y comparte la gestión de claves con UsuarioService.
service: AlquilerService hereda GenericService; búsqueda, actualización y eliminación verifican existencia.
repository: retirada consulta findOneByUsername de Cliente, que no tiene ese atributo; repositorios específicos heredan IGenericRepository.
controller/dto/config: contratos DTO preservados, password solo de entrada y sin mapeo de salida; ID de Pago y correcciones de actualización/creación.
exception/util: errores HTTP controlados sin mensajes SQL internos, una sola configuración CORS.

No se implementaron nuevas reglas de inventario, cálculo automático de totales ni disponibilidad por fechas: este trabajo corrige la arquitectura, persistencia, autenticación y contratos existentes.
