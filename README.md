# Student Internship Application System

## Student Information

**Name:** BELISHYA RURANGWA Kylie  
**Student ID:** 27109  

## Project Name

**Student Internship Application System Backend**

## Description

The system allows users to manage:

- Students
- Companies
- Booths
- Fields
- Internship applications
- Placements

It also includes:

- CRUD operations
- Derived queries
- JPQL queries
- Business rules
- Application status management
- Company field management
- Company booth management
- Placement management
- Application statistics
- Application cancellation

## Technologies Used

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- PostgreSQL
- Maven
- Postman

## Steps to Run the API

### 1. Create the PostgreSQL Database

Create a PostgreSQL database named:

```text
internship_system
```

### 2. Configure the Database Connection

Open:

```text
src/main/resources/application.properties
```

Configure the database connection as follows:

```properties
spring.application.name=student-internship-application-system
spring.datasource.url=jdbc:postgresql://localhost:5432/internship_system
spring.datasource.username=postgres
spring.datasource.password=YOUR_POSTGRES_PASSWORD
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
server.port=2005
```


### 3. Open the Project

Open the project folder in Visual Studio Code

### 4. Run the Application

Open a terminal in the project folder and run:

```powershell
.\mvnw.cmd spring-boot:run
```

### 5. Confirm the Application Started

When the application starts successfully, Tomcat runs on port:

```text
2005
```

The API base URL is:

```text
http://localhost:2005
```

### 6. Test the API Using Postman

Import the Postman collection included in the project folder:

```text
student-internship-application-system-midterm.postman_collection.json
```

The collection contains requests for:

- CRUD operations
- Derived queries
- JPQL queries
- Business rules
- Application status updates
- Company field management
- Company booth management
- Placement management
- Application statistics
- Application cancellation

## Main API Features

### Students

The system supports:

- Saving a student
- Getting all students
- Getting a student by ID
- Updating a student
- Deleting a student
- Searching students by last name
- Getting students who applied to a specific company

### Companies

The system supports:

- Saving a company
- Getting all companies
- Getting a company by ID
- Updating a company
- Deleting a company
- Finding companies by field
- Adding a field to a company
- Changing a company's booth

### Booths

The system supports:

- Saving a booth
- Getting all booths
- Getting a booth by ID
- Updating a booth
- Deleting a booth
- Getting free booths

### Fields

The system supports:

- Saving a field
- Getting all fields
- Getting a field by ID
- Updating a field
- Deleting a field
- Getting unused fields

### Applications

The system supports:

- Saving an application
- Getting all applications
- Getting an application by ID
- Updating an application
- Deleting an application
- Finding applications by status
- Finding applications between two dates
- Updating application status
- Getting application statistics by status
- Cancelling applications for a company on a specific date

### Placements

The system supports:

- Saving a placement for an accepted application
- Getting placements by student registration number
- Deleting a placement
- Preventing deletion of an application that still has placements

## Business Rules

The project includes the following business rules:

- Application dates cannot be in the past.
- A company cannot interview more than one non-rejected applicant on the same date.
- New applications are automatically saved with `SUBMITTED` status.
- A `SUBMITTED` application can only move to `SHORTLISTED` or `REJECTED`.
- A `SHORTLISTED` application can only move to `ACCEPTED` or `REJECTED`.
- `ACCEPTED` and `REJECTED` applications are final.
- A company cannot have the same field more than once.
- A booth cannot be assigned to more than one company at the same time.
- Placements can only be created for `ACCEPTED` applications.
- An application with placements cannot be deleted until its placements are deleted.

## Important API Endpoints

### Students

```text
POST   /api/students/save
GET    /api/students/all
GET    /api/students/{id}
PUT    /api/students/update/{id}
DELETE /api/students/delete/{id}
GET    /api/students/by-last-name?lastName=rurangwa
GET    /api/students/of-company/{companyId}
```

### Companies

```text
POST   /api/companies/save
GET    /api/companies/all
GET    /api/companies/{id}
PUT    /api/companies/update/{id}
DELETE /api/companies/delete/{id}
GET    /api/companies/by-field?name=soft
PUT    /api/companies/{companyId}/fields/{fieldId}
PATCH  /api/companies/{companyId}/booth?boothNumber=4
```

### Booths

```text
POST   /api/booths/save
GET    /api/booths/all
GET    /api/booths/{id}
PUT    /api/booths/update/{id}
DELETE /api/booths/delete/{id}
GET    /api/booths/free
```

### Fields

```text
POST   /api/fields/save
GET    /api/fields/all
GET    /api/fields/{id}
PUT    /api/fields/update/{id}
DELETE /api/fields/delete/{id}
GET    /api/fields/unused
```

### Applications

```text
POST   /api/applications/save
GET    /api/applications/all
GET    /api/applications/{id}
PUT    /api/applications/update/{id}
DELETE /api/applications/delete/{id}
GET    /api/applications/by-status?status=SUBMITTED
GET    /api/applications/between?start=2026-10-01&end=2026-10-31
PATCH  /api/applications/{id}/status?status=SHORTLISTED
GET    /api/applications/stats/by-status
PATCH  /api/applications/cancel-day?companyId={companyId}&date=2026-10-20
```

### Placements

```text
POST   /api/placements/save?applicationId={applicationId}
GET    /api/placements/student/{regNumber}
DELETE /api/placements/delete/{id}
```

## Postman Collection

The exported Postman collection is included in the project root with the filename:

```text
student-internship-application-system-midterm.postman_collection.json
```

## GitHub Repository

```text
https://github.com/BKylie3/student-internship-application-system-backend
```