# Mini Twitter - Especificación de Requerimientos

## 📋 Reglas de Negocio

### 👤 Usuario

- ✅ Cada usuario conoce todos los Tweets que hizo
- ✅ No se pueden agregar dos usuarios con el mismo `userName`
- ✅ El `userName` no puede ser menor a **5 caracteres** ni mayor a **25 caracteres**
- ✅ Al eliminar un usuario, todos sus tweets deben ser eliminados (no existen tweets huérfanos)

### 🐦 Tweet

- ✅ Los tweets deben tener un texto de **1 carácter** como mínimo y **280 caracteres** como máximo
- ✅ Los tweets pertenecen a un usuario y se eliminan en cascada con él

### 🔄 Retweet

- ✅ Un tweet puede ser re-tweet de otro tweet
- ✅ El retweet debe conocer a su tweet de origen
- ✅ Un re-tweet **no tiene texto adicional**
- ✅ **No se permite** crear un re-tweet de un tweet creado por el mismo usuario que está retwitteando

---

## 🎯 Resumen de Validaciones

| Entidad | Campo | Validación |
|---------|-------|------------|
| Usuario | userName | 5-25 caracteres, único |
| Tweet | texto | 1-280 caracteres |
| Retweet | tweetOrigen | No puede ser del mismo usuario |

---

## 🌐 API REST - Endpoints para Postman

### 👤 Usuarios

#### **POST /usuarios** - Registrar un nuevo usuario

**URL:** `http://localhost:8080/usuarios`

**Headers:**
```
Content-Type: application/json
```

**Body (JSON):**
```json
{
  "userName": "juanperez"
}
```

**Respuestas:**
- ✅ `201 CREATED` - Usuario registrado exitosamente
- ❌ `400 BAD REQUEST` - userName duplicado, nulo, o longitud inválida (5-25 caracteres)

---

### 🐦 Tweets

#### **POST /tweets** - Publicar un nuevo tweet

**URL:** `http://localhost:8080/tweets`

**Headers:**
```
Content-Type: application/json
```

**Body (JSON):**
```json
{
  "userName": "juanperez",
  "texto": "Este es mi primer tweet en Mini Twitter!"
}
```

**Respuestas:**
- ✅ `201 CREATED` - Tweet publicado exitosamente
- ❌ `400 BAD REQUEST` - texto vacío o longitud inválida (1-280 caracteres)

---

### 🔄 Retweets

#### **POST /tweets/retweet** - Hacer un retweet

**URL:** `http://localhost:8080/tweets/retweet`

**Headers:**
```
Content-Type: application/json
```

**Body (JSON):**
```json
{
  "userName": "marialopez",
  "tweetOriginalId": 1
}
```

**Respuestas:**
- ✅ `201 CREATED` - Retweet realizado exitosamente
- ❌ `400 BAD REQUEST` - tweet original nulo o es un tweet propio

---

## 🧪 Casos de Prueba Sugeridos en Postman

### Flujo completo exitoso:
1. **Registrar Usuario 1:** `POST /usuarios` con `{"userName": "juanperez"}`
2. **Registrar Usuario 2:** `POST /usuarios` con `{"userName": "marialopez"}`
3. **Publicar Tweet:** `POST /tweets` con `{"userName": "juanperez", "texto": "Hola mundo!"}`
4. **Hacer Retweet:** `POST /tweets/retweet` con `{"userName": "marialopez", "tweetOriginalId": 1}`

### Casos de error a probar:
- ❌ Registrar usuario con username duplicado
- ❌ Registrar usuario con username corto (< 5 caracteres)
- ❌ Registrar usuario con username largo (> 25 caracteres)
- ❌ Publicar tweet vacío
- ❌ Publicar tweet muy largo (> 280 caracteres)
- ❌ Hacer retweet de tu propio tweet
- ❌ Hacer retweet con tweetOriginalId inexistente

