# Taller 1 AYGO

Por: Julian David Devia Serna.

## Requisitos

- Java 8

- Maven v3.6.3

- NodeJS v16.13.0

- NPM 8.1.0

- Docker

- Docker Compose

## Proyectos

En este taller se construyeron 3 proyectos que se ejecutan desde imagenes docker para su despliegue en la nube.

[Log Service](LogService/README.md)

[Load Balancer](LoadBalancer/README.md)

[Frontend](log_front/README.md)

## Configuración de Imagenes Docker en [DockerHub](https://hub.docker.com/)

- Acceder a [docker hub](https://hub.docker.com).

![docker hub](images/docker/docker_hub.png)

- Crear el repositorio.

![crear repositorio](images/docker/create_repo.png)

- Construir imagen de docker del proyecto deseado. Por ejemplo para [Log Service](LogService/README.md) se crea la imagen `logservice`.

![docker build](images/docker/docker_build.png)

- Crear tag para la imagen de docker. Por ejemplo para [Log Service](LogService/README.md) se crea el tag usando imagen `logservice` para el repositorio `juliandevia/aygotaller1logservice` en el tag `latest`.

![docker tag](images/docker/docker_tag.png)

- Pushear el tag de la imagen al repositorio en docker hub. En nuestro ejemplo, se hace push del tag para el repo `juliandevia/aygotaller1logservice` con el tag `latest`.

![docker push](images/docker/docker_push.png)

## Despliegue en AWS

- Crear una instancia de EC2

![ec2](images/aws/ec2.png)

- Abrir puertos 80 y 8090 en el security group de la instancia

![security_group](images/aws/security_group.png)

- Acceder por medio de SSH

![ssh](images/aws/ssh.png)

- Actualizar los paquetes del sistema operativo

![update](images/aws/update.png)

- Instalar Docker

![docker_1](images/aws/docker.png)

![docker_2](images/aws/docker_2.png)

![docker_3](images/aws/docker_3.png)

- Instalar docker-compose

![docker-compose_1](images/aws/docker-compose.png)

![docker-compose_2](images/aws/docker-compose_2.png)

- Copiar el archivo [`docker-compose.yml`](docker-compose.yml)

![scp](images/aws/scp.png)

- Ejecutar el archivo docker-compose

![docker-compose_up_1](images/aws/docker-compose_up.png)

![docker-compose_up_2](images/aws/docker-compose_up_2.png)

- Acceder a el DNS público de la instancia para ver el frontend

![front](images/aws/front.png)

- Almacenar un log esceribiendolo en el campo de texto y oprimiendo el botón

![front_2](images/aws/front_2.png)

![front_3](images/aws/front_3.png)

- Si se desea, se puede visualizar en los logs de [Load Balancer](LoadBalancer/README.md) que balancea los request que recibe en las 3 instancias de [Log Service](LogService/README.md) que el docker-compose levanta.

![balancer](images/aws/balancer.png)
