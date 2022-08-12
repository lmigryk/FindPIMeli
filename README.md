# Find Number PI

## Descripción de la solución

Se necesita implementar una API que permita a través de peticiones HTTP  retornar la cantidad de decimales de número PI (π), si este no se encuentra en una cache, se debe hacer el calculo, de lo contrario, la cache debe devolver el registro. Es por esto que se realiza una investigación para encontrar la forma más eficiente para realizare esta búsqueda de decimales.

Existen varios métodos por los cuales se pueden calcular de forma aproximada los decimales de PI (π) y una de las más eficientes es a través de [La fórmula de BPP](https://mathworld.wolfram.com/BBPFormula.html), por lo que es la indicada para el desarrollo del proyecto.

El proyecto va a contener dos microservicios el primero será una api desarrollada en Java 11, bajo el alero de Springboot y una cache Distribuida de Redis. Ambos estarán dockerizadas y se iniciarán por medio del archivo docker-compose.yml

## Stack Tecnológico

La solución fue desarrollada con la siguiente tecnología:

* Java 11 -> Como lenguaje de programación

* SpringBoot -> Como framework de Java, el cual permite la creación de una API

* Redis -> Como memoria caché distribuida.

* Dockers -> Para el levantamiento y funcionamiento de la aplicación

Plugins relevantes de Springboot:

* Maven -> Como Configuración del proyecto en Springboot.

* Junit -> Para pruebas unitarias.

* Jacoco -> Para reportes de test.

* Swagger -> Para vizualizacion de endpoint y documentación del contrato de la aplicación.

Software externos:

* Postman -> Para probar el servicio a traves de llamadas http

* Apache jmatter -> Para pruebas de carga

## Consideraciones Generales

* La colección de postman de encuentra en la raiz del proyecto, al igual que el makefile, DockerFile y docker-compose.yml

* El algoritmo empleado es uno de los más certeros, puede varia al momento de aproximar en el último dígito del decimal obtenido.

* Para efectos de la programa, el número mínimo que debe ingresar el usario es 1.

* La configuración Redis es la por default, por lo que no posee usuario ni contraseña.

* Para entrar a swagger, dirigirse a [link](http://localhost:8089/api/v1/swagger-ui/)

> El proyecto debe estar levantado para poder ver swagger

* Para vizualizar porcentaje de coverage entregado por Jacoco, desde la carpeta raiz del proyecto dirigirse a ./target/site/jacoco/index.html y luego abrir el archivo index.html en un browser.

> El proyecto debe estar levantado para poder ver el porcentaje

## Estructura de directorios

A continuación se presenta la estructura del directorio principal, el cual sería src.

<pre><code>
├───main
│   ├───java
│   │   └───com
│   │       └───example
│   │           └───meli
│   │               ├───adapters
│   │               │   └───controllers
│   │               ├───commons
│   │               │   ├───constants
│   │               │   ├───dto
│   │               │   ├───exception
│   │               │   ├───utils
│   │               │   └───validator
│   │               ├───config
│   │               └───domain
│   │                   ├───models
│   │                   └───services
│   └───resources
└───test
    └───java
        └───com
            └───example
                └───meli
                    ├───commons
                    │   └───utils
                    ├───controller
                    └───domain
                        ├───model
                        └───services

</code></pre>

El proyecto se divide en 4 carpetas principales:

* Adapters: Aquí se encontrará los controladores de la aplicación.

* Commons: Se encuentran elementos que permitiran y ayudaran de manera transversala la aplicación a realizar sus tareas, dentro de estas tenemos Constantes, Dtos de Respuesta, Excepciones, Validadores y Utils.

* Config: Se encuentran configuraciones del proyecto, como por ejemplo la configuración de Swagger

* Domain: Se encuentran tanto los modelos como los servicios construidos.

## Diagrama de la solución

Para vizualizar el diagrama, dirigirse a este [link](https://drive.google.com/file/d/1EzXQ75O2VzY9N-TQLcjQDhW_zGDC5340/view?usp=sharing)

## Parámetros de configuración del microservicio

La url Base del proyecto iniciado es: <http://localhost:8089/api/v1/>

DockerFile de contenerdor de API
<pre><code>
FROM openjdk:11-jre-slim-buster
EXPOSE 8089
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
</code></pre>

docker-compose.yml para orquestar el contenedor de Redis y Api
<pre><code>

version: '3'
services:
  backend:
    environment:
      - MAX_RANDOM_PRECISION=25
      - REDIS_ENABLED=1
    build: .
    ports:
      - "8089:8089"
    links:
      - redis

  redis:
    image: redis
    container_name: redis
    ports:
        - 6379:6379

</code></pre>
Los puertos de nuestra aplicación para la API es en 8089 y para Redis 6379

Las variables de entorno MAX_RANDOM_PRECISION y REDIS_ENABLED se encuentran configurados en la sección <b>environment</b> de nuestro docker-compose.yml , estos están inicialmente seteados en 25 y 1 respectivamente.

De querer actualizar, cambiar los valores en docker-compose y levantar nuevamente los servicios.

<pre><code>docker compose up -d --build</code></pre>

## Intalación de microservicio dockerizados

### Forma Manual

En la raiz del repositorio, ejecutar el comando para poder generar el .jar del aplicativo, este será generado con el Maven embebido.

<pre><code>./mvnw clean install</code></pre>

Luego, para levantar los ambientes en docker, realizar siguiente comando

<pre><code>docker compose up -d --build</code></pre>

### Levantamiento con makefile

En la raiz del proyecto, correr el siguiente comando

<pre><code>make --file makefile.mk compile docker-run</code></pre>

o

<pre><code>make --file makefile.mk run-project</code></pre>

MakeFile construido para levantamiento
<pre><code>default:
 cat ./makefile.mk
compile:
 ./mvnw clean install
docker-run:
 docker compose up -d --build
run-project:
 compile docker-run </code></pre>

En caso de tener cualquier problema con el makefile, levantar el proyecto de forma manual.

## Test de performance

Se ha dejado un script con pruebas de cargar de las tres funciones requeridas. Para correr los test de carga:

Ingresar desde la raiz del proyecto a la carpeta k6 y ejecutar el siguiente comando:

Desde powershell de windows
<pre><code>cat test.js | docker run --network host --rm -i grafana/k6 run -</code></pre>

Desde consola Unix:
```
docker run --network host --rm -i grafana/k6 run -<test.js
```

Ejemplo de salida tras pasar Test

```
default ✓ [ 100% ] 0/2 VUs  22s

     ✗ is status 409
      ↳  0% — ✓ 143 / ✗ 16265
     ✓ is status 200

     checks.........................: 66.95% ✓ 32959       ✗ 16265
     data_received..................: 14 MB  627 kB/s
     data_sent......................: 5.5 MB 248 kB/s
     http_req_blocked...............: avg=2.51µs   min=641ns    med=1.02µs   max=1.11ms   p(90)=1.38µs   p(95)=1.8µs
     http_req_connecting............: avg=946ns    min=0s       med=0s       max=1.04ms   p(90)=0s       p(95)=0s
     http_req_duration..............: avg=571.68µs min=270.17µs med=475.42µs max=9.92ms   p(90)=860.71µs p(95)=904.64µs
       { expected_response:true }...: avg=572.03µs min=270.17µs med=475.68µs max=9.92ms   p(90)=860.81µs p(95)=904.68µs
     http_req_failed................: 0.29%  ✓ 143         ✗ 49081
     http_req_receiving.............: avg=28.25µs  min=-84295ns med=26.03µs  max=748.38µs p(90)=40.91µs  p(95)=47.82µs
     http_req_sending...............: avg=5.69µs   min=3.09µs   med=4.63µs   max=663.12µs p(90)=6.42µs   p(95)=9.66µs
     http_req_tls_handshaking.......: avg=0s       min=0s       med=0s       max=0s       p(90)=0s       p(95)=0s
     http_req_waiting...............: avg=537.73µs min=222.63µs med=442.55µs max=9.79ms   p(90)=826.7µs  p(95)=866.02µs
     http_reqs......................: 49224  2237.354448/s
     iteration_duration.............: avg=1.85ms   min=1.46ms   med=1.79ms   max=12.32ms  p(90)=2ms      p(95)=2.15ms
     iterations.....................: 16408  745.784816/s
     vus............................: 1      min=1         max=2
     vus_max........................: 2      min=2         max=2

```
## Seguridad de la API

> This block quote is here for your information
