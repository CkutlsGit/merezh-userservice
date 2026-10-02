# User Service - Merezh

Microservice responsible for storing and managing users.

---

## 📋 Overview

User Service is a Spring Boot microservice that provides user management
functionality: registration, retrieval, validation of credentials, and deletion.
It is used internally by Auth Service for authentication and by other services
through the Gateway.

The service does **not** handle authentication itself - it only stores user data
and exposes a validation endpoint used by Auth Service.

---

## 🚀 Technology Stack

**Backend**
- Java 21 - core language
- Spring Boot 3 - application framework
- Spring Data JPA - database access and ORM
- Spring Security Crypto - BCrypt password encoder

**Database**
- PostgreSQL - production database

**DevOps**
- Docker - containerization
- Docker Compose - multi-container orchestration
- Spring Boot Actuator - health checks and monitoring

**Testing**
- JUnit 5
- Mockito

---

## ✨ Features

### 👤 User Management
- Create a new user
- Get user by ID
- Get all users (admin only, via Gateway)
- Delete user by ID
- Validate user credentials (internal, used by Auth Service)

### 🔐 Security
- Passwords stored as BCrypt hashes (hashing performed in Auth Service)
- Endpoint protection is handled by the Gateway (JWT + role-based)
- Service trusts `X-User-Id` and `X-User-Role` headers set by the Gateway
- Internal endpoints (`/create`, `/validate`) are not exposed through the Gateway

### ✅ Data Validation
- Email format and uniqueness
- Login uniqueness
- Consistent error responses via `@RestControllerAdvice`

---

## 🛠️ Quick Start

### Prerequisites
- Docker
- Docker Compose

### Run with Docker Compose

```bash
docker compose up --build
```

The service will be available on port **8080**.
---

## 📚 API Endpoints

Base path: `/api/v1/users`

| Method | Endpoint       | Description                          | Access        |
|--------|----------------|--------------------------------------|---------------|
| GET    | `/`            | Get all users                        | ADMIN         |
| GET    | `/{id}`        | Get user by ID                       | ADMIN         |
| POST   | `/create`      | Create a new user                    | authservice   |
| POST   | `/validate`    | Validate user credentials            | authservice   |
| DELETE | `/{id}`        | Delete user by ID                    | ADMIN         |

**Note:**  This service trusts data from the Gateway.

## 📦 Project Structure

```
src/main/java/ru/merezh/userservice/
├── config/                    # Spring configuration (PasswordEncoder, etc.)
├── controller/                # REST controllers
├── dto/                       # Data Transfer Objects
├── entity/                    # JPA entities
│   └── enums/                 # Role enum
├── exception/                 # Custom exceptions and handlers
│   ├── controller/            # @RestControllerAdvice
│   └── dto/                   # Error response DTOs
├── repository/                # Spring Data JPA repositories
└── service/                   # Business logic
```

---

## 🔒 Security

- **Passwords are stored as BCrypt hashes.** Hashing is performed in Auth Service
  before the user is created here.

## 🩺 Health Checks

The service exposes Spring Boot Actuator endpoints:

| Endpoint                              | Purpose                        |
|---------------------------------------|--------------------------------|
| `/actuator/health`                    | Overall health                 |
| `/actuator/health/liveness`           | Liveness probe                 |
| `/actuator/health/readiness`          | Readiness probe (includes DB)  |
| `/actuator/info`                      | Service info                   |

## 🧪 Testing

```bash
mvn test
```

Unit tests cover the main `UserService` flows:
- Creating a user with a duplicate email → exception
- Creating a user with a duplicate login → exception
- Creating a user with valid data → saved

