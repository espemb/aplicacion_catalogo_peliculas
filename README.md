# Sistema de Gestión de Películas y Directores

## Descripción

Este proyecto es un sistema de gestión de películas y directores implementado en Java. 
Permite realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) para manejar la información de películas y directores en una base de datos. 
El sistema está diseñado para ser flexible y fácil de usar, proporcionando una interfaz robusta para la administración de datos relacionados con el cine.

## Funcionalidades

- **Gestión de Películas**:
  - Insertar nuevas películas.
  - Consultar todas las películas, ordenadas por diferentes criterios.
  - Buscar una película por ID o título.
  - Modificar detalles de una película existente.
  - Eliminar una película.

- **Gestión de Directores**:
  - Insertar nuevos directores.
  - Consultar todos los directores.
  - Buscar un director por ID o nombre.
  - Modificar detalles de un director existente.
  - Eliminar un director.

## Tecnologías Utilizadas

- **Java**: Lenguaje de programación principal.
- **JDBC**: Para la conexión y operación con bases de datos.
- **SQLite**: Sistema de gestión de bases de datos embebido.

## Requisitos

- **Java Development Kit (JDK)**: Versión 8 o superior.
- **SQLite**: El archivo de base de datos SQLite debe estar disponible y accesible.
- **Conexión a Internet**: Para descargar dependencias o conectarse a recursos externos, si es necesario.

## Instalación

1. Clona el repositorio:
    ```sh
    git clone <[URL_DEL_REPOSITORIO](https://github.com/espemb/aplicacion_catalogo_peliculas.git)>
    ```

2. Configura la base de datos:
    - Asegúrate de tener un archivo de base de datos SQLite.
    - Importa el esquema de la base de datos utilizando una herramienta como [DB Browser for SQLite](https://sqlitebrowser.org/) o ejecutando el script SQL adecuado.

3. Configura el archivo de conexión:
    - Edita el archivo `Utilidades.java` para proporcionar la ruta al archivo de base de datos SQLite.

4. Compila y ejecuta el proyecto:
    ```sh
    javac -d bin src/modelo/*.java
    java -cp bin src.modelo.Main
    ```

## Uso

- **Insertar**: Utiliza los métodos `insertarPelicula` e `insertarDirector` para agregar nuevas entradas.
- **Consultar**: Utiliza `dameTodas` para listar todos los registros.
- **Buscar**: Utiliza `buscaPorId` o `buscaPorTitulo` (para películas) y `buscaPorNombre` (para directores) para obtener registros específicos.
- **Modificar**: Utiliza `modificaPelicula` y `modificaDirector` para actualizar información existente.
- **Eliminar**: Utiliza `borraPorId` para eliminar registros por ID.
