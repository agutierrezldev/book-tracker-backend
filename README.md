# Backend de Book-Tracker

Este es el backend para **Book-Tracker**, un sistema basado en microservicios que gestiona libros, autores y préstamos. Está construido utilizando **Spring Boot**, con componentes como API Gateway, Discovery Server y Config Server.

## Arquitectura

La arquitectura de **Book-Tracker** está compuesta por los siguientes servicios:

- **API Gateway**: Gestiona todas las solicitudes entrantes hacia los microservicios.
- **Discovery Server (Eureka)**: Permite el registro y descubrimiento de microservicios.
- **Config Server**: Proporciona configuración centralizada para todos los microservicios.
- **Microservicios**: Servicios encargados de la gestión de libros, autores, y préstamos.

## Componentes

- **API Gateway (cloud-gateway)**: Redirige el tráfico hacia los microservicios correspondientes.
- **Discovery Server (discovery-server)**: Registra los microservicios y permite la comunicación entre ellos.
- **Config Server (config-server)**: Gestiona la configuración de todos los microservicios desde un único repositorio centralizado.
- **Book tracker service (book-tracker-service)**: Gestiona libros, autores y prestamos.
![Swagger Dashboard](/images/swagger-dashboard.png)
![Swagger Libros](/images/swagger-libros.png)
![Swagger Libros](/images/swagger-autores.png)
![Swagger Libros](/images/swagger-prestamos.png)


## Instalación

1. **Clonar el repositorio**

   Clona el repositorio en tu máquina local:

   ```bash
   git clone https://github.com/agutierrezldev/book-tracker-backend.git
   cd book-tracker

2. **discovery-server**

    ```bash
    cd discovery-server
    mvn clean install
    mvn mvn spring-boot:run

3. **config-server**

    Es importante tener acceso al repositorio de perfiles donde se encuentran configuraciones de los microservicios: ```https://github.com/agutierrezldev/book-tracker-profile```  branch ```develop```

    ```bash
    cd config-server
    mvn clean install
    mvn mvn spring-boot:run

4. **cloud-gateway**

    ```bash
    cd cloud-gateway
    mvn clean install
    mvn mvn spring-boot:run

5. **book-tracker-service**

    ```bash
    cd book-tracker-service
    mvn clean install
    mvn mvn spring-boot:run

## IMPORTANTE

  En el siguiente repositorio: ```https://github.com/agutierrezldev/book-tracker-profile```  branch ```develop```
  Se va a encontrar la dockerizacion de los servicios utilizados ```base de datos en Oracle``` cd /docker/


