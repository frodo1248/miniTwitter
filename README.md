# 🐦 Mini Twitter

Una aplicación de red social simplificada estilo Twitter, desarrollada con Java 23 y Spring Boot, que permite a los usuarios publicar tweets, hacer retweets y visualizar timelines.

## 📋 Descripción

Mini Twitter es una API REST que implementa las funcionalidades básicas de una red social de microblogging. Los usuarios pueden crear tweets de hasta 280 caracteres, hacer retweets de publicaciones de otros usuarios, y visualizar timelines personalizados.

### ✨ Características Principales

- ✅ Registro de usuarios con validación de username
- ✅ Publicación de tweets (1-280 caracteres)
- ✅ Sistema de retweets
- ✅ Timeline por usuario
- ✅ Listado global de tweets
- ✅ Fechas en formato ISO 8601
- ✅ Distinción visual entre tweets y retweets
- ✅ API REST completa con validaciones

---

## 🛠️ Tecnologías Utilizadas

- **Java 23** - Lenguaje de programación
- **Spring Boot 3.5.6** - Framework principal
- **Spring Data JPA** - Persistencia de datos
- **Hibernate 7.0.7** - ORM
- **MariaDB** - Base de datos (producción)
- **H2 Database** - Base de datos en memoria (tests)
- **Lombok** - Reducción de código boilerplate
- **Maven** - Gestión de dependencias
- **JUnit 5 + Mockito** - Testing (90%+ cobertura)

---

## 🏗️ Arquitectura

El proyecto sigue una arquitectura en capas con modelo de dominio rico:

```
src/main/java/unrn/
├── config/          # Configuración (CORS)
├── model/           # Modelo de dominio (Usuario, Tweet, Retweet)
├── repository/      # Capa de persistencia (JPA)
├── service/         # Lógica de negocio
└── web/             # Controllers y DTOs
```

### Modelo de Dominio

- **Usuario**: Representa un usuario de la red social
- **Tweet**: Publicación de texto (1-280 caracteres)
- **Retweet**: Extensión de Tweet que referencia un tweet original

---

## 🚀 Instalación y Configuración

### Prerrequisitos

- Java 23 o superior
- Maven 3.6+
- MariaDB 10.x
- IDE recomendado: IntelliJ IDEA

### Configuración de Base de Datos

1. Crear la base de datos:
```sql
CREATE DATABASE twitter;
```

2. La aplicación usa estas credenciales por defecto:
   - **Usuario:** root
   - **Password:** (vacío)
   - **URL:** jdbc:mariadb://localhost:3306/twitter

3. Las tablas se crean automáticamente al iniciar la aplicación (configuración `CREATE_DROP`).

### Ejecutar la Aplicación

```bash
# Clonar el repositorio
git clone <url-del-repositorio>
cd miniTwitter

# Compilar el proyecto
mvn clean compile

# Ejecutar tests
mvn test

# Ejecutar la aplicación
mvn spring-boot:run
```

La aplicación estará disponible en: `http://localhost:8080`

---

## 📡 API REST - Endpoints

### 👤 Usuarios

#### Registrar Usuario
```http
POST /usuarios
Content-Type: application/json

{
  "userName": "juanperez"
}
```
**Respuestas:**
- `201 CREATED` - Usuario creado exitosamente
- `400 BAD REQUEST` - userName duplicado, nulo o longitud inválida (5-25 caracteres)

#### Listar Usuarios
```http
GET /usuarios
```
**Respuesta:**
```json
[
  {
    "userName": "juanperez"
  },
  {
    "userName": "marialopez"
  }
]
```

---

### 🐦 Tweets

#### Publicar Tweet
```http
POST /tweets
Content-Type: application/json

{
  "userName": "juanperez",
  "texto": "Mi primer tweet en Mini Twitter!"
}
```
**Respuestas:**
- `201 CREATED` - Tweet publicado exitosamente
- `400 BAD REQUEST` - Texto inválido (debe tener 1-280 caracteres)

#### Listar Todos los Tweets
```http
GET /tweets
```
**Respuesta:**
```json
[
  {
    "id": 1,
    "texto": "Mi primer tweet",
    "autor": "juanperez",
    "fecha": "2025-11-26T15:30:45.123",
    "esRetweet": false,
    "tweetOriginalId": null,
    "autorOriginal": null
  },
  {
    "id": 2,
    "texto": "Mi primer tweet",
    "autor": "marialopez",
    "fecha": "2025-11-26T15:35:20.456",
    "esRetweet": true,
    "tweetOriginalId": 1,
    "autorOriginal": "juanperez"
  }
]
```

#### Listar Tweets de un Usuario
```http
GET /tweets/usuario/{userName}
```
**Ejemplo:**
```http
GET /tweets/usuario/juanperez
```
**Respuestas:**
- `200 OK` - Lista de tweets del usuario
- `404 NOT FOUND` - Usuario no encontrado

---

### 🔄 Retweets

#### Hacer Retweet
```http
POST /tweets/retweet
Content-Type: application/json

{
  "userName": "marialopez",
  "tweetOriginalId": 1
}
```
**Respuestas:**
- `201 CREATED` - Retweet creado exitosamente
- `400 BAD REQUEST` - Tweet original no existe o es del mismo usuario

---

## 📊 Datos de Prueba

El proyecto incluye un script SQL con datos de prueba listos para usar:

### 📄 Archivo: `src/main/resources/data-test.sql`

**Contenido:**
- ✅ 5 usuarios de ejemplo
- ✅ 80 tweets originales (16 por usuario)
- ✅ 8 retweets distribuidos entre usuarios
- ✅ **Total: 88 registros** para probar la aplicación

### Usuarios Incluidos:
- `juanperez` - Tweets sobre Java y Spring Boot
- `marialopez` - Tweets sobre desarrollo backend
- `carlosgomez` - Tweets sobre Clean Code y SOLID
- `anarodriguez` - Tweets sobre full-stack development
- `luismartinez` - Tweets sobre principios de desarrollo

### Cómo Usar los Datos de Prueba:

```bash
# Conectarse a MariaDB
mysql -u root -p

# Seleccionar la base de datos
USE twitter;

# Ejecutar el script
SOURCE src/main/resources/data-test.sql;

# Verificar datos
SELECT COUNT(*) FROM usuarios;  -- Debe retornar 5
SELECT COUNT(*) FROM tweets;    -- Debe retornar 88
```

Alternativamente, puedes copiar el contenido del archivo y ejecutarlo en tu cliente SQL favorito (DBeaver, MySQL Workbench, etc.).

---

## 🧪 Testing

El proyecto cuenta con una cobertura de tests del **90%+**:

### Ejecutar Tests
```bash
# Todos los tests
mvn test

# Solo tests unitarios
mvn test -Dtest="*Test"

# Solo tests de integración
mvn test -Dtest="*IntegrationTest"
```

### Reporte de Cobertura
```bash
mvn test jacoco:report

# Ver reporte HTML
# Abrir: target/site/jacoco/index.html
```

### Tipos de Tests
- **Tests Unitarios** - Controllers, Services, DTOs (con Mockito)
- **Tests de Integración** - Flujo completo con H2 en memoria
- **Tests de Configuración** - CORS y configuraciones Spring

---

## 🌐 Configuración CORS

La aplicación tiene CORS configurado para permitir peticiones desde:
- `http://localhost:5173` (Vite React por defecto)

Para cambiar el origen permitido, editar: `src/main/java/unrn/config/CorsConfig.java`

---

## 📁 Estructura del Proyecto

```
miniTwitter/
├── src/
│   ├── main/
│   │   ├── java/unrn/
│   │   │   ├── config/           # Configuraciones (CORS)
│   │   │   ├── model/            # Entidades (Usuario, Tweet, Retweet)
│   │   │   ├── repository/       # Interfaces JPA
│   │   │   ├── service/          # Lógica de negocio
│   │   │   └── web/              # Controllers y DTOs
│   │   └── resources/
│   │       └── data-test.sql     # Script de datos de prueba
│   └── test/
│       └── java/unrn/
│           ├── config/           # Tests de configuración
│           ├── integration/      # Tests de integración
│           ├── model/            # Tests del modelo
│           ├── repository/       # Tests de repositories
│           ├── service/          # Tests de servicios
│           └── web/              # Tests de controllers y DTOs
├── pom.xml
└── README.md
```

---

## 🔒 Validaciones

### Usuario
- **userName**: 5-25 caracteres, único, no nulo

### Tweet
- **texto**: 1-280 caracteres, no nulo
- **autor**: Debe existir en el sistema

### Retweet
- **tweetOriginal**: No puede ser nulo
- **restricción**: No se puede hacer retweet de un tweet propio

---

## 📝 Reglas de Negocio

1. Los usuarios deben tener un userName único
2. Los tweets tienen un límite de 280 caracteres
3. Los retweets no tienen texto propio (muestran el texto del tweet original)
4. No se puede hacer retweet de un tweet propio
5. La fecha se asigna automáticamente al crear un tweet
6. Los tweets se ordenan por fecha descendente

---

## 🔍 Ejemplos de Uso con cURL

### Registrar un usuario
```bash
curl -X POST http://localhost:8080/usuarios \
  -H "Content-Type: application/json" \
  -d '{"userName": "testuser"}'
```

### Publicar un tweet
```bash
curl -X POST http://localhost:8080/tweets \
  -H "Content-Type: application/json" \
  -d '{"userName": "testuser", "texto": "Mi primer tweet!"}'
```

### Listar tweets
```bash
curl -X GET http://localhost:8080/tweets
```

### Hacer retweet
```bash
curl -X POST http://localhost:8080/tweets/retweet \
  -H "Content-Type: application/json" \
  -d '{"userName": "testuser", "tweetOriginalId": 1}'
```

---

## 📚 Documentación Adicional

El proyecto incluye documentación detallada en los siguientes archivos:

- `ENDPOINTS.md` - Especificación completa de la API REST
- `CORS_CONFIG.md` - Configuración de CORS para frontend
- `ESTRATEGIA_TESTING_COBERTURA.md` - Estrategia y cobertura de tests
- `INSTRUCCIONES_DATA_TEST.md` - Guía detallada del script SQL

---

## 🤝 Contribuir

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/NuevaCaracteristica`)
3. Commit tus cambios (`git commit -m 'Agregar nueva característica'`)
4. Push a la rama (`git push origin feature/NuevaCaracteristica`)
5. Abre un Pull Request

---

## 📄 Licencia

Este proyecto es un trabajo académico desarrollado para fines educativos.

---

## 👨‍💻 Autor

Desarrollado como proyecto educativo para la materia de Programación Orientada a Objetos.

---

## 🎯 Roadmap Futuro

- [ ] Autenticación y autorización (Spring Security)
- [ ] Sistema de likes
- [ ] Comentarios en tweets
- [ ] Seguir/dejar de seguir usuarios
- [ ] Timeline personalizado (solo usuarios seguidos)
- [ ] Búsqueda de tweets
- [ ] Hashtags
- [ ] Notificaciones
- [ ] Paginación en listados
- [ ] Imágenes en tweets
- [ ] API versioning

---

**Última actualización:** 2025-11-26

¡Gracias por usar Mini Twitter! 🐦✨

