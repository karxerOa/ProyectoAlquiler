# 👔 ProyectoAlquiler

Sistema de gestión integral para el alquiler de vestimentas: control de prendas, clientes, alquileres, devoluciones, pagos y penalidades en una sola plataforma.

![Java](https://img.shields.io/badge/Java-25-orange?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1-6DB33F?logo=springboot&logoColor=white)
![Angular](https://img.shields.io/badge/Angular-22-DD0031?logo=angular&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15%2B-4169E1?logo=postgresql&logoColor=white)
![JWT](https://img.shields.io/badge/Auth-JWT-000000?logo=jsonwebtokens&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-3.9%2B-C71A36?logo=apachemaven&logoColor=white)

## 📑 Tabla de contenidos

- [Descripción del sistema](#-descripción-del-sistema)
- [Stack tecnológico](#-stack-tecnológico)
- [Arquitectura](#-arquitectura)
- [Módulos del sistema](#-módulos-del-sistema)
- [Requisitos previos](#-requisitos-previos)
- [Instalación y puesta en marcha](#-instalación-y-puesta-en-marcha)
- [Credenciales por defecto](#-credenciales-por-defecto)
- [Endpoints principales](#-endpoints-principales)
- [Colección Postman](#-colección-postman)
- [Estructura de carpetas](#-estructura-de-carpetas)
- [Contribución](#-contribución)
- [Licencia](#-licencia)

## 📖 Descripción del sistema

ProyectoAlquiler es una aplicación web pensada para tiendas y talleres de alquiler de vestimenta —trajes formales, ropa de fiesta, disfraces y prendas de temporada—. Permite registrar el catálogo de prendas por categoría, gestionar clientes y empleados, y formalizar alquileres con su detalle de prendas, fechas y montos.

El sistema cubre el ciclo operativo completo: reserva y entrega, registro de pagos, devoluciones con verificación del estado de la prenda, aplicación de penalidades y control de mantenimientos o producción interna. Cada movimiento queda asociado a un usuario autenticado, lo que permite auditar quién registró cada operación.

La solución está separada en dos repositorios: una API REST en Spring Boot y una aplicación SPA en Angular. La comunicación se realiza mediante JSON sobre HTTP y se protege con tokens JWT, de modo que el frontend y el backend pueden desplegarse y escalarse de forma independiente.

## 🧰 Stack tecnológico

### Backend

| Componente | Tecnología | Versión |
| --- | --- | --- |
| Lenguaje | Java | 25 |
| Framework | Spring Boot | 4.1 |
| Persistencia | Spring Data JPA / Hibernate | Incluido en Spring Boot |
| Base de datos | PostgreSQL (producción) · H2 (desarrollo) | 15+ |
| Seguridad | Spring Security + JJWT | 0.13 |
| Mapeo de objetos | ModelMapper | 3.2.4 |
| Utilidades | Lombok | Incluido en Spring Boot |
| Documentación API | Springdoc OpenAPI (Swagger UI) | 3.2.0 |
| Build | Maven Wrapper | 3.9+ |

### Frontend

| Componente | Tecnología | Versión |
| --- | --- | --- |
| Framework | Angular | 22 |
| Lenguaje | TypeScript | 5.x |
| UI | Angular Material + Angular CDK | 22 |
| Reactividad | RxJS | 7.8 |
| Gestión de sesión | JWT en `localStorage` | — |
| Servidor de desarrollo | Angular CLI (`ng serve`) | 22 |
| Gestor de paquetes | npm | 12+ |

## 🏗️ Arquitectura

```text
┌──────────────────────────────┐        HTTP/JSON + JWT        ┌──────────────────────────────┐
│   Frontend (Angular 22)      │  ──────────────────────────▶  │   Backend (Spring Boot 4.1)  │
│   localhost:4200             │  ◀──────────────────────────  │   localhost:9494             │
└──────────────────────────────┘                               └──────────────┬───────────────┘
                                                                              │
                                    ┌─────────────────────────────────────────┴──────────────┐
                                    │  Controller → Service → Repository (Spring Data JPA)   │
                                    └─────────────────────────────────────────┬──────────────┘
                                                                              │
                                                              ┌───────────────┴───────────────┐
                                                              │  PostgreSQL 15+  /  H2 local  │
                                                              └───────────────────────────────┘
```

El backend sigue una arquitectura en capas:

- **Controller** — expone los recursos REST bajo `/v1/*` y valida el contrato de entrada.
- **Service** — concentra la lógica de negocio; las implementaciones heredan de un servicio genérico reutilizable.
- **Repository** — acceso a datos mediante interfaces de Spring Data JPA.
- **Model / DTO** — entidades persistentes separadas de los objetos de transferencia.
- **Security** — filtro JWT, `AuthenticationManager` con BCrypt y autorización por roles (ADMIN, EMPLEADO, USER).
- **Exception / Util** — manejo centralizado de errores HTTP y utilidades compartidas.

El frontend aplica una organización por funcionalidad: cada módulo agrupa sus componentes, y los servicios de datos consumen la API a través de un `HttpInterceptor` que adjunta el token automáticamente.

## 🧩 Módulos del sistema

| Módulo | Descripción |
| --- | --- |
| Alquiler | Registro y seguimiento de alquileres con cliente, fechas y estado. |
| DetalleAlquiler | Prendas y cantidades asociadas a cada alquiler. |
| Devolución | Registro de devoluciones y verificación del estado de la prenda. |
| Penalidad | Cargos adicionales por daños, retrasos o incumplimientos. |
| Pago | Registro de pagos y saldos por alquiler. |
| Prenda | Catálogo de vestimentas con talla, estado y disponibilidad. |
| Categoría | Clasificación del catálogo (trajes, vestidos, disfraces, etc.). |
| Cliente | Datos de contacto e historial de alquileres del cliente. |
| Empleado | Personal de la tienda y su información laboral. |
| Mantenimiento | Servicios de limpieza y reparación aplicados a las prendas. |
| Producción | Control de prendas en confección o preparación interna. |
| Rol | Definición de roles y permisos del sistema. |
| Usuario | Cuentas de acceso, credenciales y estado de habilitación. |

## ✅ Requisitos previos

| Herramienta | Versión mínima |
| --- | --- |
| Java (JDK) | 25 |
| Node.js | 20+ |
| npm | 12+ |
| PostgreSQL | 15+ |
| Maven | 3.9+ (o usar el wrapper incluido) |

Verificar el entorno:

```bash
java -version
node -v
npm -v
psql --version
```

## 🚀 Instalación y puesta en marcha

### 1. Clonar los repositorios

```bash
git clone <url-del-repositorio-backend> ProyectoAlquiler-Backend
git clone <url-del-repositorio-frontend> ProyectoAlquiler-frontend
```

### 2. Crear la base de datos

```sql
CREATE DATABASE "ProyectoAlquiler";
```

### 3. Configurar el backend

La configuración se toma de variables de entorno, con valores por defecto para desarrollo. Edita `src/main/resources/application.properties` o define las variables:

| Variable | Descripción | Valor por defecto |
| --- | --- | --- |
| `DB_URL` | URL JDBC de conexión | `jdbc:postgresql://localhost:5432/ProyectoAlquiler` |
| `DB_USERNAME` | Usuario de la base de datos | `postgres` |
| `DB_PASSWORD` | Contraseña de la base de datos | *(obligatoria)* |
| `JWT_SECRET` | Secreto de firma del token (mín. 32 bytes) | *(obligatoria)* |
| `CORS_ORIGINS` | Orígenes permitidos | `http://localhost:4200` |
| `SERVER_PORT` | Puerto del servidor | `9494` |
| `DDL_AUTO` | Estrategia de esquema de Hibernate | `update` |

Ejemplo en Linux/macOS:

```bash
export DB_URL="jdbc:postgresql://localhost:5432/ProyectoAlquiler"
export DB_USERNAME="postgres"
export DB_PASSWORD="tu_clave"
export JWT_SECRET="un_secreto_aleatorio_de_al_menos_32_bytes"
```

Ejemplo en PowerShell:

```powershell
$env:DB_URL="jdbc:postgresql://localhost:5432/ProyectoAlquiler"
$env:DB_USERNAME="postgres"
$env:DB_PASSWORD="tu_clave"
$env:JWT_SECRET="un_secreto_aleatorio_de_al_menos_32_bytes"
```

> Para desarrollo sin PostgreSQL, el proyecto incluye H2 en modo archivo. Basta con apuntar `DB_URL` a una URL de H2 para trabajar sin instalar el motor.

Alternativamente, puedes crear `config/local-secrets.properties` (excluido de Git) con estas mismas claves; Spring Boot lo carga automáticamente al arrancar desde la raíz del proyecto.

### 4. Ejecutar el backend

```bash
# Linux / macOS
./mvnw spring-boot:run

# Windows
mvnw.cmd spring-boot:run
```

La API queda disponible en `http://localhost:9494` y la documentación interactiva en `http://localhost:9494/swagger-ui/index.html`.

### 5. Ejecutar el frontend

```bash
cd ProyectoAlquiler-frontend
npm install
ng serve
```

La aplicación se abre en `http://localhost:4200` y consume la API en `http://localhost:9494/v1` (configurado en `src/environments/environment.ts`).

## 🔑 Credenciales por defecto

Al primer arranque, el sistema crea el usuario administrador a partir de las variables `BOOTSTRAP_ADMIN_USERNAME` y `BOOTSTRAP_ADMIN_PASSWORD`:

| Campo | Valor |
| --- | --- |
| Usuario | `admin` |
| Contraseña | `Admin12345` |

> Cambia esta contraseña en entornos reales y elimina `BOOTSTRAP_ADMIN_PASSWORD` después del primer arranque. El inicializador no sobrescribe claves de usuarios existentes.

Roles disponibles:

- **ADMIN** — acceso total a todos los módulos, incluidos usuarios y roles.
- **EMPLEADO** — operaciones de negocio, sin gestión de usuarios ni roles.
- **USER** — solo lectura de los módulos de negocio.

## 🔌 Endpoints principales

Todas las rutas están bajo el prefijo `/v1` y requieren `Authorization: Bearer <token>`, salvo las de autenticación.

| Método | Ruta base | Descripción |
| --- | --- | --- |
| `POST` | `/login` | Autenticación; devuelve el token JWT |
| `POST` | `/registro` | Registro de usuario con rol USER |
| `GET/POST/PUT/DELETE` | `/v1/alquileres` | Gestión de alquileres |
| `GET/POST/PUT/DELETE` | `/v1/detalles-alquiler` | Detalle de prendas por alquiler |
| `GET/POST/PUT/DELETE` | `/v1/devoluciones` | Registro de devoluciones |
| `GET/POST/PUT/DELETE` | `/v1/penalidades` | Penalidades aplicadas |
| `GET/POST/PUT/DELETE` | `/v1/pagos` | Pagos de alquileres |
| `GET/POST/PUT/DELETE` | `/v1/prendas` | Catálogo de prendas |
| `GET/POST/PUT/DELETE` | `/v1/categorias` | Categorías de vestimenta |
| `GET/POST/PUT/DELETE` | `/v1/clientes` | Clientes registrados |
| `GET/POST/PUT/DELETE` | `/v1/empleados` | Personal de la tienda |
| `GET/POST/PUT/DELETE` | `/v1/mantenimientos` | Mantenimientos de prendas |
| `GET/POST/PUT/DELETE` | `/v1/producciones` | Producción interna |
| `GET/POST/PUT/DELETE` | `/v1/roles` | Roles del sistema |
| `GET/POST/PUT/DELETE` | `/v1/usuarios` | Usuarios del sistema |

Cada recurso soporta además `GET /{id}`, `PUT /{id}` y `DELETE /{id}`. Códigos de respuesta: `200` consulta/actualización, `201` creación, `204` eliminación, `400` JSON inválido, `401` credenciales o token inválidos, `403` permisos insuficientes, `404` recurso inexistente, `409` conflicto de datos.

Ejemplo de autenticación:

```bash
curl -X POST http://localhost:9494/login \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"Admin12345"}'
```

Respuesta:

```json
{
  "access_token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

## 📬 Colección Postman

El repositorio incluye la colección `docs/ProyectoAlquiler.postman_collection.json` con todos los módulos, cuerpos de ejemplo para los 13 DTO y captura automática del token e IDs.

1. Importar el archivo en Postman.
2. Configurar las variables `baseUrl`, `username` y `password`.
3. Ejecutar primero la petición **Login ADMIN**.
4. Ejecutar las carpetas en orden: las relaciones entre entidades están encadenadas.

## 📁 Estructura de carpetas

### Backend

```text
ProyectoAlquiler-Backend/
├── config/
│   └── local-secrets.properties      # Credenciales locales (no versionado)
├── docs/
│   ├── Guia-pruebas.md
│   ├── ProyectoAlquiler.postman_collection.json
│   └── migracion-esquema.sql
├── src/main/java/com/prueba/ProyectoAlquiler/
│   ├── config/
│   ├── controller/
│   ├── dto/
│   ├── exception/
│   ├── model/
│   ├── repository/
│   ├── security/
│   ├── service/
│   │   ├── implementations/
│   │   └── interfaz/
│   └── util/
├── src/main/resources/
│   └── application.properties
├── src/test/
├── mvnw
├── mvnw.cmd
└── pom.xml
```

### Frontend

```text
ProyectoAlquiler-frontend/
├── public/
├── src/
│   ├── app/
│   │   ├── model/
│   │   ├── pages/
│   │   │   ├── alquiler/
│   │   │   ├── categoria/
│   │   │   ├── cliente/
│   │   │   ├── detalle-alquiler/
│   │   │   ├── devolucion/
│   │   │   ├── empleado/
│   │   │   ├── layout/
│   │   │   ├── login/
│   │   │   ├── mantenimiento/
│   │   │   ├── pago/
│   │   │   ├── penalidad/
│   │   │   ├── prenda/
│   │   │   ├── produccion/
│   │   │   ├── rol/
│   │   │   └── usuario/
│   │   ├── services/
│   │   ├── shared/
│   │   ├── app.routes.ts
│   │   └── app.config.ts
│   ├── environments/
│   └── main.ts
├── angular.json
├── package.json
└── tsconfig.json
```

## 🤝 Contribución

1. Crea una rama descriptiva desde `develop`:

   ```bash
   git checkout -b feature/nombre-de-la-mejora
   ```

2. Mantén el estilo existente: controladores delgados, lógica en servicios y DTO para la entrada/salida.
3. Verifica que el proyecto compile y que las pruebas pasen antes de abrir el cambio:

   ```bash
   ./mvnw clean test
   ```

4. Documenta los nuevos endpoints y actualiza la colección de Postman si corresponde.
5. Abre un Pull Request describiendo el cambio, su motivación y cómo probarlo.

## 📄 Licencia

Distribuido bajo la licencia **MIT**. Consulta el archivo `LICENSE` para más detalles.

---

Desarrollado para la gestión profesional de alquiler de vestimentas.
