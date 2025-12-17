# Vehicle Control System (Java Spring Boot Edition)

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2.2-green)
![Status](https://img.shields.io/badge/Status-Active-brightgreen)
![License](https://img.shields.io/badge/License-MIT-blue)

Welcome to the **Ultimate Vehicle Control System**! This project is a complete modernization of a legacy Python script, transformed into a robust, enterprise-grade Java application. It features a sleek web dashboard, a comprehensive REST API, and simulated vehicle physics including fuel consumption and odometer tracking.

## 🌟 Key Features

*   **Advanced Physics Engine**:
    *   **Fuel System**: Watch your fuel gauge drop as you speed up! If you run out, the engine stops.
    *   **Odometer**: Track every kilometer traveled.
    *   **Engine & Gears**: Realistic engine start/stop procedures and gear management.
*   **Modern Web Dashboard**:
    *   Built with **Thymeleaf** and **Bootstrap 5**.
    *   Real-time updates of Speed, Steering, Fuel, and Odometer.
    *   Interactive controls for a gamified experience.
*   **RESTful API**:
    *   Full programmatic control over the vehicle.
    *   **Swagger UI**: Interactive API documentation built-in!
*   **Robust Testing**:
    *   Unit Tests (JUnit 5).
    *   Integration Tests (MockMvc).
    *   E2E Tests (Selenium).

## 🚀 Quick Start

### Prerequisites
*   Java 21 or higher
*   Maven 3.x

### Build & Run
```bash
mvn spring-boot:run
```

Once started, open your browser:
*   **Dashboard**: [http://localhost:8080](http://localhost:8080)
*   **API Docs (Swagger)**: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## 🎮 How to Play

1.  **Start the Engine**: You can't drive with the engine off! Click "Start Engine".
2.  **Select a Gear**: (Optional) Shift up for efficiency!
3.  **Accelerate**: Set your desired acceleration and hit "Accelerate". Watch your speed climb and fuel drop.
4.  **Refuel**: Running low? Hit the "Refuel" button to top up the tank.
5.  **Steer**: Adjust your trajectory between -45 and 45 degrees.

## 🔌 API Reference

The application exposes a fully documented REST API.

**Interactive Documentation**:
Navigate to `/swagger-ui.html` on your running instance to explore and test endpoints interactively.

**Core Endpoints**:

| Method | Endpoint | Description |
|ion | --- | --- |
| `GET` | `/api/vehicle` | Get full vehicle status (Speed, Fuel, Odometer, etc.) |
| `POST` | `/api/control` | Send commands (accelerate, brake, steer, gear) |
| `POST` | `/api/vehicle/refuel` | Refuel the vehicle to 100% |

**Example: Check Status**
```bash
curl http://localhost:8080/api/vehicle
```

**Example: Refuel**
```bash
curl -X POST http://localhost:8080/api/vehicle/refuel
```

## 🧪 Testing

Run the full test suite with Maven:
```bash
mvn test
```
*Note: Selenium E2E tests are skipped by default if no display is detected.*

## 🏗 Architecture

*   **Controller**: `VehicleController` handles Web and API requests.
*   **Service**: `VehicleService` contains the business logic (physics, validation).
*   **Model**: `Vehicle` POJO holds the state.
*   **View**: `dashboard.html` renders the UI server-side.

---
*Created by Jules*
