# LogHub

Sistema de gestión y monitoreo de logs desarrollado con Spring Boot.

## Descripción

LogHub es una API REST orientada al registro y administración de logs de aplicaciones. Permite almacenar eventos, clasificarlos por niveles y gestionar aplicaciones mediante una arquitectura backend basada en Spring Boot.

El proyecto implementa buenas prácticas utilizando:

* Arquitectura por capas
* DTOs para requests y responses
* Validaciones
* Manejo global de excepciones
* Seguridad con API Key
* Persistencia con JPA
* PostgreSQL
* Swagger/OpenAPI

---

## Tecnologías utilizadas

* Java 21
* Spring Boot 4
* Spring Web MVC
* Spring Security
* Spring Data JPA
* PostgreSQL
* Lombok
* Swagger / OpenAPI
* Maven
* HTML, CSS y JavaScript

---

## Estructura del proyecto

```bash
src
├── main
│   ├── java/com/example/Ejer12
│   │   ├── controller
│   │   ├── service
│   │   ├── repository
│   │   ├── DTO
│   │   ├── exception
│   │   ├── security
│   │   └── filter
│   └── resources
│       ├── static
│       └── application.properties
```

---

## Funcionalidades principales

* Registro de logs
* Gestión de aplicaciones
* Clasificación por niveles de log
* Validación de datos
* Seguridad mediante API Key
* Documentación automática con Swagger
* Manejo global de excepciones
* Persistencia en PostgreSQL

---

## Niveles de logs

El sistema utiliza distintos niveles de logs para clasificar eventos:

* INFO
* WARNING
* ERROR
* DEBUG

---

## Seguridad

La API utiliza un filtro personalizado de API Key:

```java
ApiKeyFilter
```

La configuración de seguridad se encuentra en:

```java
SecurityConfig
```

---

## Documentación Swagger

La documentación de la API se encuentra disponible mediante Swagger UI.

URL:

```bash
http://localhost:8080/swagger-ui.html
```

O:

```bash
http://localhost:8080/swagger-ui/index.html
```

---

## Instalación y ejecución

### 1. Clonar el repositorio

```bash
git clone https://github.com/SanDeber/LogHub.git
```

### 2. Entrar al proyecto

```bash
cd LogHub
```

### 3. Configurar PostgreSQL

Editar el archivo:

```bash
src/main/resources/application.properties
```

Agregar tus credenciales:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/loghub
spring.datasource.username=postgres
spring.datasource.password=tu_password
```

---

### 4. Ejecutar el proyecto

Con Maven Wrapper:

```bash
./mvnw spring-boot:run
```

En Windows:

```bash
mvnw.cmd spring-boot:run
```

---

## Frontend estático

El proyecto incluye una interfaz frontend básica ubicada en:

```bash
src/main/resources/static
```

Archivos:

* index.html
* style.css
* app.js

---

## Arquitectura

El proyecto sigue una arquitectura por capas:

```text
Controller → Service → Repository → Database
```

Además utiliza:

* DTOs para transferencia de datos
* Exceptions personalizadas
* Filtros de seguridad
* Configuración centralizada

---

## Clases importantes

### Controllers

* `ApplicationController`
* `LogController`

### Services

* `ApplicationService`
* `LogService`

### Repositories

* `ApplicationRepository`
* `LogRepository`

### Seguridad

* `SecurityConfig`
* `ApiKeyFilter`
* `SwaggerConfig`

### Exceptions

* `ApplicationNotFoundException`
* `InvalidApiKeyException`
* `GlobalExceptionHandler`

---

## Posibles mejoras futuras

* Autenticación JWT
* Dashboard de logs en tiempo real
* Filtros avanzados
* Exportación de logs
* Dockerización
* Tests unitarios y de integración
* Deploy en la nube

---

## Autor

Desarrollado por:

**Santino Debernardi**

GitHub:

[https://github.com/SanDeber](https://github.com/SanDeber)

---

## Licencia

Este proyecto es de uso educativo y académico.
