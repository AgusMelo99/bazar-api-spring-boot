# 🛒 Bazar API — Spring Boot

API REST para gestionar un bazar: productos, clientes y ventas, con control transaccional del inventario.

Proyecto desarrollado durante el curso *Desarrollo de APIs en Java con Spring Boot* (TodoCode Academy, 2025).

## Qué resuelve

- **Stock consistente:** antes de procesar una venta se valida que haya stock; al editar una venta se reintegra el stock anterior y se descuenta el nuevo.
- **Reportes:** ingresos y cantidad de ventas por día, y la venta de mayor monto con los datos del cliente.

## Decisiones de diseño

- **Arquitectura en capas con DTOs:** los reportes devuelven DTOs específicos (venta de mayor monto, reporte diario) para no exponer las entidades ni la estructura de la base de datos.
- **Persistencia:** relación venta–detalle con `@OneToMany` y `orphanRemoval = true`, para que los detalles eliminados de una venta se borren de la base automáticamente.
- **Java Streams y lambdas** para calcular montos y filtrar datos.

## Tecnologías

Java 17 · Spring Boot 3 · Spring Data JPA / Hibernate · MySQL 8 · Maven

## Endpoints principales

| Método | Ruta | Descripción |
|---|---|---|
| `POST` | `/bazar/ventas/crear` | Crea una venta validando el stock |
| `PUT` | `/bazar/ventas/edit` | Edita una venta y actualiza el inventario |
| `GET` | `/bazar/ventas/dia/{fecha}` | Ingresos y cantidad de ventas de un día |
| `GET` | `/bazar/ventas/mayor_venta` | Venta de mayor valor con datos del cliente |

<!-- TODO: agregar el resto de los endpoints (productos y clientes) y un ejemplo de body JSON para crear una venta -->

## Cómo ejecutarlo

**Requisitos:** JDK 17 y MySQL 8 corriendo en `localhost:3306`.

1. Crear la base de datos:
   ```sql
   CREATE DATABASE bazar2;
   ```
2. Revisar usuario y contraseña de MySQL en `src/main/resources/application.properties`.
3. Levantar la aplicación:
   ```bash
   ./mvnw spring-boot:run
   ```

Las tablas se crean solas al iniciar (`spring.jpa.hibernate.ddl-auto=update`). Por defecto la API queda en `http://localhost:8080`.

## Próximos pasos

- Tests unitarios y de integración con JUnit y Mockito.
- Documentación de la API con OpenAPI / Swagger.
- Credenciales de la base de datos por variables de entorno.
- Dockerfile y `docker-compose` con MySQL.
