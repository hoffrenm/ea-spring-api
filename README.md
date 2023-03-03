# Movie Characters API

Movie Characters API was completed for the third Java assignment in the Noroff Accelerate backend program. 
The project was made by Mika Hoffren and Aino Ylä-Outinen.

## How to run

After cloning the project there are multiple ways to get project running.

### With Docker

Use supplied `docker-compose.yaml` file. You will need `docker-compose` on your machine.

Execute following at the project root directory

- `docker-compose up`

Everything is executed within containers. You might want to run `docker-compose down` after you are done.

### With IDE / terminal

For these you will need to have Postgres instance running on your local machine e.g. database at `localhost:5432/movieDB` needs to exists. For example this can be done with PgAdmin tool.

- You can execute the project with most IDEs. Please note that the project utilizes maven.

Or if you prefer terminal

- `mvn clean install -DskipTests`
- `mvn exec:java -Dexec.mainClass=com.example.easpringapi.EaSpringApiApplication`

### API endpoints 

In all cases the project will launch to `localhost:8080`. You can check api endpoints at http://localhost:8080/swagger-ui.html

## Objective and Requirements

The main objective of the project was to create a PostgreSQL database using Hibernate and expose it through a deployed Web API.
All of the functional requirements are met, and each test has been passed.

### Tools
  - IntelliJ with Java 17
  - Spring Web, Spring Data JPA, PostgreSQL, Lombok
  - PostgreSQL with PgAdmin
  - Docker 

### Business Rules

  - One movie contains many characters, and a character can play in multiple movies
  - One movie belongs to one franchise, but a franchise can contain many movies

### API Requirements

Full CRUD functionality in Movies, Characters, and Franchises. Additionally:

  - Updating movies in a franchise
  - Updating characters in a movie
  - Get all the movies in a franchise
  - Get all the characters in a movie
  - Get all the characters in a franchise

### Documentation and Deployment

Documentation was made by using Swagger and Open API. Application was built by using Docker.






