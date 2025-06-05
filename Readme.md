# API de Gestión de Pacientes - GraphQL + REST con Swagger

Este proyecto implementa una API para la gestión de pacientes que soporta tanto GraphQL como REST, con documentación completa usando Swagger/OpenAPI.

## 🚀 Características

- **GraphQL API** - Consultas flexibles y eficientes
- **REST API** - Endpoints tradicionales RESTful  
- **Documentación Swagger** - Documentación interactiva completa
- **CORS habilitado** - Acceso desde diferentes dominios
- **Spring Boot 3.2** - Framework moderno y robusto

## 📋 Prerrequisitos

- Java 17 o superior
- Maven 3.6 o superior

## 🛠️ Instalación y Ejecución

1. **Clona el repositorio**
   ```bash
   git clone <url-del-repositorio>
   cd graphql-springboot
   ```

2. **Compila el proyecto**
   ```bash
   mvn clean compile
   ```

3. **Ejecuta la aplicación**
   ```bash
   mvn spring-boot:run
   ```

4. **Verifica que esté funcionando**
   - La aplicación estará disponible en: `http://localhost:8080`

## 📚 Documentación y Interfaces

### Swagger UI (Documentación REST)
- **URL**: `http://localhost:8080/swagger-ui.html`
- **Descripción**: Interfaz interactiva para probar y documentar los endpoints REST

### GraphiQL (Interfaz GraphQL)
- **URL**: `http://localhost:8080/graphiql`
- **Descripción**: Interfaz para escribir y probar consultas GraphQL

### OpenAPI JSON
- **URL**: `http://localhost:8080/api-docs`
- **Descripción**: Especificación OpenAPI en formato JSON

## 🔧 Endpoints REST

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `GET` | `/api/pacientes` | Obtener todos los pacientes |
| `GET` | `/api/pacientes/{id}` | Obtener paciente por ID |
| `POST` | `/api/pacientes` | Crear nuevo paciente |
| `PUT` | `/api/pacientes/{id}` | Actualizar paciente existente |
| `DELETE` | `/api/pacientes/{id}` | Eliminar paciente |

### Ejemplo de Request Body (POST/PUT)
```json
{
  "name": "Juan Pérez García",
  "email": "juan.perez@email.com"
}
```

### Ejemplo de Response
```json
{
  "id": "123e4567-e89b-12d3-a456-426614174000",
  "name": "Juan Pérez García",
  "email": "juan.perez@email.com"
}
```

## 🔍 Consultas GraphQL

### Obtener todos los pacientes
```graphql
{
  getPacientes {
    id
    name
    email
  }
}
```

### Obtener paciente por ID
```graphql
{
  getPacienteById(id: "123e4567-e89b-12d3-a456-426614174000") {
    id
    name
    email
  }
}
```

### Crear nuevo paciente
```graphql
mutation {
  createPaciente(input: {
    name: "María González"
    email: "maria@email.com"
  }) {
    id
    name
    email
  }
}
```

### Actualizar paciente
```graphql
mutation {
  updatePaciente(id: "123e4567-e89b-12d3-a456-426614174000", input: {
    name: "María González López"
    email: "maria.gonzalez@email.com"
  }) {
    id
    name
    email
  }
}
```

### Eliminar paciente
```graphql
mutation {
  deletePaciente(id: "123e4567-e89b-12d3-a456-426614174000")
}
```

## 🏗️ Estructura del Proyecto

```
src/main/java/com/ejemplo/graphql_springboot/
├── GraphQlSpringBootApplication.java    # Clase principal
├── config/
│   ├── SwaggerConfig.java              # Configuración de Swagger
│   └── WebConfig.java                  # Configuración CORS
├── controller/
│   ├── PacienteController.java         # Controlador GraphQL
│   └── PacienteRestController.java     # Controlador REST
├── model/
│   └── Paciente.java                   # Modelo de datos
└── service/
    └── PacienteService.java            # Lógica de negocio

src/main/resources/
├── application.properties              # Configuración de la aplicación
└── graphql/
    └── schema.graphqls                # Schema de GraphQL
```

## 🧪 Probando la API

### Con cURL (REST)

**Crear paciente:**
```bash
curl -X POST http://localhost:8080/api/pacientes \
  -H "Content-Type: application/json" \
  -d '{"name":"Juan Pérez","email":"juan@email.com"}'
```

**Obtener todos los pacientes:**
```bash
curl http://localhost:8080/api/pacientes
```

### Con GraphQL

Visita `http://localhost:8080/graphiql` y ejecuta las consultas mencionadas anteriormente.

## ⚙️ Configuración

La configuración principal se encuentra en `application.properties`:

```properties
# Configuración de GraphQL
spring.graphql.graphiql.enabled=true
spring.graphql.graphiql.path=/graphiql

# Configuración de Swagger
springdoc.api-docs.enabled=true
springdoc.swagger-ui.enabled=true
springdoc.swagger-ui.path=/swagger-ui.html
```

## 🤝 Contribución

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

## 📝 Notas Importantes

- Los datos se almacenan en memoria, se perderán al reiniciar la aplicación
- Para producción, considera implementar persistencia con JPA/Hibernate
- Las validaciones están implementadas básicamente, considera añadir más validaciones según tus necesidades
- El CORS está configurado para permitir todos los orígenes, ajústalo según tu entorno

## 📄 Licencia

Este proyecto está bajo la Licencia MIT - ver el archivo [LICENSE.md](LICENSE.md) para más detalles.