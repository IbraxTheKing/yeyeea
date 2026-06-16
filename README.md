# Website Template Starter - Yeyeea

A reusable full-stack website template designed to serve as a foundation for future projects, client websites, and personal applications.

This project provides a clean architecture, a REST API backend, database integration, and a scalable structure that can be customized depending on project requirements.

---

## 🚀 Purpose

The goal of this project is to have a ready-to-use foundation for building websites and web applications faster.

It can be used for:

- Client projects
- Personal websites
- Business websites
- Web applications
- Prototypes and MVPs
- Future custom projects

The structure is designed to be extended rather than rebuilt from scratch.

---

# 🏗️ Architecture

The project follows a layered architecture:

```
Project
│
├── Common
│   ├── Entities
│   ├── DTOs
│   └── Services Interfaces
│
├── Data
│   ├── JPA Implementations
│   ├── Database Access
│   └── Persistence Layer
│
├── Business
│   ├── Business Services
│   └── Business Logic
│
└── REST Server
    ├── Resources
    └── API Endpoints
```

### Layers

### Common
Contains shared objects used across the application.

Includes:

- Database entities
- Service interfaces
- Shared models

---

### Data Layer

Responsible for communication with the database.

Features:

- JPA persistence
- CRUD operations
- Custom queries
- Entity management

---

### Business Layer

Contains application logic.

Responsibilities:

- Data validation
- Service orchestration
- Business rules

---

### REST Layer

Provides HTTP endpoints.

Supports:

- GET requests
- POST requests
- PUT requests
- DELETE requests

---

# 🛠️ Technologies

## Backend

- Kotlin
- Jakarta EE
- JAX-RS
- JPA
- EclipseLink
- REST API

## Database

- MySQL
- SQL

## Server

- GlassFish / Jakarta EE compatible server

---

# 📦 Features

Current template includes:

## User Management

- Create users
- Retrieve users
- Update users
- Delete users
- Search users by:
  - username
  - email

## Database Management

- Entity mapping
- Generic CRUD service
- Transaction handling

## REST API

Example endpoints:

```
GET    /users
GET    /users/{id}

POST   /users

PUT    /users/{id}

DELETE /users/{id}
```

---

# ⚙️ Installation

## Requirements

Install:

- JDK 17+
- Maven
- MySQL
- Jakarta EE server

---

## Database Setup

Create a database:

```sql
CREATE DATABASE website_template;
```

Configure your persistence settings:

```
src/main/resources/META-INF/persistence.xml
```

Example:

```xml
<property 
name="jakarta.persistence.jdbc.url"
value="jdbc:mysql://localhost:3306/website_template"/>
```

---

## Run the project

Deploy the application on your Jakarta EE server.

The API will be available at:

```
http://localhost:8080/
```

---

# 🔐 Security Notes

This template is not production-ready by default.

Before deploying:

- Add authentication
- Add authorization rules
- Hash passwords
- Validate user input
- Configure HTTPS
- Add logging

---

# 🔄 Future Improvements

Possible additions:

- JWT authentication
- Role management
- Admin dashboard
- Frontend integration
- File uploads
- Email system
- Payment integration
- Docker support
- Automated deployment

---

# 📁 Project Philosophy

This project is meant to evolve.

The objective is to keep a reliable base architecture where new features can be added quickly without rewriting the entire application.

---

# 👤 Author

Created as a personal development template and reusable foundation.

Built for:
- personal projects
- professional projects
- client solutions
