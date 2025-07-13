# 📚 EduFeesMS - Microservices for Student & Fee Management

EduFeesMS is a clean and modular **Spring Boot microservices project** designed to manage student records and fee collection in a scalable, real-world style.

---

## 🚀 Project Highlights

EduFeesMS is split into two independent services:

| Service              | Description                                   |
| -------------------- | --------------------------------------------- |
| 🎓 `student-service` | Handles student data (name, grade, etc.)      |
| 💰 `receipt-service` | Manages fee collection and generates receipts |

### 🌟 Why Microservices?

- Separation of concerns (student logic ≠ receipt logic)
- Better scalability and maintainability
- Independent deployability
- Aligned with Domain-Driven Design principles

---

## 🖼 Architecture Flow

![Service Flow Diagram](./assets/service_flow_diagram.png)

---

## 🛠️ Tech Stack

- Java 21
- Spring Boot 3.2+
- Spring Web, Spring Data JPA
- Feign Client (OpenFeign)
- Swagger / OpenAPI
- Gradle
- H2 In-Memory DB
- JUnit 5 + Mockito
- No Lombok (Manual Getters, Setters, Constructors used)

---

## 📦 Services Overview

### 1️⃣ Student Service (`student-service`)

Handles:
- Adding new students
- Fetching student by ID
- Auto-assigning default fees based on grade

📍 **Runs on `8081`**

| Endpoint                | Method | Description       |
| ----------------------- | ------ | ----------------- |
| `/api/v1/students`      | POST   | Add a new student |
| `/api/v1/students/{id}` | GET    | Get student by ID |
| `/api/v1/students/{id}/fees` | GET | Get pending fees  |

---

### 2️⃣ Receipt Service (`receipt-service`)

Handles:
- Creating fee receipts
- Validating student ID via `student-service`
- Fetching receipts

📍 **Runs on `8082`**

| Endpoint                               | Method | Description                      |
| -------------------------------------- | ------ | -------------------------------- |
| `/api/v1/receipts`                     | POST   | Create a new receipt             |
| `/api/v1/receipts/{id}`                | GET    | Fetch a receipt by ID            |
| `/api/v1/receipts/student/{studentId}` | GET    | Get receipts for a student       |

---

## 🔗 Inter-Service Communication

- Uses **Spring Cloud OpenFeign**
- `receipt-service` calls `student-service` for student validation

---

## 📄 API Documentation (Swagger)

| Service         | Swagger URL                             |
| --------------- | --------------------------------------- |
| student-service | `http://localhost:8081/swagger-ui.html` |
| receipt-service | `http://localhost:8082/swagger-ui.html` |

---

## ✅ Engineering Best Practices

- ✅ DTOs for API boundaries
- ✅ Service Layer with SOLID design
- ✅ Manual validation (No Lombok used)
- ✅ Feign Client for microservice calls
- ✅ Global Exception Handling
- ✅ Complete unit tests for all classes
- ✅ 100% test coverage via JaCoCo
- ✅ Swagger for live API testing

---

## 🔮 Future Enhancements

- 🔐 Add JWT-based authentication
- 🧩 Migrate to PostgreSQL/MySQL
- 📡 Service discovery via Eureka
- 🚪 Add API Gateway (Spring Cloud Gateway)
- 🔁 Circuit breaker (Resilience4j)

---

## 📁 Setup

Refer to [`SETUP.md`](SETUP.md) for instructions on how to run, test, and troubleshoot this project locally.

---

🎉 **Built with care for learning and real-world use.**

👨‍💻 Vishal Agrawal  
Backend Engineer | Java | Spring Boot | Microservices  
📧 vishalagrawal1420@gmail.com
