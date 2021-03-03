# Mutants API for Magneto

## Description
This Api is used to provide a Dna analyzer for finding mutants by given Dna sequences
Also collects metrics and give statistics.

## Deployment

The Application was deployed on Heroku under the next url https://mutantsmal.herokuapp.com/
The application was deployed on Heroku as a simple Java application using a postgres Addon to provide the Database
since this is provided by Heroku credentials and database domain may expire, and have to be changed on the config file.
### Api docs are shown on Swagger: https://mutantsmal.herokuapp.com/swagger-ui.html



## Running tests
To run automated tests just type:
```
mvn test
```

# Deploying instructions

Since this is a Maven project package can be done with 
```
mvn package
```
and deploy the jar on the desired environment, also a Docker image can be build with the Docker file in order to deploy it on a docker stack, providing the application.yml 
pointing where the postgres db url is deployed, also in the magnetodb folder is a DockerFile and a Dockeer-compose in order to
deploy a psotgres db with the database schema and role configured that is needed for the application, if this is done make sure that conainers share the network in order to 
mutants api can reach the postgres db.
## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
* Java 11

## Authors
-  [Oscar Pinto](https://github.com/TheBaphomet666)
