-- Script para resetear la base de datos después del cambio de estrategia de herencia
-- Ejecutar este script en MariaDB antes de reiniciar la aplicación

DROP TABLE IF EXISTS retweets;
DROP TABLE IF EXISTS tweets;
DROP TABLE IF EXISTS usuarios;

-- Las tablas se recrearán automáticamente cuando inicies la aplicación
-- gracias a la configuración Action.CREATE_DROP en EmfBuilder

