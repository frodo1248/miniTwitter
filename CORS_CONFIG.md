# 🔒 Configuración CORS - Mini Twitter

## ¿Qué es CORS?

CORS (Cross-Origin Resource Sharing) es un mecanismo de seguridad que permite que tu frontend (React en Vite) pueda hacer peticiones a tu backend (Spring Boot) cuando están en diferentes puertos.

## 🎯 Configuración Actual

El backend está configurado para aceptar peticiones desde:
- **Frontend URL:** `http://localhost:5173` (Vite default)
- **Backend URL:** `http://localhost:8080` (Spring Boot default)

## 📁 Archivo de Configuración

**Ubicación:** `src/main/java/unrn/config/CorsConfig.java`

```java
@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:5173")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}
```

## 🔧 Personalización

### Si tu frontend corre en otro puerto:

Modifica la línea `allowedOrigins` en `CorsConfig.java`:

```java
.allowedOrigins("http://localhost:TU_PUERTO")
```

### Si quieres permitir múltiples orígenes:

```java
.allowedOrigins(
    "http://localhost:5173",
    "http://localhost:3000",
    "http://localhost:4200"
)
```

### Para producción:

```java
.allowedOrigins(
    "http://localhost:5173",  // Desarrollo
    "https://tu-dominio.com"   // Producción
)
```

## ✅ Cómo Verificar que Funciona

1. **Inicia tu backend Spring Boot:**
   ```bash
   mvn spring-boot:run
   ```

2. **Inicia tu frontend React/Vite:**
   ```bash
   npm run dev
   ```

3. **Haz una petición desde tu frontend:**
   ```javascript
   fetch('http://localhost:8080/usuarios')
     .then(response => response.json())
     .then(data => console.log(data));
   ```

4. **Verifica la consola del navegador:**
   - ✅ Si funciona: verás la respuesta JSON
   - ❌ Si falla: verás error de CORS

## 🐛 Troubleshooting

### Error: "CORS policy: No 'Access-Control-Allow-Origin'"

**Solución:**
1. Verifica que `CorsConfig.java` exista en `src/main/java/unrn/config/`
2. Reinicia la aplicación Spring Boot
3. Verifica que el puerto del frontend sea el correcto (5173 para Vite)

### Error persiste después de configurar CORS

**Posibles causas:**
1. El backend no se reinició después de agregar la configuración
2. El puerto del frontend es diferente a 5173
3. Hay un firewall o proxy bloqueando las peticiones

### Frontend en puerto diferente

Si tu Vite corre en otro puerto, edita `vite.config.js`:

```javascript
export default {
  server: {
    port: 5173  // Cambiar este número
  }
}
```

## 📖 Ejemplo de Uso en React

```javascript
// Obtener usuarios
async function obtenerUsuarios() {
  try {
    const response = await fetch('http://localhost:8080/usuarios');
    const usuarios = await response.json();
    console.log(usuarios);
  } catch (error) {
    console.error('Error:', error);
  }
}

// Registrar usuario
async function registrarUsuario(userName) {
  try {
    const response = await fetch('http://localhost:8080/usuarios', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ userName })
    });
    
    if (response.ok) {
      console.log('Usuario registrado exitosamente');
    }
  } catch (error) {
    console.error('Error:', error);
  }
}
```

## 🔐 Métodos HTTP Permitidos

- ✅ GET
- ✅ POST
- ✅ PUT
- ✅ DELETE
- ✅ OPTIONS (preflight)

## 📝 Headers Permitidos

- ✅ Todos (`*`)
- Incluye: `Content-Type`, `Authorization`, `Accept`, etc.

## 🌐 Credentials

- ✅ Habilitado: Permite enviar cookies y headers de autenticación

---

**Nota:** Esta configuración es para desarrollo. En producción, deberías especificar exactamente los orígenes permitidos en lugar de usar `*` o localhost.

