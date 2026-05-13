# Role & Domain-Based Access Control API

A secure Spring Boot REST API implementing JWT Authentication, Role-Based Access Control (RBAC), and Domain-Based Access Control using Spring Security.

---

## Features

- JWT-based Authentication
- Role-Based Access Control (RBAC)
- Domain-Based Access Control
- Spring Security Integration
- Stateless Authentication
- Persistent H2 Database
- BCrypt Password Encoding
- Custom Security Filters

---

## Tech Stack

- Java 21
- Spring Boot 3
- Spring Security
- Spring Data JPA
- JWT (JJWT)
- H2 Database
- Maven

---

## Project Architecture

```text
Request
   ↓
DomainValidationFilter
   ↓
JwtAuthenticationFilter
   ↓
Spring Security RBAC
   ↓
Controller
```

---

## Default Users

| Username | Password | Role |
|----------|----------|------|
| admin | admin123 | ROLE_ADMIN |
| user | user123 | ROLE_USER |

---

## Role-Based Access Control (RBAC)

| Endpoint | Access |
|----------|--------|
| `/admin/**` | ROLE_ADMIN |
| `/user/**` | ROLE_USER, ROLE_ADMIN |

---

## Domain-Based Access Control

Admin endpoints can only be accessed from:

```text
admin.myapp.com
```

Requests from other domains are blocked.

---

## API Endpoints

### Login

```http
POST /auth/login
```

### Request Body

```json
{
  "username": "admin",
  "password": "admin123"
}
```

### Response

```json
{
  "token": "JWT_TOKEN"
}
```

---

## Protected Endpoints

### Admin Endpoint

```http
GET /admin/dashboard
```

### Required Headers

```text
Authorization: Bearer <JWT_TOKEN>
Host: admin.myapp.com
```

---

### User Endpoint

```http
GET /user/profile
```

### Required Headers

```text
Authorization: Bearer <JWT_TOKEN>
```

---

## JWT Authentication Flow

```text
Login Request
(username/password)
        ↓
AuthenticationService
        ↓
JWT Token Generated
        ↓
Client Stores JWT
        ↓
Client Sends Bearer Token
        ↓
JwtAuthenticationFilter
        ↓
SecurityContext Populated
        ↓
RBAC Authorization
        ↓
Protected Endpoint Access
```

---

## Domain Validation Flow

```text
Request
   ↓
Check Request Domain
   ↓
Validate Host Header
   ↓
Allow or Reject Request
```

---

## Database Configuration

The project uses a persistent H2 database:

```properties
jdbc:h2:file:./data/testdb
```

Data persists after application restart.

---

## How To Run The Project

### Prerequisites

Make sure the following are installed:

- Java 21
- Maven
- Git (optional)

---

### Clone Repository

```bash
git clone <your-repository-url>
```

---

### Navigate To Project Directory

```bash
cd accessmanagementapi
```

---

### Run Using Maven

```bash
mvn spring-boot:run
```

---

### Run Using IntelliJ IDEA

1. Open the project in IntelliJ IDEA
2. Wait for Maven dependencies to load
3. Run:

```text
AccessmanagementapiApplication.java
```

---

## Application URL

```text
http://localhost:8080
```

---

## Postman Testing

### Login Request

```http
POST http://localhost:8080/auth/login
```

### Login Body

```json
{
  "username": "admin",
  "password": "admin123"
}
```

---

### Access Admin Endpoint

```http
GET http://localhost:8080/admin/dashboard
```

Headers:

```text
Authorization: Bearer <JWT_TOKEN>
Host: admin.myapp.com
```

---

### Access User Endpoint

```http
GET http://localhost:8080/user/profile
```

Headers:

```text
Authorization: Bearer <JWT_TOKEN>
```

---

## Security Concepts Implemented

- JWT Authentication
- Stateless Security
- Role-Based Access Control (RBAC)
- Spring Security Filter Chain
- Custom Authentication Filters
- BCrypt Password Hashing
- Domain-Based Request Validation

---
