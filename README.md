## --- NETOLOGY HOMEWORK ---
### WEB, SPRING & SPRING MVC --> Java EE, Java Servlets


#### --> Task 1 --> Spring Web MVC

# Spring MVC Web Application

This project is a Spring MVC web application that demonstrates CRUD operations on `Post` entities using an in-memory storage with `ConcurrentHashMap`. It's structured with Spring's best practices, using annotations and Java Config for Spring MVC setup without `web.xml`.

## Features

- CRUD operations for `Post` entities.
- RESTful service endpoints.
- In-memory storage of posts.
- Exception handling for resource not found scenarios.

### Prerequisites

- JDK 11 or later.
- Maven 3.6 or later.

### Endpoints
The application defines the following RESTful endpoints:

- GET /api/posts - Retrieve all posts.
- GET /api/posts/{id} - Retrieve a post by its ID.
- POST /api/posts - Create a new post. The request body must contain the post content in JSON format.
- DELETE /api/posts/{id} - Delete a post by its ID.

### Built With
- Spring MVC - The web framework used.
- Maven - Dependency Management.
- Gson - Used to convert Java Objects into their JSON representation.
  
### Authors
  Emrah Hakan AGAN - Initial work