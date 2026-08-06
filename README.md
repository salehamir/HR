# HR Employee API

A small Spring Boot REST application for managing employee records. The project uses Spring Data JPA for persistence and an in-memory H2 database for local development.

## Technology stack

- Java 25
- Spring Boot 4.1.0
- Spring Web
- Spring Data JPA
- H2 Database
- Maven Wrapper

## Project structure

```text
src/main/
|-- java/ir/irancelllabs/hr/
|   |-- HrApplication.java
|   |-- controller/EmployeeController.java
|   |-- model/Employee.java
|   |-- repository/EmployeeRepository.java
|   `-- service/EmployeeService.java
`-- resources/
    `-- application.properties
```

The application follows a conventional layered structure:

- `controller` exposes HTTP endpoints.
- `service` contains employee operations.
- `repository` provides database access through `JpaRepository`.
- `model` defines the `Employee` JPA entity.

## Employee model

Employee records contain these fields:

| Field | Type | Constraint |
|---|---|---|
| `id` | `Long` | Auto-generated primary key |
| `firstName` | `String` | Required |
| `lastName` | `String` | Required |
| `email` | `String` | Unique |
| `department` | `String` | Optional |
| `salary` | `Double` | Optional |

## Prerequisites

- JDK 25

You do not need a system-wide Maven installation because the Maven Wrapper is included.

## Run the application

On Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

On macOS or Linux:

```bash
./mvnw spring-boot:run
```

The application starts at `http://localhost:8081`.

## API

### List employees

```http
GET /employees
```

Example request:

```bash
curl http://localhost:8081/employees
```

The response is a JSON array of employee records.

> At present, this is the only HTTP endpoint exposed by the controller. The service contains create, read-by-ID, update, and delete methods, but corresponding controller routes have not yet been added.

## H2 database console

While the application is running, open:

```text
http://localhost:8081/h2-console
```

Use these connection settings:

| Setting | Value |
|---|---|
| JDBC URL | `jdbc:h2:mem:hrdb` |
| User name | `sa` |
| Password | Leave blank |

The database is stored in memory, so its contents are lost whenever the application stops.

## Build and test

On Windows:

```powershell
.\mvnw.cmd test
.\mvnw.cmd package
```

On macOS or Linux:

```bash
./mvnw test
./mvnw package
```

The packaged application is created under `target/` and can be run with:

```bash
java -jar target/hr-1.0.0-SNAPSHOT.jar
```

## Current development notes

- `EmployeeService` and `EmployeeController` declare dependencies but do not currently inject them through constructors or fields. Requests to `GET /employees` will fail until dependency injection is configured.
- `Employee` does not currently define constructors, getters, or setters. These are needed for typical JSON serialization/deserialization and for implementing update operations.
- The update field assignments in `EmployeeService` are currently commented out.
- There are currently no automated tests under `src/test`.
