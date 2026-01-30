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

---

## 🧩 Architecture

Controller (MVC / REST)
↓
Service Layer
↓
Repository (Spring Data JPA)
↓
Database (H2 / MySQL)


- **Single domain model** reused across MVC and REST
- Controllers are thin, business logic lives in services
- DTOs isolate API contracts from entities

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

## 📸 Application Screenshots

> Screenshots are available in the `Todo-App-Screenshots/` folder

### 1️⃣ To-Do List View (CRUD Dashboard)
Shows all existing To-Dos with Update/Delete actions.

![Todo List](Todo-App-Screenshots/1.png)

---

### 2️⃣ Add New To-Do
Form to create a new To-Do item with target date and status.

![Add Todo](Todo-App-Screenshots/2.png)

---

### 3️⃣ Update Existing To-Do
Edit an existing To-Do while reusing the same form logic.

![Update Todo](Todo-App-Screenshots/3.png)

---

### 4️⃣ Validation Errors (@Valid)
Server-side validation errors handled using Bean Validation.

![Validation Error](Todo-App-Screenshots/4.png)

---

### 5️⃣ REST API – JSON Response
REST endpoint returning structured JSON data for API consumers.

![REST API JSON](Todo-App-Screenshots/5.png)

---

### 6️⃣ Centralized Error Handling
Consistent error response using `@ControllerAdvice`.

![API Error Response](Todo-App-Screenshots/6.png)

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

REST API: http://localhost:8080/api/todos

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


---

### 🔥 Next (optional but HIGH impact)
If you want, I can:
- Tailor a **shorter README for recruiters**
- Optimize this README for **GitHub SEO**
- Write **resume bullets that perfectly match this README**

Just tell me what you want next.
