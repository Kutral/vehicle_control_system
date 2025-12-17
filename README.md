# Vehicle Control System (Java Spring Boot Edition)

This is a modernized, "surprising" upgrade of the original Vehicle Control System. It has been completely rewritten in Java using **Spring Boot 3** and enhanced with a modern UI and robust architecture.

## Improvements & New Features

*   **Technology Stack**: Migrated from Python/Flask to **Java 21 / Spring Boot 3**.
*   **Architecture**: Clean separation of concerns (Model-View-Controller).
*   **Enhanced Physics**:
    *   **Engine State**: You must start the engine before driving!
    *   **Gears**: Added a gear system (1-5, Neutral, Reverse).
    *   **Constraints**: Validated speed limits and steering angles.
*   **Modern UI**: Replaced raw HTML with **Thymeleaf + Bootstrap 5** for a responsive, dashboard-style interface.
*   **API Support**: Added a REST API (`/api/vehicle` and `/api/control`) for programmatic control.
*   **Testing**: Comprehensive test suite including Unit Tests, Integration Tests (MockMvc), and E2E Selenium Tests (Java).

## How to Run

### Prerequisites
*   Java 21+
*   Maven 3.x

### Build and Run
```bash
mvn spring-boot:run
```

Access the dashboard at: **http://localhost:8080**

### Running Tests
```bash
mvn test
```
*Note: The Selenium E2E test requires a local Chrome installation and is disabled by default for CI environments without a display.*

## API Usage

You can control the vehicle programmatically!

**Get Status:**
```bash
curl http://localhost:8080/api/vehicle
```

**Control Vehicle:**
```bash
# Start Engine
curl -X POST "http://localhost:8080/api/control?action=toggle_engine"

# Accelerate
curl -X POST "http://localhost:8080/api/control?action=accelerate&value=20"

# Steer
curl -X POST "http://localhost:8080/api/control?action=steer&value=30"
```
