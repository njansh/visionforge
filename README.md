
# 🚀 VisionForge

### Distributed Asynchronous Image Processing Platform

------------------------------------------------------------------------

## 🎯 Project Purpose

**VisionForge** is a distributed backend platform designed to process images asynchronously and extract structured information using computer vision techniques.

This project was created as a **technical growth challenge**, simulating real-world backend architecture to move beyond basic CRUD applications into the realm of distributed systems and clean engineering.

------------------------------------------------------------------------

## 🏗 High-Level Architecture

Client  
↓  
**REST API** (Spring Boot)  
↓  
**RabbitMQ** (Message Broker)  
↓  
**Worker** (Image Processing Pipeline)  
↓  
**PostgreSQL** (Persistent Storage)

------------------------------------------------------------------------

## 🔄 Execution Flow (Current Implementation)

1.  **Job Creation**: User uploads an image via REST API.
2.  **Persistence**: API creates a Job record in **PostgreSQL** with status `CREATED`.
3.  **Event Publishing**: API publishes a message containing the `jobId` to **RabbitMQ**.
4.  **Async Consumption**: Worker consumes the message and updates Job status to `RUNNING`.
5.  **Image Processing**: Worker applies a native Grayscale filter to the image.
6.  **Completion**: Job is updated to `DONE` with the path to the processed image.
7.  **Query**: User checks job status and retrieves the processed result via API.

------------------------------------------------------------------------

## 📦 Tech Stack

-   **Java 17** & **Spring Boot 3**
-   **PostgreSQL**: Relational database for persistent job tracking
-   **RabbitMQ**: Message broker for asynchronous task distribution
-   **Docker & Docker Compose**: Orchestration of database and messaging services
-   **JUnit 5**: Unit testing for core domain logic
-   **SLF4J (Logback)**: Structured professional logging
-   **Swagger (OpenAPI 3)**: API documentation and interactive testing

------------------------------------------------------------------------

## 📈 Technical Roadmap

### ✅ Phase 1 — Foundation (Completed)
- [x] Multi-module project setup.
- [x] Docker Compose orchestration (Postgres + RabbitMQ).
- [x] Hexagonal Architecture (Clean Architecture) package structure.

### ✅ Phase 2 — API & Persistence (Completed)
- [x] Job entity and JPA persistence layer with **PostgreSQL**.
- [x] REST API for image upload and status tracking.

### ✅ Phase 3 — Asynchronous Worker (Completed)
- [x] Message publishing and consumption logic.
- [x] Professional structured logging (SLF4J).
- [x] Job state machine implementation (`CREATED` -> `RUNNING` -> `DONE`).

### 🚧 Phase 4 — Vision Pipeline (In Progress)
- [x] **Native Grayscale Filter**: Implemented via Java AWT/ImageIO.
- [ ] OpenCV & OCR (Tesseract) integration for text extraction.
- [ ] Artifact storage (Moving from local to Cloud Storage).

### 🚧 Phase 5 — Quality & Observability (In Progress)
- [x] **Unit Testing**: 100% coverage of Job domain logic status transitions.
- [ ] **Resiliency**: Implementation of Dead Letter Queues (DLQ) and Retry strategies.
- [ ] **Idempotency**: Ensuring unique processing per message.
- [ ] CI/CD Pipeline via GitHub Actions.

------------------------------------------------------------------------

## 📂 Implementation Details (Clean Architecture)

-   **Domain**: Contains the `Job` entity, status enums, and repository interfaces. Logic is pure Java.
-   **Application**: Use Cases orchestrate business flow (Start, Complete, Fail Job).
-   **Infrastructure**: Implements external concerns like RabbitMQ consumers, JPA repositories, and Image processing services.

------------------------------------------------------------------------

## 📌 Project Status

**Active Development - Feature Complete Core Pipeline.** The system currently supports the full lifecycle of an asynchronous job, from upload to persistent grayscale processing and retrieval.
