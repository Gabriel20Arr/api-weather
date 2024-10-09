# Weather API - Technical Test for Backend Developer

## Descripcion del proyecto

Este proyecto constituye una API que proporciona datos meteorológicos mediante la integración con [OpenWeatherMap](https://openweathermap.org/). Su funcionalidad incluye la obtención de información meteorológica en tiempo real y pronósticos para ciudades específicas. Destaca por las siguientes características:

- Cache de Respuestas: Implementa un sistema de caché para optimizar el rendimiento, permitiendo respuestas más rápidas al almacenar y recuperar datos previamente solicitados. Esto mejora la experiencia del usuario al reducir los tiempos de espera para consultas recurrentes.

- Limitación de Peticiones: Se ha establecido un límite de 5 peticiones por minuto para gestionar el acceso y garantizar un uso responsable de la API. Esta medida contribuye a mantener la estabilidad del servicio y prevenir posibles abusos.

- Integración con OpenWeatherMap: Utiliza la plataforma OpenWeatherMap para garantizar la calidad y precisión de los datos meteorológicos proporcionados. Esta integración ofrece una amplia gama de información, desde la temperatura actual hasta pronósticos a futuro.

## Requisitos Previos

### Asegurarse de tener instalado:

- [Docker](https://www.docker.com/products/docker-desktop/)
- [Java Development(JDK)](https://www.oracle.com/ar/java/technologies/downloads/)
- [Postman](https://www.postman.com/downloads/)

## Endpoints Disponibles

#### La aplicacion ofrece los siguientes endpoints para la gestion de usuario , autenticacion y peticiones a la API de Weather

## Registro de Usuario (POST)

### Ruta: `/api/auth/nuevo`

**Ejemplo de Cuerpo de Solicitud:**

```json
{
  "nombre": "nombre",
  "nombreUsuario": "nombreUsuario",
  "email": "email@Example.com",
  "password": "contraseña",
  "roles": ["ROLE_ADMIN, ROLE_USER"]
};
```

## Autenticacion de Usuarios(POST)

### Ruta:`/api/auth/login`

**Ejemplo de Cuerpo de Solicitud:**

```json
{
  "nombreUsuario": "nombreUsuario",
  "password": "contraseña",
};
```

**Respuesta Exitosa:**

```json
{
  "token": "TuTokenDeAcceso"
};
```

## Obtener Informacion Meteorológica Actual (GET)

### Ruta: `/api/weather/current`

### Parametro de consulta:

- `cityName`: Nombre de la ciudad para la cual se desea obtener la informacion meteorológica.

### Respuesta Exitosa:

```json
{
  "name": "string",
  "coord": {
    "lon": 0,
    "lat": 0
  },
  "weather": [
    {
      "main": "string",
      "description": "string",
      "icon": "string"
    }
  ],
  "base": "string",
  "main": {
    "temp": 0,
    "humidity": 0,
    "temp_min": 0,
    "temp_max": 0
  },
  "wind": {
    "speed": 0,
    "deg": 0
  },
  "dt": 0,
  "sys": {
    "country": "string",
    "sunrise": 0,
    "sunset": 0
  }
}
```

## Obtener Pronostico del Tiempo de 5 días para una Ciudad Designada(GET)

### Ruta: `/api/weather/forecast`

### Parametro de consulta:

- `Longitud y Latitud`: Longitud y Latitud de la ciudad para la cual se desea obtener el clima.

### Respuesta Exitosa:

```json
 {
  "city": {
    "name": "string",
    "country": "string"
  },
  "message": 0,
  "cnt": 0,
  "list": [
    {
      "dt": 0,
      "main": {
        "temp": 0,
        "feels_like": 0,
        "temp_min": 0,
        "temp_max": 0,
        "humidity": 0
      },
      "weather": [
        {
          "id": 0,
          "main": "string",
          "description": "string",
          "icon": "string"
        }
      ],
      "wind": {
        "speed": 0,
        "deg": 0,
        "gust": 0
      },
      "visibility": 0,
      "pop": 0,
      "sys": {
        "pod": "string",
        "change": "string"
      },
      "dt_txt": "string"
    }
  ]
}
```

## Acceder a datos de contaminación del aire actual para una ciudad seleccionada (GET)

### Ruta: `/api/weather/air-pollution`

### Parametro de consulta:

- `Longitud y Latitud`: Longitud y Latitud de la ciudad para la cual se desea obtener la informacion actual de la contaminacion del aire.

### Respuesta Exitosa:

```json
{
  "coord": {
    "lon": 0,
    "lat": 0
  },
  "list": [
    {
      "main": {
        "aqi": 0
      },
      "components": {
        "co": 0,
        "no": 0,
        "no2": 0,
        "o3": 0,
        "so2": 0,
        "pm2_5": 0,
        "pm10": 0,
        "nh3": 0
      },
      "dt": 0
    }
  ]
}
```

## Documentación y Exploración con Swagger

Esta API está documentada y se puede explorar fácilmente utilizando Swagger, una herramienta de visualización interactiva para APIs. Swagger proporciona una interfaz gráfica que simplifica la comprensión de los endpoints disponibles, sus parámetros y las respuestas esperadas.

## Acceder a Swagger

- Iniciar la Aplicación: Asegúrate de que la aplicación esté en ejecución según las instrucciones proporcionadas anteriormente.

- Acceder a Swagger UI: Abre tu navegador web y navega a la siguiente URL: http://localhost:8080/swagger-ui.html?

## Interacción con Swagger

Una vez en Swagger UI, podrás explorar y probar los diferentes endpoints de la API de manera interactiva. Esto incluye la ejecución de solicitudes, visualización de respuestas y comprensión detallada de los parámetros requeridos.

## Pasos para Levantar el Proyecto

**Sigue estos pasos para levantar la aplicacion Sprint Boot y ejecutarla:**

#### Correr la aplicaciocion manualmente:

```bash
# Para construir y empaquetar la aplicación (omitir pruebas)
./mvnw clean package -DskipTests

# Para construir los contenedores de Docker
docker-compose build

# Para iniciar la aplicación y los servicios relacionados en Docker
docker-compose up
```

**Asegúrate de estar en el directorio raíz de tu proyecto Spring Boot al ejecutar estos comandos. Con estos comandos, podrás automatizar fácilmente la construcción y ejecución de tu aplicación Dockerizada.**