# 🛒 Bazar API - Spring Boot 



API REST para la gestión integral de un bazar, desarrollada para administrar productos, clientes y ventas con un enfoque en la integridad de datos y el control de inventario.



## Características Técnicas Destacadas



***Gestión de Stock Transaccional:*** El sistema valida la disponibilidad de productos antes de procesar cada venta. Se implementó lógica de "reintegro de stock" para ediciones de ventas, garantizando la consistencia del inventario.

***Arquitectura DTO:*** Uso de **Data Transfer Objects** para reportes específicos (Venta Mayor, Reporte Diario), protegiendo la estructura de la base de datos y optimizando las respuestas.

***Persistencia Avanzada:*** Uso de `@OneToMany` con `orphanRemoval = true` para una gestión limpia de registros de detalles de venta.

***Programación Funcional:*** Implementación de **Java Streams** y **Lambdas** para cálculos de montos y filtrado de datos.



## 🛠 Tecnologías Utilizadas



 * *Java 17*

* *Spring Boot 3*

* *Spring Data JPA*

* *MySQL*

* *Maven*



## ⚙ Endpoints Principales



 `POST /bazar/ventas/crear`: Procesamiento de ventas con validación de stock.

`PUT /bazar/ventas/edit`: Edición segura de ventas y actualización de inventario.

`GET /bazar/ventas/dia/{fecha}`: Reporte de ingresos y cantidad de ventas diarias.

`GET /bazar/ventas/mayor\_venta`: Consulta de la venta de mayor valor con datos del cliente.



##   🞋 Sobre el Proyecto

Este desarrollo fue realizado como parte de mi formación en el curso de **TodoCode Academy**, aplicando estándares de calidad de software y lógica de negocio para sistemas de gestión.

