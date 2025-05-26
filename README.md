# Course Management API

A RESTful API built with Spring Boot for managing courses. This application provides CRUD (Create, Read, Update, Delete) operations for course management with JPA/Hibernate integration.

## Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Configuration](#configuration)
- [API Endpoints](#api-endpoints)
- [Request/Response Examples](#requestresponse-examples)
- [Error Handling](#error-handling)
- [Database Schema](#database-schema)
- [Testing](#testing)
- [Contributing](#contributing)

## Features

- RESTful API design
- CRUD operations for course management
- JPA/Hibernate integration for database operations
- Spring Boot auto-configuration
- Exception handling for robust error responses
- Clean architecture with separation of concerns

## Technologies Used

- **Java 17+**
- **Spring Boot 3.x**
- **Spring Data JPA**
- **Hibernate**
- **Jakarta Persistence API**
- **Maven** (for dependency management)
- **H2/MySQL/PostgreSQL** (database - configurable)

## Project Structure

```
src/main/java/com/javaspringboot/JavaSpringBoot/
├── controller/
│   └── MyController.java          # REST Controller
├── entities/
│   └── Course.java               # JPA Entity
├── services/
│   ├── CourseService.java        # Service Interface
│   └── CourseServiceImpl.java    # Service Implementation
└── dao/
    └── CourseDao.java            # JPA Repository (referenced)
```

## Prerequisites

- Java 17 or higher
- Maven 3.6+
- Your preferred IDE (IntelliJ IDEA, Eclipse, VS Code)
- Database (H2 for development, MySQL/PostgreSQL for production)

## Installation

1. **Clone the repository:**
   ```bash
   git clone <repository-url>
   cd JavaSpringBoot
   ```

2. **Build the project:**
   ```bash
   mvn clean install
   ```

3. **Run the application:**
   ```bash
   mvn spring-boot:run
   ```

4. **Access the application:**
   - Base URL: `http://localhost:8080`
   - Health check: `http://localhost:8080/home`

## Configuration

### Database Configuration

Add the following properties to your `application.properties` file:

#### For H2 Database (Development):
```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.h2.console.enabled=true
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=create-drop
```

#### For MySQL (Production):
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/coursedb
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

## API Endpoints

| Method | Endpoint | Description | Request Body | Response |
|--------|----------|-------------|--------------|----------|
| GET | `/home` | Welcome message | None | String |
| GET | `/courses` | Get all courses | None | List of Course objects |
| GET | `/courses/{courseId}` | Get course by ID | None | Course object |
| POST | `/courses` | Create new course | Course JSON | Created Course object |
| PUT | `/courses` | Update existing course | Course JSON | Updated Course object |
| DELETE | `/courses/{courseId}` | Delete course by ID | None | HTTP Status |

## Request/Response Examples

### 1. Get Welcome Message
```http
GET /home
```
**Response:**
```
Welcome Home!
```

### 2. Get All Courses
```http
GET /courses
```
**Response:**
```json
[
  {
    "id": 1,
    "title": "Spring Boot Fundamentals",
    "description": "Learn the basics of Spring Boot framework"
  },
  {
    "id": 2,
    "title": "REST API Development",
    "description": "Build RESTful APIs with Spring Boot"
  }
]
```

### 3. Get Course by ID
```http
GET /courses/1
```
**Response:**
```json
{
  "id": 1,
  "title": "Spring Boot Fundamentals",
  "description": "Learn the basics of Spring Boot framework"
}
```

### 4. Create New Course
```http
POST /courses
Content-Type: application/json

{
  "id": 3,
  "title": "Database Integration",
  "description": "Learn JPA and Hibernate with Spring Boot"
}
```
**Response:**
```json
{
  "id": 3,
  "title": "Database Integration",
  "description": "Learn JPA and Hibernate with Spring Boot"
}
```

### 5. Update Course
```http
PUT /courses
Content-Type: application/json

{
  "id": 1,
  "title": "Advanced Spring Boot",
  "description": "Advanced concepts in Spring Boot development"
}
```
**Response:**
```json
{
  "id": 1,
  "title": "Advanced Spring Boot",
  "description": "Advanced concepts in Spring Boot development"
}
```

### 6. Delete Course
```http
DELETE /courses/1
```
**Response:**
- **Success:** HTTP 200 OK
- **Error:** HTTP 500 Internal Server Error

## Error Handling

The API includes basic error handling:

### Delete Course Errors
- **404 Not Found:** When trying to delete a non-existent course
- **500 Internal Server Error:** For any other server-side errors

### Common HTTP Status Codes
- **200 OK:** Successful GET, PUT, DELETE operations
- **201 Created:** Successful POST operations
- **404 Not Found:** Resource not found
- **500 Internal Server Error:** Server-side errors

## Database Schema

### Course Entity

| Column | Type | Constraints |
|--------|------|-------------|
| id | BIGINT | Primary Key |
| title | VARCHAR(255) | - |
| description | TEXT | - |

**Entity Relationships:**
- Currently, the Course entity is standalone with no relationships to other entities.

## Testing

### Using cURL

#### Get all courses:
```bash
curl -X GET http://localhost:8080/courses
```

#### Create a new course:
```bash
curl -X POST http://localhost:8080/courses \
  -H "Content-Type: application/json" \
  -d '{
    "id": 4,
    "title": "Microservices with Spring Boot",
    "description": "Building microservices architecture"
  }'
```

#### Update a course:
```bash
curl -X PUT http://localhost:8080/courses \
  -H "Content-Type: application/json" \
  -d '{
    "id": 4,
    "title": "Advanced Microservices",
    "description": "Advanced microservices patterns and practices"
  }'
```

#### Delete a course:
```bash
curl -X DELETE http://localhost:8080/courses/4
```

### Using Postman

1. Import the API endpoints into Postman
2. Set the base URL to `http://localhost:8080`
3. Configure appropriate headers for POST/PUT requests:
   - `Content-Type: application/json`
4. Test each endpoint with sample data

## Architecture Notes

### Service Layer Pattern
The application follows the Service Layer pattern:
- **Controller Layer:** Handles HTTP requests/responses
- **Service Layer:** Contains business logic
- **DAO Layer:** Handles data access operations
- **Entity Layer:** Represents database entities

### Design Patterns Used
- **Dependency Injection:** Using Spring's `@Autowired`
- **Repository Pattern:** Through Spring Data JPA
- **MVC Pattern:** Model-View-Controller architecture

## Future Enhancements

Potential improvements for the API:

1. **Validation:** Add input validation using Bean Validation annotations
2. **Pagination:** Implement pagination for the GET /courses endpoint
3. **Search:** Add search functionality by title or description
4. **Exception Handling:** Implement global exception handling with `@ControllerAdvice`
5. **Security:** Add authentication and authorization
6. **API Documentation:** Integrate Swagger/OpenAPI for interactive documentation
7. **Unit Tests:** Add comprehensive unit and integration tests
8. **Logging:** Implement structured logging
9. **Caching:** Add caching for frequently accessed data

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/new-feature`)
3. Commit your changes (`git commit -am 'Add new feature'`)
4. Push to the branch (`git push origin feature/new-feature`)
5. Create a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

---

**Note:** This is a basic implementation suitable for learning and development purposes. For production use, consider implementing additional security, validation, and error handling measures.
