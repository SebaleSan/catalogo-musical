# Music API

API REST de un catálogo de álbumes con Spring Boot, JPA y Oracle.

## Configuración

La conexión se define con variables de entorno:

| Variable | Descripción |
|---|---|
| `DB_URL` | URL JDBC (por defecto apunta a Oracle Cloud con el wallet en `./wallet`) |
| `DB_USER` | Usuario de la base de datos (por defecto `MUSIC`) |
| `DB_PASSWORD` | Contraseña (obligatoria) |

Ejemplo con una base de datos local:

```powershell
$env:DB_URL = 'jdbc:oracle:thin:@//localhost:1521/XEPDB1'
$env:DB_USER = 'DP_APP'
$env:DB_PASSWORD = 'clave'
.\mvnw.cmd spring-boot:run
```

## Base de datos

No hay que crear nada a mano. Al arrancar, Hibernate crea la tabla `ALBUM` (`ddl-auto=create`) y `data.sql` la llena con 20 álbumes de ejemplo. Los datos se reinician en cada ejecución.

El usuario solo necesita permisos para crear tablas.
