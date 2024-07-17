# ForoHub

## Descripción
ForoHub es un proyecto backend desarrollado con Spring Boot que simula un foro en línea. En este foro, los usuarios pueden interactuar planteando preguntas y discutiendo sobre diversos tópicos. La API soporta operaciones CRUD sobre los tópicos y emplea JWT para la autenticación de los usuarios, asegurando que solo los usuarios autenticados puedan realizar ciertas operaciones.

## Funcionalidades
- **CRUD de Tópicos:** Los usuarios pueden crear, leer, actualizar y eliminar tópicos.
- **Autenticación de Usuarios:** Se implementa mediante tokens JWT para proteger las rutas y asegurar que solo usuarios autenticados interactúen con la API.
- **Control de Acceso:** Dependiendo del token proporcionado, se permite o deniega el acceso a las diferentes operaciones.
