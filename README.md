# mutants
## La app está desplegada en https://mutantsmal.herokuapp.com/
## e.g https://mutantsmal.herokuapp.com/stats
## Swagger https://mutantsmal.herokuapp.com/swagger-ui.html

### este es un proyecto de maven, la paquetería y las pruebas se pueden correr con los commandos de mvn.

# Instrucciones de despliegue

## Se puede desplegar en local o en un stack de Docker con el DockerFile
## de la aplicacion cambiando la configuración del application.yml a apuntar a la DB de postgres
## o bien provisionandole uno nuevo

##  En el directorio magnetodb se puede desplegar un contenedor con la base de datos de postgres
## ya configurada con el schema y la tabla necesaria además del role

# El despliegue en heroku se hizo directo desplegando una app de java y usando un addon
# de postgres para la DB esas configuraciones se pusieron directamente en el application.yml
# para temas de facilidad pero este debe ser aplicado por ambiente.


