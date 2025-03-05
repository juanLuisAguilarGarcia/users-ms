# users-ms
service in charge management of users for double v patners  

## How to Run

This application is packaged as a jar which has Tomcat 8 embedded. No Tomcat or JBoss installation is necessary. You run it using the ```java -jar``` command.

* Clone this repository https://github.com/juanLuisAguilarGarcia/users-ms
* Make sure you are using JDK 1.17, Maven 3.6.3 and spring boot 3.3.4
* You can build the project and run the tests by running ```mvn clean package```
* Once successfully built, you can run the service by one of these two methods:
```
        java -jar -Dspring.profiles.active=test target/users-ms-1.0-SNAPSHOT.jar
or
        mvn spring-boot:run -Drun.arguments="spring.profiles.active=default"
```
* Check the stdout or boot_example.log file to make sure no exceptions are thrown

Once the application runs you should see something like this

```
2017-08-29 17:31:23.091  INFO 19387 --- [           main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat started on port(s): 8090 (http)
2017-08-29 17:31:23.097  INFO 19387 --- [           main] com.khoubyari.example.Application        : Started Application in 22.285 seconds (JVM running for 23.032)
```
## How to Create data base objects

run sql follow sql script:



## About the Service
 
Here are some endpoints you can call:

```
CREATE TABLE dvp.users (
    user_id  bigint AUTO_INCREMENT PRIMARY KEY NOT NULL,
    first_name varchar(100),
    last_name varchar(100), 
    create_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, 
    update_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL 
);  

ALTER TABLE dvp.users
ADD UNIQUE (first_name, last_name); 
```

### Get information about system health, configurations, etc.

``` 
http://localhost:8081/health
http://localhost:8081/info
http://localhost:8081/metrics
```

### Create a user resource

```
POST /users/api/v1/user
Accept: application/json
Content-Type: application/json

{ 
    "first_name": "juan",
    "last_name" : "aguilar" 
}

RESPONSE: HTTP 201 (Created) 
```

### Get user by id

```
GET /users/api/v1/user
Accept: application/json
Content-Type: application/json

Response: HTTP 200 
```

### Get all users  

```
GET /users/api/v1/user/1
Accept: application/json
Content-Type: application/json

Response: HTTP 200 
```

### Update a user resource

```
PUT /users/api/v1/user/1
Accept: application/json
Content-Type: application/json

{ 
    "first_name": "Jose",
    "last_name" : "Lema" 	
}

RESPONSE: HTTP 200 
``` 

### To view Swagger 2 API docs

Run the server and browse to /users/api/v1/swagger-ui/index.html

# About Spring Boot

Spring Boot is an "opinionated" application bootstrapping framework that makes it easy to create new RESTful services (among other types of applications). It provides many of the usual Spring facilities that can be configured easily usually without any XML. In addition to easy set up of Spring Controllers, Spring Data, etc. Spring Boot comes with the Actuator module that gives the application the following endpoints helpful in monitoring and operating the service:

**/metrics** Shows “metrics” information for the current application.

**/health** Shows application health information.

**/info** Displays arbitrary application info.

**/configprops** Displays a collated list of all @ConfigurationProperties.

**/mappings** Displays a collated list of all @RequestMapping paths.

**/beans** Displays a complete list of all the Spring Beans in your application.

**/env** Exposes properties from Spring’s ConfigurableEnvironment.

**/trace** Displays trace information (by default the last few HTTP requests).
 
# Questions and Comments: juanaguilargarcia20@gmail.com