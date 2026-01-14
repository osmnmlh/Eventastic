# Eventastic Backend – Part 2 Implementation

## Course
INF13204L – Metodologias e Desenvolvimento de Software  
Practical Work – Part 2 (Implementation)

## Authors
- Osman Melih Gümüş (ex68082)  
- Teodora Kancheva (ex69739)  
- Trayana Atanasova (ex68986)

---

## 1. Project Overview

This project implements the backend of the **Eventastic** system, based on the requirements, use cases, and class diagram developed in Part 1 of the assignment.

The backend is implemented as a **Java library**, intended to be imported and used by another application.  
It provides methods for managing events, participants, and registrations, following the defined business rules.

No user interface is included.

---

## 2. Scope and Restrictions

According to the assignment specification, the following features are **explicitly not implemented**:

- Authentication / login
- Payment confirmation
- Participant check-in

Although these concepts exist in the data model (Payment, CheckIn), they are implemented as **data structures only**, without any business logic.

Data persistence is handled **in memory**, using standard Java collections.  
No database or external storage system is used.

---

## 3. Architecture and Design Decisions

The project follows a layered architecture:

### Model Layer (`com.eventastic.model`)
Contains all domain entities defined in Part 1, such as:
- Event
- Registration
- Participant
- Payment
- CheckIn
- RegistrationPhase
- AddOnOption

Enumerations representing domain concepts are located in:

### Repository Layer (`com.eventastic.repository`)
Repositories manage in-memory storage using Java collections:
- EventRepository
- ParticipantRepository
- RegistrationRepository

These classes are responsible only for data storage and retrieval.

### Service Layer (`com.eventastic.service`)
The service layer contains the application’s business logic.

`RegistrationService` is responsible for:
- Validating registration periods
- Enforcing event capacity limits
- Calculating registration prices
- Creating registrations with status `PENDING_PAYMENT`

All business rules are implemented in this layer.

---

## 4. Build and Dependency Management

The project uses **Maven** to manage dependencies and build configuration.

Standard Maven directory structure is followed:
src/main/java
src/test/java


The project can be built using:
mvn clean package


---

## 5. Version Control

Git was used for version control throughout the project.

- The implementation was divided into multiple GitHub Issues
- Each issue corresponds to a specific development task
- Regular commits and pushes were made to the GitHub repository
- The `main` branch contains the stable implementation

---

## 6. Main Class – Library Usage Example

A `Main` class is provided to demonstrate how an external application can use the Eventastic backend library.

The example includes:
- Creating an event
- Defining registration phases and prices
- Adding optional add-ons
- Registering a participant
- Displaying the calculated total registration price

This class serves purely as a usage example and not as a user interface.

---

## 7. Conclusion

This implementation fulfills all the requirements defined for Part 2 of the assignment.  
It respects the constraints, follows the design specified in Part 1, and demonstrates correct use of software engineering principles such as modularity, separation of concerns, and version control.

The backend is ready to be integrated into a larger application.
