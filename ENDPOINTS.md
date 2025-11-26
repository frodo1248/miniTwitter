# 🌐 Mini Twitter - API REST Endpoints

## 📌 Base URL
```
http://localhost:8080
```

---

## 👤 USUARIOS

### **POST /usuarios** - Registrar un nuevo usuario

Registra un nuevo usuario en el sistema.

**Endpoint:** `POST /usuarios`

**Request Body:**
```json
{
  "userName": "juanperez"
}
```

**Validaciones:**
- `userName`: obligatorio, 5-25 caracteres, único

**Respuestas:**
- ✅ **201 CREATED** - Usuario registrado exitosamente
- ❌ **400 BAD REQUEST** 
  - "Ya existe un usuario con ese userName"
  - "El userName debe tener entre 5 y 25 caracteres"
  - "El userName no puede ser nulo"
  - "El userName no puede estar vacío"
- ❌ **500 INTERNAL SERVER ERROR** - Error inesperado

---

### **GET /usuarios** - Listar todos los usuarios

Obtiene la lista de todos los usuarios registrados.

**Endpoint:** `GET /usuarios`

**Respuestas:**
- ✅ **200 OK** - Lista de usuarios

**Ejemplo de Respuesta:**
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

**Ejemplo con cURL:**
```bash
curl -X GET http://localhost:8080/usuarios
```

---

## 🐦 TWEETS

### **POST /tweets** - Publicar un nuevo tweet

Publica un tweet de un usuario existente.

**Endpoint:** `POST /tweets`

**Request Body:**
```json
{
  "userName": "juanperez",
  "texto": "Este es mi primer tweet en Mini Twitter!"
}
```

**Validaciones:**
- `userName`: obligatorio, no puede estar vacío
- `texto`: obligatorio, 1-280 caracteres

**Respuestas:**
- ✅ **201 CREATED** - Tweet publicado exitosamente
- ❌ **400 BAD REQUEST**
  - "El texto del tweet debe tener entre 1 y 280 caracteres"
  - "El userName no puede estar vacío"
  - "El texto del tweet no puede estar vacío"
- ❌ **500 INTERNAL SERVER ERROR** - Error inesperado

---

### **GET /tweets** - Listar todos los tweets

Obtiene la lista de todos los tweets publicados (tweets normales y retweets).

**Endpoint:** `GET /tweets`

**Respuestas:**
- ✅ **200 OK** - Lista de tweets

**Ejemplo de Respuesta:**
```json
[
  {
    "id": 2,
    "texto": "Mi primer tweet sobre Java y Spring Boot!",
    "autor": "juanperez",
    "fecha": "2025-11-26T15:30:45.123",
    "esRetweet": false,
    "tweetOriginalId": null,
    "autorOriginal": null
  },
  {
    "id": 3,
    "texto": "Mi primer tweet sobre Java y Spring Boot!",
    "autor": "marialopez",
    "fecha": "2025-11-26T15:35:20.456",
    "esRetweet": true,
    "tweetOriginalId": 2,
    "autorOriginal": "juanperez"
  }
]
```

**Ejemplo con cURL:**
```bash
curl -X GET http://localhost:8080/tweets
```

---

### **GET /tweets/usuario/{userName}** - Listar tweets de un usuario

Obtiene todos los tweets (normales y retweets) de un usuario específico.

**Endpoint:** `GET /tweets/usuario/{userName}`

**Parámetros de ruta:**
- `userName`: El nombre de usuario

**Respuestas:**
- ✅ **200 OK** - Lista de tweets del usuario
- ❌ **404 NOT FOUND** - Usuario no encontrado

**Ejemplo de Respuesta:**
```json
[
  {
    "id": 2,
    "texto": "Mi primer tweet sobre Java y Spring Boot!",
    "autor": "juanperez",
    "fecha": "2025-11-26T15:30:45.123",
    "esRetweet": false,
    "tweetOriginalId": null,
    "autorOriginal": null
  },
  {
    "id": 4,
    "texto": "Aprendiendo sobre el modelo de dominio",
    "autor": "juanperez",
    "fecha": "2025-11-26T16:10:12.789",
    "esRetweet": false,
    "tweetOriginalId": null,
    "autorOriginal": null
  }
]
```

**Ejemplo con cURL:**
```bash
curl -X GET http://localhost:8080/tweets/usuario/juanperez
```

---

## 🔄 RETWEETS

### **POST /tweets/retweet** - Hacer un retweet

Permite a un usuario retwittear un tweet de otro usuario.

**Endpoint:** `POST /tweets/retweet`

**Request Body:**
```json
{
  "userName": "marialopez",
  "tweetOriginalId": 1
}
```

**Validaciones:**
- `userName`: obligatorio, no puede estar vacío
- `tweetOriginalId`: obligatorio, no puede ser nulo
- El tweet original debe existir
- No se puede hacer retweet de un tweet propio

**Respuestas:**
- ✅ **201 CREATED** - Retweet realizado exitosamente
- ❌ **400 BAD REQUEST**
  - "El tweet original no puede ser nulo"
  - "No se puede hacer retweet de un tweet propio"
  - "El userName no puede estar vacío"
  - "El ID del tweet original no puede ser nulo"
- ❌ **500 INTERNAL SERVER ERROR** - Error inesperado


---

## 🧪 Flujo de Prueba Completo en Postman

### 1️⃣ Registrar dos usuarios
```json
POST /usuarios
{
  "userName": "juanperez"
}
```
```json
POST /usuarios
{
  "userName": "marialopez"
}
```

### 2️⃣ Publicar un tweet
```json
POST /tweets
{
  "userName": "juanperez",
  "texto": "Mi primer tweet sobre Java y Spring Boot!"
}
```

### 3️⃣ Hacer un retweet
```json
POST /tweets/retweet
{
  "userName": "marialopez",
  "tweetOriginalId": 1
}
```

### 4️⃣ Listar todos los tweets
```
GET /tweets
```

### 5️⃣ Listar tweets de un usuario específico
```
GET /tweets/usuario/juanperez
```

### 6️⃣ Listar todos los usuarios
```
GET /usuarios
```

---

## 📊 Resumen de Endpoints

| Método | Endpoint | Descripción | Status Success |
|--------|----------|-------------|----------------|
| POST | `/usuarios` | Registrar usuario | 201 |
| GET | `/usuarios` | Listar todos los usuarios | 200 |
| POST | `/tweets` | Publicar tweet | 201 |
| GET | `/tweets` | Listar todos los tweets | 200 |
| GET | `/tweets/usuario/{userName}` | Listar tweets de un usuario | 200 |
| POST | `/tweets/retweet` | Hacer retweet | 201 |

---

## 🔍 Casos de Error a Probar

### Usuarios:
- ❌ Username duplicado
- ❌ Username muy corto (< 5 caracteres)
- ❌ Username muy largo (> 25 caracteres)
- ❌ Username vacío o nulo

### Tweets:
- ❌ Texto vacío
- ❌ Texto muy largo (> 280 caracteres)
- ❌ Usuario inexistente

### Retweets:
- ❌ Tweet original inexistente
- ❌ Retweet de tweet propio
- ❌ ID de tweet nulo
- ❌ Usuario inexistente

---

## 📝 Notas Técnicas

- **Content-Type requerido:** `application/json` (solo para POST)
- **Encoding:** UTF-8
- **Formato de respuesta de error:** Texto plano con el mensaje del error
- **Respuestas exitosas POST:** Sin body, solo status code 201
- **Respuestas exitosas GET:** JSON con status code 200
- **Base de datos:** H2 en memoria (datos se pierden al reiniciar)
- **CORS:** Configurado para aceptar peticiones desde `http://localhost:5173` (Vite/React)

### 📋 Estructura de Respuestas JSON

#### UsuarioResponse:
```json
{
  "userName": "string"
}
```

#### TweetResponse (Tweet normal):
```json
{
  "id": "number",
  "texto": "string",
  "autor": "string",
  "fecha": "string (ISO 8601 format: 2025-11-26T15:30:45.123)",
  "esRetweet": false,
  "tweetOriginalId": null,
  "autorOriginal": null
}
```

#### TweetResponse (Retweet):
```json
{
  "id": "number",
  "texto": "string (texto del tweet original)",
  "autor": "string (quien hizo el retweet)",
  "fecha": "string (fecha del retweet, ISO 8601 format)",
  "esRetweet": true,
  "tweetOriginalId": "number",
  "autorOriginal": "string (autor del tweet original)"
}
```

---

## 🐛 Correcciones Recientes

- **2025-11-23:** Corregido error 500 en retweets - La columna `texto` no permite null en BD, ahora los retweets usan string vacío

## ✨ Nuevas Funcionalidades

- **2025-11-23:** Agregados endpoints GET para listar usuarios y tweets
- **2025-11-23:** Agregada documentación completa de estructuras de respuesta JSON
- **2025-11-23:** Configurado CORS para permitir peticiones desde frontend React/Vite
- **2025-11-26:** Eliminado campo `clave` del modelo Usuario - Simplificación del registro
- **2025-11-26:** Agregado campo `fecha` a los tweets - Formato ISO 8601 para mostrar en frontend

---

**Última actualización:** 2025-11-26

