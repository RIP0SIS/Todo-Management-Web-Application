# 📝 Spring Boot To-Do Management Application

A **production-style To-Do Management System** built with **Spring Boot**, showcasing clean backend architecture, REST API design, validation, security, and dual UI + API exposure.

This project demonstrates how the **same business domain** can be reused across:
- A **JSP-based MVC UI**
- A **JSON REST API** for API consumers

---

## 🚀 Features Overview

- ✅ Full **CRUD operations** for To-Dos
- 🔁 Hybrid backend: **JSP MVC + REST API**
- 🔐 **Spring Security** with role-based access
- 🧪 **Bean Validation** with centralized error handling
- 🧱 Clean layered architecture (Controller → Service → Repository)
- 🗄️ **H2 (dev)** and **MySQL (Docker-ready)** support
- 📦 DTO-based request/response handling
- ⚠️ Consistent JSON error responses via `@ControllerAdvice`
- 📘 **API Documentation**: Integrated `springdoc-openapi` to auto-generate interactive OpenAPI/Swagger documentation for all REST endpoints, making the API easily testable and consumer-friendly.
- 📈 **Application Monitoring**: Enabled **Spring Boot Actuator** to expose operational endpoints (health, metrics) for basic application observability and production readiness.
- 🎯 **Response Filtering**: Applied **static filtering** on REST responses to control exposed fields and keep API contracts clean and intentional.


---

## 🧩 Architecture

Controller (MVC / REST)
     ↓
Service Layer
     ↓
Repository (Spring Data JPA)
     ↓
Database (H2 / MySQL)

### Design Highlights
- Layered architecture with clear separation of concerns
- Thin controllers delegating persistence to repositories
- Single domain model reused across MVC views and REST APIs
- DTOs used to control and stabilize REST API contracts

---

## 🛠️ Tech Stack

| Layer | Technology |
|-----|-----------|
| Language | Java |
| Framework | Spring Boot |
| Web | Spring MVC, REST |
| Security | Spring Security, BCrypt |
| Persistence | Spring Data JPA, Hibernate |
| Validation | Bean Validation (`@Valid`) |
| UI | JSP, JSTL |
| Database | H2 (dev), MySQL (Docker-ready) |
| Build Tool | Maven |

---

## 🧪 Testing

- Wrote **JUnit tests** for the repository layer using **@DataJpaTest** to verify persistence behavior in an **isolated in-memory database (H2)**.
- Tested core JPA operations such as **save**, **find**, and **delete**, along with **custom query methods** like `findByUsername`.
- Ensured repository correctness independent of web and service layers.

---


## 📸 Application Screenshots

> Screenshots are available in the `Todo-App-Screenshots/` folder

### 1️⃣ Login Page
Authenticate the user.

![Todo List](Todo-App-Screenshots/1.png)

---

### 2️⃣ Add New To-Do
Form to create a new To-Do item with target date and status.

![Add Todo](Todo-App-Screenshots/4.png)

---

### 3️⃣ CRUD Dashboard & Update Existing To-Do
Shows all existing To-Dos with Update/Delete actions.
Edit an existing To-Do while reusing the same form logic.

![Update Todo](Todo-App-Screenshots/3.png)

---

### 4️⃣ Validation Errors (@Valid)
Server-side validation errors handled using Bean Validation.

![Validation Error](Todo-App-Screenshots/5.png)

---

### 5️⃣ REST API – JSON Response
REST endpoint returning structured JSON data for API consumers.

![REST API JSON](Todo-App-Screenshots/7.png)

---

### 6️⃣ Centralized Error Handling
Consistent error response using `@ControllerAdvice`.

![API Error Response](Todo-App-Screenshots/8.png)

---

## 🔐 Security

- In-memory user store for demo purposes
- Passwords encoded using **BCrypt**
- Role-based endpoint protection
- Session handling for MVC flows

```java
@Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}
```

---

⚠️ Validation & Error Handling

Request validation using @Valid on DTOs

Centralized exception handling using @ControllerAdvice

Custom NotFoundException for missing resources

Consistent error JSON payload for REST clients

Example error response:


```json
{
  "timestamp": "2026-01-30T10:15:00",
  "message": "Validation failed",
  "details": {
    "description": "Description must not be blank"
  }
}
```

---

🗄️ Database Configuration
H2 (Default – Development)

Fast local setup

Auto-created schema

Console enabled

MySQL (Production-ready)

Configuration included but commented

Docker-friendly

Easy switch using profiles

# spring.datasource.url=jdbc:mysql://localhost:3306/todos
# spring.datasource.username=todos-user
# spring.datasource.password=****

---

📦 Sample Data

Demo data is bootstrapped using data.sql

Ensures consistent demo runs

Useful for interviews and evaluations

▶️ How to Run Locally
Prerequisites

Java 17+

Maven

Steps
git clone https://github.com/<your-username>/<repo-name>.git
cd <repo-name>
mvn spring-boot:run


Access:

MVC UI: http://localhost:8080

REST API: http://localhost:8080/users/todos

🎯 What This Project Demonstrates

✔ Real-world Spring Boot backend patterns
✔ REST API design with proper HTTP semantics
✔ Validation, security, and exception handling
✔ Readiness for Dockerized & production environments

👨‍💻 Author

Riposis
📧 Email: restinpeace869@gmail.com

🐙 GitHub: https://github.com/RIP0SIS


---

⭐ Final Note

This project is intentionally backend-focused and designed to reflect industry-standard Spring Boot practices expected from entry-level to junior backend developers.

Feel free to explore, fork, or extend it 🚀
