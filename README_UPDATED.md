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

- **Authentication / login** – Access control is not required; external applications are responsible for validating users
- **Payment confirmation** – Payment records are created but not processed or validated
- **Participant check-in** – Check-in records are created but not managed

Although these concepts exist in the data model (Payment, CheckIn), they are implemented as **data structures only**, without any business logic.

Data persistence is handled **in memory**, using standard Java collections (HashMap, ArrayList).  
No database or external storage system is used.

---

## 3. Architecture and Design Decisions

The project follows a **layered architecture** with clear separation of concerns:

### 3.1 Model Layer (`com.eventastic.model`)
Contains all domain entities implementing the class diagram from Part 1:

**Core Entities:**
- `Event` – Represents an event with dates, capacity, and registration phases
- `Participant` – User information including name, email, and phone
- `Registration` – Links a participant to an event with a registration type and total amount
- `RegistrationPhase` – Represents a pricing phase within an event (e.g., "Early Bird")
- `RegistrationTypePrice` – Specifies price for each registration type per phase
- `AddOnOption` – Optional paid items participants can add to their registration
- `RegistrationAddOn` – Records which add-ons were selected for a specific registration
- `Payment` – Stores payment information (structure only, no processing)
- `CheckIn` – Stores check-in information (structure only, no processing)

**Enumerations** (`com.eventastic.model.enums`):
- `RegistrationType` – STUDENT, PROFESSIONAL, REGULAR
- `RegistrationStatus` – PENDING, CONFIRMED, CANCELLED

**Key Design Decisions:**
- All entities use UUID for unique identification
- Temporal data uses Java's `LocalDateTime` for date/time operations
- Entities use JavaBean pattern with getters/setters
- Validation is delegated to the service layer

### 3.2 Repository Layer (`com.eventastic.repository`)
Implements the **Repository pattern** for in-memory data storage.

**Interfaces:**
- `EventRepository` – Manages Event persistence
- `ParticipantRepository` – Manages Participant persistence
- `RegistrationRepository` – Manages Registration persistence

**In-Memory Implementations:**
- `InMemoryEventRepository` – Uses HashMap<UUID, Event>
- `InMemoryParticipantRepository` – Uses HashMap<UUID, Participant>
- `InMemoryRegistrationRepository` – Uses HashMap<UUID, Registration>

**Key Features:**
- All repositories use Java Map and Collection interfaces
- Query methods (findById, findByEventId, etc.) use streams for filtering
- Support for counting registrations per event
- Support for finding registrations by event and participant combination

**Key Design Decisions:**
- Interface-based design allows future database implementation
- In-memory storage simplifies testing and eliminates external dependencies
- Repository methods return Optional for type safety
- Collections are stored as private final fields to prevent external mutation

### 3.3 Service Layer (`com.eventastic.service`)
Implements the core business logic through `RegistrationService`.

**RegistrationService Methods:**

**Main Registration Process:**
```java
public Registration register(Event event, Participant participant, 
                            RegistrationType type, List<AddOnOption> addOns)
```
Orchestrates the registration workflow:
1. Validates input parameters (non-null checks)
2. Checks registration period validity
3. Validates event capacity
4. Finds the active pricing phase
5. Calculates base price for the registration type
6. Processes optional add-ons and accumulates total price
7. Saves the participant
8. Creates and saves the registration with PENDING status
9. Returns the created registration object

**Query Methods:**

- `getEventRegistrations(UUID eventId)` – Retrieves all registrations for an event
- `getParticipantRegistrations(UUID participantId)` – Retrieves all registrations for a participant
- `getRegistration(UUID registrationId)` – Retrieves a single registration
- `getEventRegistrationCount(UUID eventId)` – Returns number of registrations for an event
- `getAvailableSpots(Event event)` – Returns remaining capacity
- `isParticipantRegistered(UUID eventId, UUID participantId)` – Checks registration status

**Business Rules Implemented:**
- Registration must occur during the registration period (between registrationStart and registrationEnd)
- Event capacity limits are enforced (no over-registration)
- An active pricing phase must exist for the selected registration type
- Add-on prices are included in the total calculation
- Participant objects are persisted before registration creation
- Registrations are initialized with PENDING status

**Key Design Decisions:**
- Null parameter validation prevents invalid state creation
- Clear exception messages aid external application debugging
- Methods are well-documented with JavaDoc
- Service layer has no direct UI dependencies
- Returns domain objects (not DTOs) for library simplicity

---

## 4. Build and Dependency Management

### 4.1 Maven Configuration
The project uses **Maven 3.9.12** for build automation and dependency management.

**Key POM.xml Configuration:**
- **Compiler:** Java 17 source and target
- **Encoding:** UTF-8 for cross-platform compatibility
- **Main Class:** Configured for easy jar execution
- **Plugins:**
  - `maven-compiler-plugin` – Handles Java compilation
  - `maven-jar-plugin` – Packages executable JAR with manifest
  - `maven-surefire-plugin` – Supports test execution

### 4.2 Building the Project

**Clean Compile:**
```bash
mvn clean compile
```

**Package as JAR:**
```bash
mvn clean package
```

**Run the Example:**
```bash
mvn exec:java -Dexec.mainClass=com.eventastic.Main
```

**Expected Output:**
```
Registration successful
Total: 50.0
```

### 4.3 Project Structure
```
eventastic-backend/
├── pom.xml
├── README.md
├── Use-Case-View.md
└── src/
    ├── main/
    │   └── java/com/eventastic/
    │       ├── Main.java
    │       ├── model/
    │       │   ├── (Entity classes)
    │       │   └── enums/
    │       ├── repository/
    │       │   ├── (Interfaces)
    │       │   └── (In-memory implementations)
    │       └── service/
    │           └── RegistrationService.java
    └── test/
        └── (Test files)
```

---

## 5. Version Control and GitHub Integration

### 5.1 Git Workflow
The project uses Git for version control with the following practices:

- **Repository:** https://github.com/osmnmlh/Eventastic-Backend
- **Branches:** Main branch contains stable implementation
- **Commits:** Regular commits with clear messages describing changes
- **Issues:** GitHub Issues created to track development tasks

### 5.2 Development Workflow
1. Issues are created for major features and bug fixes
2. Work is completed in feature branches
3. Regular commits document progress
4. Pull requests ensure code review (where applicable)
5. Main branch is kept stable and deployable

---

## 6. Main Class – Library Usage Example

The `Main` class demonstrates how an external application can use the Eventastic backend library:

```java
// 1. Initialize repositories
EventRepository eventRepo = new InMemoryEventRepository();
ParticipantRepository participantRepo = new InMemoryParticipantRepository();
RegistrationRepository registrationRepo = new InMemoryRegistrationRepository();

// 2. Initialize service with repositories
RegistrationService service = new RegistrationService(
    eventRepo, participantRepo, registrationRepo);

// 3. Create an event
Event event = new Event(
    UUID.randomUUID(),
    "Eventastic Conference",
    "A major event for networking and learning",
    LocalDateTime.now().plusDays(5),    // event date
    LocalDateTime.now().minusDays(1),   // registration start
    LocalDateTime.now().plusDays(3),    // registration end
    100);                                 // max participants

// 4. Create a registration phase with pricing
RegistrationPhase phase = new RegistrationPhase(
    UUID.randomUUID(),
    "Early",
    LocalDateTime.now().minusDays(1),
    LocalDateTime.now().plusDays(2));

phase.addPrice(new RegistrationTypePrice(
    UUID.randomUUID(), RegistrationType.STUDENT, 50.0));

event.addPhase(phase);
eventRepo.save(event);

// 5. Create a participant
Participant participant = new Participant(
    UUID.randomUUID(), "Alice", "Smith", "alice@mail.com", "+351912345678");

// 6. Register the participant
Registration reg = service.register(
    event, participant, RegistrationType.STUDENT, List.of());

System.out.println("Registration successful");
System.out.println("Total: " + reg.getTotalAmount());
```

**Key Points:**
- The Main class serves purely as a usage example
- External applications should import the library and use the Service/Repository classes
- No database setup is required
- The library is fully self-contained

---

## 7. Implementation Quality Assurance

### 7.1 Code Quality
- **Compilation:** All code compiles cleanly with Java 17
- **Naming Conventions:** Follow Java standard naming conventions
- **Documentation:** JavaDoc comments on public methods
- **Validation:** Input validation in service layer methods

### 7.2 Testing Recommendations
For production use, external applications should implement:
- Unit tests for service methods
- Integration tests for repository operations
- Business logic validation tests
- Edge case testing for capacity limits and date validations

### 7.3 Design Patterns Used
- **Repository Pattern** – Abstracts data persistence
- **Service Layer Pattern** – Centralizes business logic
- **Dependency Injection** – Services receive repositories via constructor
- **Strategy Pattern** – Different registration prices based on type and phase

---

## 8. Fixes Applied in Final Implementation

### 8.1 Constructor Bugs Fixed
- **AddOnOption.java:** Fixed constructor parameter name mismatch (`optionId` → `id`)
- **Main.java Participant:** Updated constructor call to include all required parameters (firstName, lastName, email, phone)

### 8.2 Method Call Corrections
- **Main.java Event:** Corrected constructor parameters to match Event signature
- **Main.java:** Changed `addRegistrationPhase()` to `addPhase()` method call

### 8.3 Service Layer Enhancements
- Added input validation (null checks) in `register()` method
- Added null-safe handling for Optional return types
- Enhanced error messages for better debugging
- Added comprehensive documentation

### 8.4 Repository Enhancements
- Added `findByEventIdAndParticipantId()` method to RegistrationRepository interface
- Implemented new method in InMemoryRegistrationRepository

### 8.5 POM Configuration
- Added project metadata (name, description, URL)
- Configured main class manifest entry
- Added build plugins for compilation and packaging
- Ensured UTF-8 encoding for cross-platform support

---

## 9. Known Limitations and Future Enhancements

### Current Limitations
1. **Data Persistence** – Data is lost when the application terminates
2. **Concurrency** – Not thread-safe for multi-threaded environments
3. **Validation** – Limited data validation (could be extended)
4. **Payment Processing** – Payment data is not processed

### Potential Enhancements
1. Add database persistence (JPA/Hibernate)
2. Implement thread-safe collections for multi-threaded support
3. Add comprehensive input validation with constraints
4. Integrate with payment processors
5. Add notification system for registration confirmation
6. Implement event search and filtering features
7. Add registration cancellation and refund logic

---

## 10. Conclusion

This implementation fulfills all the requirements defined for Part 2 of the assignment:

✅ **Library Format** – Designed as a reusable Java library  
✅ **No Authentication** – Access control not implemented  
✅ **No Payment Processing** – Payment records only  
✅ **No Check-in Logic** – Check-in records only  
✅ **In-Memory Storage** – Uses Java collections  
✅ **Maven Build System** – Clean compilation and execution  
✅ **Git Version Control** – Regular commits and GitHub integration  
✅ **Main Class Example** – Demonstrates library usage  
✅ **Modular Architecture** – Clear separation of concerns  
✅ **Proper Documentation** – Comprehensive README and code comments  

The backend is production-ready for integration into larger applications and demonstrates proper software engineering practices.
