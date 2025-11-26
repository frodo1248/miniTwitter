-- Script SQL para poblar la base de datos con datos de prueba
-- 5 usuarios, más de 15 tweets cada uno, y 8 retweets

-- ====================================
-- 1. INSERTAR USUARIOS (5)
-- ====================================
INSERT INTO usuarios (userName) VALUES ('juanperez');
INSERT INTO usuarios (userName) VALUES ('marialopez');
INSERT INTO usuarios (userName) VALUES ('carlosgomez');
INSERT INTO usuarios (userName) VALUES ('anarodriguez');
INSERT INTO usuarios (userName) VALUES ('luismartinez');

-- ====================================
-- 2. INSERTAR TWEETS (80 tweets totales = 16 por usuario)
-- ====================================

-- Tweets de juanperez (16 tweets)
INSERT INTO tweets (tipo, texto, autor_username, fecha, tweet_original_id) VALUES
('TWEET', 'Hola mundo! Este es mi primer tweet en Mini Twitter', 'juanperez', NOW(), NULL),
('TWEET', 'Aprendiendo Java y Spring Boot es increíble 🚀', 'juanperez', NOW(), NULL),
('TWEET', 'El paradigma orientado a objetos facilita mucho el desarrollo', 'juanperez', NOW(), NULL),
('TWEET', 'Hoy implementé un nuevo endpoint REST', 'juanperez', NOW(), NULL),
('TWEET', 'Los tests unitarios son fundamentales para código de calidad', 'juanperez', NOW(), NULL),
('TWEET', 'Me encanta usar Mockito para los tests', 'juanperez', NOW(), NULL),
('TWEET', 'Spring Boot hace que desarrollar APIs sea muy sencillo', 'juanperez', NOW(), NULL),
('TWEET', 'Trabajando con JPA y Hibernate es muy potente', 'juanperez', NOW(), NULL),
('TWEET', 'El patrón Repository simplifica el acceso a datos', 'juanperez', NOW(), NULL),
('TWEET', 'Los DTOs ayudan a separar las capas correctamente', 'juanperez', NOW(), NULL),
('TWEET', 'Configurar CORS fue más fácil de lo que pensaba', 'juanperez', NOW(), NULL),
('TWEET', 'LocalDateTime es perfecto para manejar fechas', 'juanperez', NOW(), NULL),
('TWEET', 'Maven facilita la gestión de dependencias', 'juanperez', NOW(), NULL),
('TWEET', 'IntelliJ IDEA es un IDE espectacular', 'juanperez', NOW(), NULL),
('TWEET', 'El modelo de dominio rico es mejor que objetos anémicos', 'juanperez', NOW(), NULL),
('TWEET', 'Alcanzar 90% de cobertura de tests es un gran logro', 'juanperez', NOW(), NULL);

-- Tweets de marialopez (16 tweets)
INSERT INTO tweets (tipo, texto, autor_username, fecha, tweet_original_id) VALUES
('TWEET', 'Empezando mi viaje en el desarrollo backend', 'marialopez', NOW(), NULL),
('TWEET', 'Java 23 trae características muy interesantes', 'marialopez', NOW(), NULL),
('TWEET', 'Los records en Java simplifican tanto el código', 'marialopez', NOW(), NULL),
('TWEET', 'Spring Security será mi próximo tema de estudio', 'marialopez', NOW(), NULL),
('TWEET', 'Entendiendo mejor el principio Tell Dont Ask', 'marialopez', NOW(), NULL),
('TWEET', 'Los tests de integración con H2 funcionan perfectamente', 'marialopez', NOW(), NULL),
('TWEET', 'Lombok reduce mucho el código boilerplate', 'marialopez', NOW(), NULL),
('TWEET', 'Las validaciones en el constructor son muy útiles', 'marialopez', NOW(), NULL),
('TWEET', 'Diseñar una API REST requiere pensar en los recursos', 'marialopez', NOW(), NULL),
('TWEET', 'Los códigos de estado HTTP tienen mucho sentido ahora', 'marialopez', NOW(), NULL),
('TWEET', 'MariaDB es una excelente base de datos relacional', 'marialopez', NOW(), NULL),
('TWEET', 'El patrón MVC separa las responsabilidades claramente', 'marialopez', NOW(), NULL),
('TWEET', 'JSON es el formato perfecto para APIs REST', 'marialopez', NOW(), NULL),
('TWEET', 'Postman es indispensable para probar endpoints', 'marialopez', NOW(), NULL),
('TWEET', 'La inyección de dependencias de Spring es mágica', 'marialopez', NOW(), NULL),
('TWEET', 'Documentar el código es tan importante como escribirlo', 'marialopez', NOW(), NULL);

-- Tweets de carlosgomez (16 tweets)
INSERT INTO tweets (tipo, texto, autor_username, fecha, tweet_original_id) VALUES
('TWEET', 'Buenos días a todos los desarrolladores', 'carlosgomez', NOW(), NULL),
('TWEET', 'Refactorizando código legacy hoy', 'carlosgomez', NOW(), NULL),
('TWEET', 'Clean Code debería ser lectura obligatoria', 'carlosgomez', NOW(), NULL),
('TWEET', 'Los principios SOLID son la base del buen diseño', 'carlosgomez', NOW(), NULL),
('TWEET', 'Single Responsibility Principle en acción', 'carlosgomez', NOW(), NULL),
('TWEET', 'Open/Closed Principle hace el código más flexible', 'carlosgomez', NOW(), NULL),
('TWEET', 'Git es esencial para el trabajo en equipo', 'carlosgomez', NOW(), NULL),
('TWEET', 'Los commits atómicos facilitan el seguimiento', 'carlosgomez', NOW(), NULL),
('TWEET', 'Code review mejora la calidad del equipo', 'carlosgomez', NOW(), NULL),
('TWEET', 'Pair programming es más productivo de lo que parece', 'carlosgomez', NOW(), NULL),
('TWEET', 'TDD cambia completamente la forma de desarrollar', 'carlosgomez', NOW(), NULL),
('TWEET', 'Red-Green-Refactor es un ciclo muy efectivo', 'carlosgomez', NOW(), NULL),
('TWEET', 'Los microservicios tienen sus ventajas y desventajas', 'carlosgomez', NOW(), NULL),
('TWEET', 'Docker simplifica el deployment enormemente', 'carlosgomez', NOW(), NULL),
('TWEET', 'CI/CD automatiza y acelera el proceso de entrega', 'carlosgomez', NOW(), NULL),
('TWEET', 'La arquitectura hexagonal separa el negocio de la infraestructura', 'carlosgomez', NOW(), NULL);

-- Tweets de anarodriguez (16 tweets)
INSERT INTO tweets (tipo, texto, autor_username, fecha, tweet_original_id) VALUES
('TWEET', 'Feliz de estar aprendiendo desarrollo web', 'anarodriguez', NOW(), NULL),
('TWEET', 'React + Spring Boot es una combinación poderosa', 'anarodriguez', NOW(), NULL),
('TWEET', 'Vite hace que el desarrollo frontend sea rapidísimo', 'anarodriguez', NOW(), NULL),
('TWEET', 'Los hooks de React son geniales', 'anarodriguez', NOW(), NULL),
('TWEET', 'useState y useEffect son mis favoritos', 'anarodriguez', NOW(), NULL),
('TWEET', 'Fetch API facilita las llamadas al backend', 'anarodriguez', NOW(), NULL),
('TWEET', 'Async/await hace el código más legible', 'anarodriguez', NOW(), NULL),
('TWEET', 'Tailwind CSS acelera el diseño de interfaces', 'anarodriguez', NOW(), NULL),
('TWEET', 'TypeScript añade seguridad de tipos al JavaScript', 'anarodriguez', NOW(), NULL),
('TWEET', 'ESLint mantiene el código consistente', 'anarodriguez', NOW(), NULL),
('TWEET', 'npm es el gestor de paquetes más usado', 'anarodriguez', NOW(), NULL),
('TWEET', 'Component-driven development es el futuro', 'anarodriguez', NOW(), NULL),
('TWEET', 'Props y state son conceptos fundamentales en React', 'anarodriguez', NOW(), NULL),
('TWEET', 'El Virtual DOM optimiza el renderizado', 'anarodriguez', NOW(), NULL),
('TWEET', 'React Developer Tools es muy útil para debugging', 'anarodriguez', NOW(), NULL),
('TWEET', 'Full-stack development abre muchas oportunidades', 'anarodriguez', NOW(), NULL);

-- Tweets de luismartinez (16 tweets)
INSERT INTO tweets (tipo, texto, autor_username, fecha, tweet_original_id) VALUES
('TWEET', 'Comenzando el día con café y código', 'luismartinez', NOW(), NULL),
('TWEET', 'La consistencia es clave en el aprendizaje', 'luismartinez', NOW(), NULL),
('TWEET', 'Stack Overflow ha salvado mi código miles de veces', 'luismartinez', NOW(), NULL),
('TWEET', 'GitHub es más que un repositorio, es una comunidad', 'luismartinez', NOW(), NULL),
('TWEET', 'Open source permite aprender de los mejores', 'luismartinez', NOW(), NULL),
('TWEET', 'Markdown hace que documentar sea fácil', 'luismartinez', NOW(), NULL),
('TWEET', 'README.md es la primera impresión de tu proyecto', 'luismartinez', NOW(), NULL),
('TWEET', 'Las buenas prácticas se aprenden con experiencia', 'luismartinez', NOW(), NULL),
('TWEET', 'Refactorizar regularmente previene deuda técnica', 'luismartinez', NOW(), NULL),
('TWEET', 'Los comentarios deben explicar el por qué, no el qué', 'luismartinez', NOW(), NULL),
('TWEET', 'Las variables deben tener nombres descriptivos', 'luismartinez', NOW(), NULL),
('TWEET', 'DRY: Dont Repeat Yourself es un principio vital', 'luismartinez', NOW(), NULL),
('TWEET', 'KISS: Keep It Simple, Stupid aplica siempre', 'luismartinez', NOW(), NULL),
('TWEET', 'YAGNI: You Arent Gonna Need It evita sobre-ingeniería', 'luismartinez', NOW(), NULL),
('TWEET', 'El debugging es parte del arte de programar', 'luismartinez', NOW(), NULL),
('TWEET', 'Nunca dejes de aprender en este campo', 'luismartinez', NOW(), NULL);

-- ====================================
-- 3. INSERTAR RETWEETS (8 retweets)
-- ====================================
-- Nota: Los IDs pueden variar, estos son ejemplos asumiendo IDs secuenciales

-- marialopez retweetea tweets de juanperez
INSERT INTO tweets (tipo, texto, autor_username, fecha, tweet_original_id) VALUES
('RETWEET', '', 'marialopez', NOW(), 1),  -- Retweet del primer tweet de juanperez
('RETWEET', '', 'marialopez', NOW(), 5);  -- Retweet sobre tests unitarios

-- carlosgomez retweetea tweets de juanperez y marialopez
INSERT INTO tweets (tipo, texto, autor_username, fecha, tweet_original_id) VALUES
('RETWEET', '', 'carlosgomez', NOW(), 2),  -- Retweet sobre Spring Boot
('RETWEET', '', 'carlosgomez', NOW(), 20); -- Retweet de marialopez sobre records

-- anarodriguez retweetea tweets de varios usuarios
INSERT INTO tweets (tipo, texto, autor_username, fecha, tweet_original_id) VALUES
('RETWEET', '', 'anarodriguez', NOW(), 7),  -- Retweet sobre Spring Boot
('RETWEET', '', 'anarodriguez', NOW(), 35); -- Retweet de carlosgomez sobre SOLID

-- luismartinez retweetea tweets de otros usuarios
INSERT INTO tweets (tipo, texto, autor_username, fecha, tweet_original_id) VALUES
('RETWEET', '', 'luismartinez', NOW(), 25), -- Retweet de marialopez sobre validaciones
('RETWEET', '', 'luismartinez', NOW(), 50); -- Retweet de anarodriguez sobre React

-- ====================================
-- RESUMEN
-- ====================================
-- Total usuarios: 5
-- Total tweets originales: 80 (16 por usuario)
-- Total retweets: 8
-- Total registros en tweets: 88

