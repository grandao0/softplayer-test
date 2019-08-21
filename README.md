# README #

### What is this repository for? ###

Quick summary

	+ Build a RESTful service to perform CRUD operations on a Client resource.
	+ Build API classes to perform operations such as:
		- List, create, update and delete Clients.

* Version 1.0a

### How do I get set up? ###

* Summary of set up:

	+ Docker is used to build and start the application
	+ An embedded in-memory database is used: H2
	+ The database and table creation is done by the application
	+ To use the Basic Authentication for the endpoints and to log in the swagger-ui the username is admin and the password is softplayer **IMPORTANT!**

* Maven POM Configuration

* Packaging:

	+ jar

* Parent:

	+ spring-boot-starter-parent
	+ version 2.1.7.RELEASE

* Dependencies:

	+ spring-boot-starter-actuator
	+ spring-boot-starter-data-rest
	+ spring-boot-starter-data-jpa
	+ spring-boot-starter-hateoas
	+ spring-boot-starter-jetty
	+ spring-boot-starter-test
	+ spring-boot-starter-web
	+ spring-boot-devtools
	+ springfox-swagger2
	+ springfox-swagger-ui
	+ h2
	+ lombok
	+ javax.inject

* Plugins:

	+ build-helper-maven-plugin
	+ dockerfile-maven-plugin
	+ maven-dependency-plugin
	+ spring-boot-maven-plugin
	+ swagger-codegen-maven-plugin

* Database configuration:

	+ H2 in-memory database

* Example of calls:

	+ After the application is running, open the browser and type http://localhost:8080/api/softplan/v1/swagger-ui.html
	+ You will be prompted a user and password. The username is admin and the password is softplayer
	+ Swagger UI let's you make calls easily by choosing the desired Operations for each Entity in the most intuitive and easy way
	+ Choose the Client Resource for Client operations
	+ Choose the Source Resource for source URL

* Example of calls with CURL (https://curl.haxx.se):

		+ Creating a Client with CURL:
			curl -X POST "http://localhost:8080/api/softplan/v1/clientes?cpf=123.456.789-18
			&dataNascimento=2010-10-10&email=cassio.babilonia%40gmail.com&nacionalidade=Brasil
			&naturalidade=Patos%20de%20Minas&nome=Cassio%20Player&sexo=M" -H "accept: */*"

		+ Updating a Client with CURL:
			curl -X PUT --header 'Content-Type: application/json' --header 'Accept: application/json' -d '{ \ 
			"description": "Gamer Mouse", \ 
			"name": "Mouse", \ 
			"parent": { "id": 2} \ 
			}' 'http://localhost:9091/clientes/1'

		+ Deleting a Client with CURL:
			curl -X DELETE "http://localhost:8080/api/softplan/v1/clientes/1" -H "accept: */*"

		+ Getting all Clients with CURL:
			curl -X GET "http://localhost:8080/api/softplan/v1/clientes" -H "accept: */*"

		+ Getting a Client by ID with CURL:
			curl -X GET "http://localhost:8080/api/softplan/v1/clientes/1" -H "accept: */*"

### Contribution guidelines ###

* Writing tests
* Code review
* Other guidelines

### Who do I talk to? ###

* Repository owner and admin

	cassio.babilonia at gmail.com
