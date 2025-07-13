# 🧰 SETUP.md - Run EduFeesMS Locally

This guide will help you **set up and run** the EduFeesMS project on your local machine.

---

## 📦 Prerequisites

* ✅ JDK 21 installed
* ✅ Git installed
* ✅ IDE like IntelliJ / VS Code
* ✅ Internet connection (for Gradle to resolve dependencies)

---

## 📁 Clone the Repository

```bash
git clone https://github.com/<your-username>/EduFeesMS.git
cd EduFeesMS
```

Open the project folder in your favorite IDE.

---

## ▶️ Running Microservices

You need **two terminals** or run services from your IDE.

### 1️⃣ Start `student-service`

```bash
cd student-service
./gradlew bootRun
```

* Runs on **port 8081**
* Swagger: `http://localhost:8081/swagger-ui.html`

### 2️⃣ Start `receipt-service`

```bash
cd ../receipt-service
./gradlew bootRun
```

* Runs on **port 8082**
* Swagger: `http://localhost:8082/swagger-ui.html`

---

## 🦪 Test the APIs

### Option 1: Swagger UI

* Open the above URLs in your browser
* Try endpoints interactively

### Option 2: Postman Collection

* Navigate to `postman/` folder
* Import `EduFeesMS.postman_collection.json` into Postman
* Includes tests for:

  * Adding students
  * Creating receipts
  * Fetching receipts by ID / student ID

---

## 🧼 Cleanup

* Uses **in-memory H2 DB** — no database setup required
* To reset, simply stop and rerun the services

---

## 💬 Troubleshooting

| Issue                    | Solution                                                |
| ------------------------ | ------------------------------------------------------- |
| Gradle build fails       | Run `./gradlew clean build` and retry                   |
| Port already in use      | Stop the other process or change server.port            |
| Swagger not loading      | Wait for service startup to complete fully              |
| Inter-service call fails | Ensure `student-service` is up before `receipt-service` |

---

## 🧠 Tip

To make changes and test quickly:

* Modify code in IDE
* Restart only the affected microservice

---

👏 You’re now ready to explore and build on EduFeesMS!

👨‍💼 Built by
Vishal Agrawal
Backend Engineer | Java | Spring Boot | Microservices
📧 [vishalagrawal1420@gmail.com](mailto:vishalagrawal1420@gmail.com)
