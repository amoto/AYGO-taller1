# Logs Frontend

Frontend escrito en JavaScript usando ReactJS para interactuar con el API de [LoadBalancer](../LoadBalancer/README.md) para agregar logs y visualizar los últimos 10.

## Requisitos

Para ejecutar el proyecto es necesario:

- NodeJS v16.13.0

- NPM 8.1.0

- Docker

- Una instancia de [LoadBalancer](../LoadBalancer/README.md)

## Configuración

La URL del [LoadBalancer](../LoadBalancer/README.md) se encuentra en [App.js](src/App.js) en la linea 9 para cambiarla al host adecuado para la ejecución.

## Ejecución

Para ejecutar el proyecto dede un entorno unix es necesario ejecutar los siguientes comandos:

- Para compilar el projecto y empaquetar las dependencias en sus directorios correspondientes

> npm build

- Para construir la imagen de docker con el nombre *logfront*

> docker build --tag logfront .

- Para instanciar la imagen de docker a un contenedor con el nombre *logfrontinstance* y exponer el front por el puerto 80

> docker run -p 80:3000 --name logfrontinstance logfront
