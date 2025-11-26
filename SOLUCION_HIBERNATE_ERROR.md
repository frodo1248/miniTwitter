# Solución al Error de Hibernate

## Problema
Hibernate 7.0.7 tiene un bug conocido con la estrategia de herencia `JOINED` que causa el error:
```
java.lang.IllegalStateException: Illegal pop() with non-matching JdbcValuesSourceProcessingState
```

## Solución Aplicada
Se cambió la estrategia de herencia de `JOINED` a `SINGLE_TABLE`:

### Cambios en Tweet.java
- `@Inheritance(strategy = InheritanceType.SINGLE_TABLE)`
- `@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)`
- `@DiscriminatorValue("TWEET")`

### Cambios en Retweet.java
- Se eliminó `@Table(name = "retweets")`
- Se agregó `@DiscriminatorValue("RETWEET")`

### Impacto en la Base de Datos
Ahora `Tweet` y `Retweet` se almacenan en una sola tabla `tweets` con una columna `tipo` que distingue entre 'TWEET' y 'RETWEET'.

## Cómo Proceder

### Opción 1: Reiniciar la aplicación (más simple)
1. Detén el servidor si está corriendo
2. Abre MySQL/MariaDB y ejecuta el script `reset-database.sql`
3. Reinicia la aplicación - Hibernate recreará las tablas automáticamente

### Opción 2: Manual
Ejecuta estos comandos en MariaDB:
```sql
USE twitter;
DROP TABLE IF EXISTS retweets;
DROP TABLE IF EXISTS tweets;
DROP TABLE IF EXISTS usuarios;
```

Luego reinicia la aplicación.

## Ventajas de SINGLE_TABLE
- ✅ Evita el bug de Hibernate 7.0.7
- ✅ Mejor performance en queries (menos JOINs)
- ✅ Más simple de mantener
- ✅ Queries polimórficas más rápidas

## Nota
La configuración `Action.CREATE_DROP` en `EmfBuilder` recreará automáticamente las tablas cuando inicies la aplicación.

