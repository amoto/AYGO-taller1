# Log Service

Api REST escrito en java usando spark encargado de almacenar y consultar logs en una base de datos no relacional MongoDB.

El proyecto cuenta con 3 capas:

- Controladores: Encargados de manejar los requests http que recibe el servicio.

- Servicios: Encargados de manejar la lógica de negocio.

- Repositorios: Encargados de la interacción directa con los repositorios de datos (consultar, almacenar o actualizar los datos).

Y un sistema de injección de depencias encontrado en la clase [Injector](src/main/java/co/edu/escuelaing/logservice/configs/Injector.java).

## Requisitos

Para ejecutar el proyecto es necesario:

- Java 8

- Maven v3.6.3

- Docker

- MongoDB

## Configuración

Las configuraciones las recibe por medio de  las variables de entorno:

- PORT: El puerto por medio del cual queda expuesto el servidor http. Ejemplo: `PORT=6000`

- MONGO_URI: URI de la base de datos MongoDB. Ejemplo: `MONGO_URI=mongodb://localhost:27017`

## Ejecución

Para ejecutar el proyecto dede un entorno unix es necesario ejecutar los siguientes comandos:

- Para compilar el projecto y empaquetar las dependencias en sus directorios correspondientes

> mvn package

- Para construir la imagen de docker con el nombre *logservice*

> docker build --tag logservice .

- Para instanciar la imagen de docker a un contenedor con el nombre *logserviceinstance* y exponer el api por el puerto 8080

> docker run -p 8080:6000 --name logserviceinstance logservice
