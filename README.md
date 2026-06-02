# 🎯 JobTracker — Backend

A production-grade Spring Boot REST API for managing your entire job search journey — from wishlist to offer.

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.0-brightgreen)
![Java](https://img.shields.io/badge/Java-21-orange)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue)
![JWT](https://img.shields.io/badge/JWT-0.12.6-purple)
![License](https://img.shields.io/badge/License-MIT-yellow)

## 🚀 Live Demo
Frontend: [https://job-tracker-frontend.vercel.app](https://job-tracker-frontend.vercel.app)

## 📌 Features

- **JWT Authentication** — Secure stateless auth with Spring Security
- **State Machine Workflow** — WISHLIST → APPLIED → PHONE_SCREEN → TECHNICAL → ONSITE → OFFER → ACCEPTED
- **Analytics Engine** — Application-to-interview conversion rate, platform effectiveness, monthly trends
- **Cron Scheduler** — Auto email reminders for stale applications (7 days) and upcoming interviews (24 hrs)
- **Interview Question Bank** — Store, tag, and filter 50+ real interview questions by difficulty/company
- **Interview Tracking** — Schedule rounds, add notes, feedback, and ratings

## 🏗️ Architecture

├── config/          → Security & CORS configuration
├── controller/      → REST API endpoints (5 controllers)
├── dto/             → Request/Response data transfer objects
│   ├── request/     → Input validation with Bean Validation
│   └── response/    → Clean API responses (never exposes entities)
├── entity/          → JPA entities mapping to MySQL tables
├── enums/           → Type-safe enums (ApplicationStatus, Platform, etc.)
├── exception/       → Global exception handling
├── repository/      → Spring Data JPA repositories with custom JPQL queries
├── scheduler/       → @Scheduled cron jobs for reminders
├── security/        → JWT filter chain, UserDetailsService
└── service/         → Business logic layer

## 🛠️ Tech Stack

| Technology | Purpose |
|---|---|
| Spring Boot 3.4.0 | Application framework |
| Spring Security | JWT-based authentication |
| Spring Data JPA | Database ORM |
| MySQL 8.0 | Primary database |
| jjwt 0.12.6 | JWT token generation/validation |
| Spring Mail | Email notifications |
| Spring Scheduler | Cron-based reminders |

## ⚡ Quick Start

### Prerequisites
- Java 21
- MySQL 8.0
- Maven 3.6+

### Setup

```bash
# Clone repository
git clone https://github.com/Snehaa044/job-tracker-backend.git
cd job-tracker-backend

# Configure database
# Edit src/main/resources/application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/jobtracker
spring.datasource.username=root
spring.datasource.password=your_password

# Run
./mvnw spring-boot:run
```

Server starts at: `http://localhost:8083`

## 📡 API Endpoints

### Auth

POST /api/auth/register   → Register new user
POST /api/auth/login      → Login, returns JWT token

### Applications
GET    /api/applications          → Get all my applications
POST   /api/applications          → Create new application
GET    /api/applications/{id}     → Get application by ID
PUT    /api/applications/{id}     → Update application
PUT    /api/applications/{id}/status?status=APPLIED  → Quick status update
DELETE /api/applications/{id}     → Delete application

### Interviews
GET  /api/interviews                        → Get all my interviews
POST /api/interviews                        → Schedule interview
GET  /api/interviews/application/{appId}    → Interviews for an application
PUT  /api/interviews/{id}/complete          → Mark complete with feedback
DELETE /api/interviews/{id}                 → Delete interview

### Questions
GET    /api/questions                    → Get all questions
POST   /api/questions                    → Add question
GET    /api/questions/difficulty/{diff}  → Filter by difficulty
GET    /api/questions/company/{company}  → Filter by company
PUT    /api/questions/{id}               → Update question
DELETE /api/questions/{id}               → Delete question

### Analytics
GET /api/analytics   → Full analytics (rates, breakdowns, monthly stats)

## 🗄️ Database Schema

```sql
users               → id, full_name, email, password
job_applications    → id, user_id, company, title, status, platform, dates
interviews          → id, application_id, type, scheduled_at, completed
questions           → id, user_id, question, answer, company, difficulty
```

## 🔐 Environment Variables

```properties
JWT_SECRET=your_secret_key_min_32_chars
DB_URL=jdbc:mysql://localhost:3306/jobtracker
DB_USERNAME=root
DB_PASSWORD=your_password
MAIL_USERNAME=your@gmail.com
MAIL_PASSWORD=your_app_password
```

## 📄 License

MIT License — see [LICENSE](LICENSE)

## 👩‍💻 Author

**Sneha** — [GitHub](https://github.com/Snehaa044)
