# Load Balancer

Api REST escrito en java usando spark encargado de distribuir la carga en diferentes instancias con una estrategia de round robin de [Log Service](../LogService/README.md) manteniendo los endpoints y las firmas de éste.

El proyecto cuenta con 3 capas:

- Controladores: Encargados de manejar los requests http que recibe el servicio.

- Servicios: Encargados de manejar la lógica de negocio.

- Repositorios: Encargados de la interacción directa con los repositorios de datos (consultar, almacenar o actualizar los datos).

Y un sistema de injección de depencias encontrado en la clase [Injector](src/main/java/co/edu/escuelaing/loadbalancer/configs/Injector.java).

## Requisitos

Para ejecutar el proyecto es necesario:

- Java 8

- Maven v3.6.3

- Docker

- Al menos una instancia de [Log Service](../LogService/README.md)

## Configuración

Las configuraciones las recibe por medio de  las variables de entorno:

- PORT: El puerto por medio del cual queda expuesto el servidor http. Ejemplo: `PORT=6000`

- SERVER_POOL: El conjunto de URLs de [Log Service](../LogService/README.md) en las que se va a distribuir la carga separadas por `;`. Ejemplo: `SERVER_POOL=http://web1:6000;http://web2:6000;http://web3:6000`

## Ejecución

Para ejecutar el proyecto dede un entorno unix es necesario ejecutar los siguientes comandos:

- Para compilar el projecto y empaquetar las dependencias en sus directorios correspondientes

> mvn package

- Para construir la imagen de docker con el nombre *loadbalancer*

> docker build --tag loadbalancer .

- Para instanciar la imagen de docker a un contenedor con el nombre *loadbalancerinstance* y exponer el api por el puerto 8080

> docker run -p 8080:6000 --name loadbalancerinstance loadbalancer
