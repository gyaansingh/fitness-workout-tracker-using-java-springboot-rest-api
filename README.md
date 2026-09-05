Fitness Workout Tracker API


A RESTful backend application for tracking fitness workouts and exercises.

This project is built using Java, Spring Boot, Spring JDBC (JdbcTemplate), and MySQL. It does not use JPA or Hibernate.


🚀 Features

Create a workout
Get all workouts
Get workout by ID
Update a workout
Delete a workout
Add exercises to workouts
View exercises for a workout
Filter workouts by workout type
Filter workouts by date
Input validation
Exception handling
Transaction management
MySQL database integration
RESTful API architecture
🛠️ Technologies
Java 17
Spring Boot
Spring Web
Spring JDBC
JdbcTemplate
MySQL
Maven
Jakarta Validation
🏗️ Architecture
Client
  |
  v
Controller
  |
  v
Service
  |
  v
Repository
  |
  v
JdbcTemplate
  |
  v
MySQL


The application uses a layered architecture:

controller/
    Handles HTTP requests

service/
    Contains business logic

repository/
    Executes SQL queries using JdbcTemplate

model/
    Contains application models

dto/
    Contains request objects

exception/
    Handles application exceptions



🗄️ Database

Create the MySQL database:

CREATE DATABASE fitness_tracker;


The application uses two tables:

Workouts
workouts
--------------------------------
id
name
type
workout_date
duration_minutes

Exercises
exercises
--------------------------------
id
workout_id
name
sets
reps
weight_kg


workout_id creates a relationship between an exercise and its workout.



⚙️ Configuration

Configure your MySQL connection in application.properties.


For example:

spring.datasource.url=jdbc:mysql://localhost:3306/fitness_tracker
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.sql.init.mode=always

server.port=8080


Set the environment variables before starting the application:

DB_USERNAME=root
DB_PASSWORD=your_password



▶️ How to Run
1. Clone the repository
git clone https://github.com/YOUR_USERNAME/fitness-workout-tracker-api.git

2. Open the project

Open the project in IntelliJ IDEA, Eclipse, or VS Code.

3. Create the database
CREATE DATABASE fitness_tracker;

4. Configure database credentials

Set:

DB_USERNAME
DB_PASSWORD

5. Run the application

Using Maven:

mvn spring-boot:run


Or run:

FitnessApplication.java


The API will start on:

http://localhost:8080

📡 REST API
Create Workout
POST /api/workouts

Request
{
  "name": "Chest Workout",
  "type": "STRENGTH",
  "workoutDate": "2026-09-05",
  "durationMinutes": 60,
  "exercises": [
    {
      "name": "Bench Press",
      "sets": 4,
      "reps": 10,
      "weightKg": 60
    },
    {
      "name": "Incline Dumbbell Press",
      "sets": 3,
      "reps": 12,
      "weightKg": 20
    },
    {
      "name": "Cable Fly",
      "sets": 3,
      "reps": 15,
      "weightKg": 15
    }
  ]
}

Response
{
  "id": 1,
  "name": "Chest Workout",
  "type": "STRENGTH",
  "workoutDate": "2026-09-05",
  "durationMinutes": 60,
  "exercises": [
    {
      "id": 1,
      "workoutId": 1,
      "name": "Bench Press",
      "sets": 4,
      "reps": 10,
      "weightKg": 60
    },
    {
      "id": 2,
      "workoutId": 1,
      "name": "Incline Dumbbell Press",
      "sets": 3,
      "reps": 12,
      "weightKg": 20
    },
    {
      "id": 3,
      "workoutId": 1,
      "name": "Cable Fly",
      "sets": 3,
      "reps": 15,
      "weightKg": 15
    }
  ]
}

Get All Workouts
GET /api/workouts

Response
[
  {
    "id": 1,
    "name": "Chest Workout",
    "type": "STRENGTH",
    "workoutDate": "2026-09-05",
    "durationMinutes": 60,
    "exercises": [
      {
        "id": 1,
        "workoutId": 1,
        "name": "Bench Press",
        "sets": 4,
        "reps": 10,
        "weightKg": 60
      }
    ]
  }
]

Get Workout by ID
GET /api/workouts/{id}


Example:

GET /api/workouts/1

Update Workout
PUT /api/workouts/{id}


Example:

PUT /api/workouts/1

Request
{
  "name": "Updated Chest Workout",
  "type": "STRENGTH",
  "workoutDate": "2026-09-05",
  "durationMinutes": 70,
  "exercises": [
    {
      "name": "Bench Press",
      "sets": 5,
      "reps": 8,
      "weightKg": 65
    }
  ]
}

Delete Workout
DELETE /api/workouts/{id}


Example:

DELETE /api/workouts/1


Response:

HTTP 204 No Content

Filter by Workout Type
GET /api/workouts/type/{type}


Example:

GET /api/workouts/type/STRENGTH

Filter by Date
GET /api/workouts/date/{date}


Example:

GET /api/workouts/date/2026-09-05

🧪 API Testing

You can test the API using:

Postman
Insomnia
cURL
IntelliJ HTTP Client

Example using cURL:

curl http://localhost:8080/api/workouts

🔒 Validation

The API validates incoming requests.

For example:

{
  "name": "",
  "type": "",
  "durationMinutes": 0
}


will result in a 400 Bad Request.

Example response:

{
  "name": "Workout name is required",
  "type": "Workout type is required",
  "durationMinutes": "Duration must be greater than 0"
}



❌ Error Handling

The application provides centralized exception handling using @RestControllerAdvice.

Example:

GET /api/workouts/999


Response:

{
  "error": "Workout not found with id: 999"
}

💾 Why JdbcTemplate?

This project intentionally does not use JPA or Hibernate.

Instead, it uses Spring's JdbcTemplate to execute SQL queries directly.

Example:

String sql = """
    SELECT id, name, type, workout_date, duration_minutes
    FROM workouts
    WHERE id = ?
    """;

jdbcTemplate.query(sql, ...);


Benefits:

Direct SQL control
Easy to understand SQL operations
No ORM complexity
Good understanding of database interaction
Useful for learning Spring JDBC
🔄 Transaction Management

Creating a workout and its exercises involves multiple database operations.

The service uses:

@Transactional


This ensures that the operations are handled as one transaction.

For example:

Create Workout
      ↓
Create Exercise 1
      ↓
Create Exercise 2
      ↓
Create Exercise 3


If one operation fails, the transaction can be rolled back.



🚀 Future Improvements

Possible future features:

User-specific workouts
Exercise management API
Workout statistics
Weekly/monthly progress
Calories burned tracking
Personal records
Search and pagination
Swagger/OpenAPI documentation
Unit and integration tests
Docker support
CI/CD with GitHub Actions
