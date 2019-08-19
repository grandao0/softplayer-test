# README #

### What is this repository for? ###

Quick summary

	+ Build a RESTful service using JAX-RS to perform CRUD operations on a Client resource.
	+ Build API classes to perform operations such as:
		- List, create, update and delete Clients
	+ Build JPA/Hibernate classes using annotations to persist objects in database

* Version 1.0a

### How do I get set up? ###

* Summary of set up:

	+ Docker must be used to build and start the application
	+ The application must have a stateless API and use a database to store data
	+ An embedded in-memory database is used: H2
	+ The database and tables creation should be done by the application

* Maven POM Configuration

* Packaging:

	- jar


* Parent:

	- spring-boot-starter-parent
	- version 2.1.7.RELEASE


* Dependencies:

    - spring-boot-starter-actuator
	- spring-boot-starter-data-rest
	- spring-boot-starter-data-jpa
	- spring-boot-starter-jetty
	- spring-boot-starter-web
	- spring-boot-starter-test
	- spring-boot-devtools
	- h2
	- springfox-swagger2
	- springfox-swagger-ui
	- lombok
	- commons-lang3
	- javax.inject


* Plugins:

	- spring-boot-maven-plugin
	- build-helper-maven-plugin
	- swagger-codegen-maven-plugin


* Database configuration:

	+ H2 in-memory database


* How to run tests:

	+ Before running the tests, choose the test profile by changing the value of spring.profiles.active in the application.properties file to test or by via JVM system parameter: -Dspring.profiles.active=test
	+ The tests must be started with the Maven command: mvn test


* How to compile and run from a jar file:

	+ Before compiling the application, choose the desired profile (dev / prod) by changing the value of spring.profiles.active in the application.properties file or by via JVM system parameter: -Dspring.profiles.active=dev/prod
	+ To compile the application in a .jar file, use the Maven command: mvn package
	+ An executable jar named ac-test-1.0a.jar will be created in the /target root folder of the project
	+ To run the application, use the Java command: java -jar ac-test-1.0a.jar in the file mentioned above


* How to run the application directly through Maven:

	+ Before running the application, choose the desired profile (dev / prod) by changing the value of spring.profiles.active in the application.properties file or by via JVM system parameter: -Dspring.profiles.active=dev/prod
	+ The application must start with a Maven command: mvn spring-boot:run


* Example of calls:

	+ After the application is running, open the browser and type http://localhost:9091/swagger-ui.html for dev profile or http://localhost:9090/swagger-ui.html for prod profile
	+ Swagger UI let's you make calls easily by choosing the desired Operations for each Entity in the most intuitive and easy way
	+ Choose the Client Resource for Client operations


* Example of calls with CURL (https://curl.haxx.se):
	Product calls:
		+ Creating a Client with CURL:
			- curl -X POST --header 'Content-Type: application/json' --header 'Accept: application/json' -d '{ \ 
			"description": "Gamer Mouse Cable", \ 
			"name": "Mouse Cable", \ 
			"parent": { "id": 1 } \ 
			}' 'http://localhost:9091/clientes'

		+ Updating a Client with CURL:
			- curl -X PUT --header 'Content-Type: application/json' --header 'Accept: application/json' -d '{ \ 
			"description": "Gamer Mouse", \ 
			"name": "Mouse", \ 
			"parent": { "id": 2} \ 
			}' 'http://localhost:9091/clientes/1'

		+ Deleting a Client with CURL:
			- curl -X DELETE --header 'Accept: application/json' 'http://localhost:9091/clientes/4'

		+ Get all Clients excluding relationships with CURL:
			curl -X GET \
              http://localhost:8081/api/softplan/v1/clientes \
              -H 'Postman-Token: d4b54a99-b911-40b2-898e-478307450a4b' \
              -H 'cache-control: no-cache'


### Contribution guidelines ###

* Writing tests
* Code review
* Other guidelines

### Who do I talk to? ###

* Repository owner and admin

	cassio.babilonia at gmail.com
