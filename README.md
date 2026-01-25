# Eventastic Backend - Event Registration System

## Project Information
**Course:** INF13204L – Software Development Methodologies  
**Assignment:** Part 2 - Implementation  
**Authors:** 
- Osman Melih Gümüş (ex68082)
- Teodora Kancheva (ex69739)
- Trayana Atanasova (ex68986)

**Date:** January 2026

---

## 1. What is Eventastic?

Eventastic is a **Java library** for managing event registrations. It allows:
- Creating events with registration periods
- Registering participants for events
- Managing pricing for different participant types
- Adding optional add-ons to registrations
- Calculating total registration costs

This is a **backend library** - it doesn't have a user interface. Other applications can use it to handle event management.

---

## 2. What We Built

### The Simple Version
We created three main parts:

1. **Models** (Data) - Classes that represent real things:
   - `Event` - An event happening at a specific time
   - `Participant` - A person registering for the event
   - `Registration` - When a participant joins an event

2. **Repositories** (Storage) - Where we keep our data:
   - We use memory (not a database) to store all information
   - Data is lost when the program stops

3. **Service** (Business Logic) - Rules for registrations:
   - Check if registration period is open
   - Verify event capacity isn't exceeded
   - Calculate prices based on participant type
   - Add extra costs from optional features

### What We Did NOT Implement (As Required)
✗ No login/authentication  
✗ No actual payment processing  
✗ No check-in system  

These features exist as data structures, but no actual logic was added.

---

## 3. How to Use It

### Setting Up
```java
// Create storage for our data
EventRepository eventRepo = new InMemoryEventRepository();
ParticipantRepository participantRepo = new InMemoryParticipantRepository();
RegistrationRepository registrationRepo = new InMemoryRegistrationRepository();

// Create the service that handles registrations
RegistrationService service = new RegistrationService(
    eventRepo, participantRepo, registrationRepo);
```

### Creating an Event
```java
Event event = new Event(
    UUID.randomUUID(),
    "Tech Conference 2026",
    "A conference about programming",
    LocalDateTime.of(2026, 3, 15, 9, 0),  // When it happens
    LocalDateTime.of(2026, 2, 1, 10, 0),  // Registration starts
    LocalDateTime.of(2026, 3, 10, 18, 0), // Registration ends
    100  // Maximum 100 people
);

// Add a pricing phase (e.g., early-bird discount)
RegistrationPhase phase = new RegistrationPhase(
    UUID.randomUUID(),
    "Early Bird",
    LocalDateTime.of(2026, 2, 1, 10, 0),
    LocalDateTime.of(2026, 2, 15, 18, 0)
);

// Add prices for different types of participants
phase.addPrice(new RegistrationTypePrice(
    UUID.randomUUID(), 
    RegistrationType.STUDENT, 
    25.0  // Students pay €25
));

event.addPhase(phase);
eventRepo.save(event);
```

### Registering a Participant
```java
// Create a person
Participant person = new Participant(
    UUID.randomUUID(),
    "John",
    "Doe",
    "john@example.com",
    "+351912345678"
);

// Register them for the event
Registration registration = service.register(
    event,
    person,
    RegistrationType.STUDENT,
    List.of()  // No add-ons
);

System.out.println("Total cost: €" + registration.getTotalAmount());
```

### Checking Registrations
```java
// Get all registrations for an event
List<Registration> allReg = service.getEventRegistrations(event.getId());

// Check available spots
long availableSpots = service.getAvailableSpots(event);

// See if someone is already registered
boolean isRegistered = service.isParticipantRegistered(eventId, participantId);
```

---

## 4. Project Structure

```
Eventastic-Backend/
├── pom.xml                    (Maven build configuration)
├── README.md                  (This file)
│
└── src/main/java/com/eventastic/
    ├── Main.java              (Example usage)
    │
    ├── model/                 (Data structures)
    │   ├── Event.java
    │   ├── Participant.java
    │   ├── Registration.java
    │   ├── RegistrationPhase.java
    │   ├── AddOnOption.java
    │   ├── Payment.java
    │   ├── CheckIn.java
    │   └── enums/
    │       ├── RegistrationType.java
    │       └── RegistrationStatus.java
    │
    ├── repository/            (Data storage)
    │   ├── EventRepository.java
    │   ├── ParticipantRepository.java
    │   ├── RegistrationRepository.java
    │   └── InMemory*.java (3 implementations)
    │
    └── service/               (Business logic)
        └── RegistrationService.java
```

---

## 5. How It Works - Step by Step

### When Someone Registers:

1. **Validate the input** - Check that event, participant, and type are not null
2. **Check registration period** - Is registration open right now?
3. **Check capacity** - Is there room for more people?
4. **Find the active pricing phase** - What prices apply today?
5. **Get the base price** - How much does this participant type cost?
6. **Add extras** - If they chose add-ons, add those costs
7. **Save the participant** - Store their information
8. **Create the registration** - Link person to event
9. **Return the registration** - Give back the complete registration with total cost

If any step fails, the system throws an error with a clear message.

---

## 6. Design Patterns Used

### 1. **Repository Pattern**
Instead of spreading data-access code everywhere, we centralize it:
- All saving/loading goes through repositories
- Easy to swap from memory to database later
- Keeps business logic separate from data storage

### 2. **Service Layer Pattern**
All complex logic is in one place (RegistrationService):
- Models stay simple (just data)
- Repositories only handle storage
- Service handles the rules

### 3. **Dependency Injection**
Service gets repositories in its constructor:
```java
public RegistrationService(EventRepository eventRepo,
                          ParticipantRepository participantRepo,
                          RegistrationRepository registrationRepo)
```
- Easy to test (can give fake repositories)
- Easy to change implementations
- Clear what the service needs

---

## 7. Building and Running

### Build the project:
```bash
mvn clean compile
```

### Run the example:
```bash
mvn exec:java -Dexec.mainClass=com.eventastic.Main
```

### Expected output:
```
Registration successful
Total: 50.0
```

### Package as JAR:
```bash
mvn clean package
```

---

## 8. Key Features & Tips for Discussion ✅

### What We Did Well

1. **Clean Architecture** - Three layers (Model, Repository, Service) with clear responsibilities
2. **No External Dependencies** - Uses only Java standard library
3. **Type-Safe** - Uses UUIDs for IDs, enums for types
4. **Reusable** - Can be imported and used by any Java application
5. **Testable** - Each layer can be tested independently
6. **Version Control** - Regular Git commits showing progress

### 💡 Tips for Presenting to Professor

1. **Explain the layers:**
   - "Models are like the blueprint - they just hold data"
   - "Repositories are like a filing cabinet - they store and retrieve"
   - "Service is like the manager - it enforces all the rules"

2. **Show the validation logic:**
   - Explain why we check registration dates
   - Show why we need to verify capacity
   - Demonstrate price calculation with add-ons

3. **Discuss design choices:**
   - Why in-memory storage? (Simpler, no database needed)
   - Why use interfaces? (Easy to swap implementations later)
   - Why separate service from models? (Keeps code organized)

4. **Show what was skipped:**
   - "We didn't implement authentication because it's not required..."
   - "Payment processing is just a data structure because..."
   - "Check-in logic wasn't required, but the model supports it"

5. **Mention improvements:**
   - Could add database support
   - Could add logging
   - Could add email notifications
   - Could add search/filter features

### 🔍 Code Examples to Show

**Simple usage (Main.java):**
- Creating an event
- Registering someone
- Showing the calculated total

**Registration validation (RegistrationService):**
- Step-by-step registration process
- Error checking
- Price calculation

**Repository pattern (InMemoryRegistrationRepository):**
- How data is stored
- Simple for-loops for searching
- Clear retrieval methods

---

## 9. What We Learned

### Design Patterns
- Repository Pattern for data access
- Service Layer for business logic
- Dependency Injection for flexibility

### Java Concepts
- Collections (HashMap, ArrayList)
- Interfaces and implementations
- UUID for unique identification
- LocalDateTime for date/time
- Exception handling
- Enumerations

### Software Engineering
- Layered architecture
- Separation of concerns
- Testing via dependency injection
- Git version control
- Maven build automation

---

## 10. Challenges & Solutions

| Challenge | What We Did |
|-----------|-----------|
| How to store data without a database? | Used HashMap in memory |
| How to find registrations quickly? | Used UUID as key in HashMap |
| How to handle dates/times? | Used Java's LocalDateTime |
| How to validate registrations? | Created validation in RegistrationService |
| How to organize complex code? | Split into Model, Repository, Service layers |

---

## 11. What We Could Do Next

If we had more time, we could add:

1. **Database Integration** - Use JPA/Hibernate instead of memory
2. **Unit Tests** - Write JUnit tests for each class
3. **Logging** - Add SLF4J to track what's happening
4. **Email Notifications** - Send confirmation emails
5. **Search Features** - Find events by name, date range, etc.
6. **Cancellation** - Allow participants to cancel registrations
7. **Refunds** - Automatic refund calculation
8. **Multiple Venues** - Support events at different locations

---

## 12. How to Run & Test

### Prerequisites
- Java 17 or newer
- Maven 3.9.0 or newer

### Build
```bash
mvn clean compile
```

### Test the Library
```bash
mvn exec:java -Dexec.mainClass=com.eventastic.Main
```

### Check for Errors
```bash
mvn clean compile
```
(If this succeeds, all code is correct)

### Package It
```bash
mvn clean package
```
This creates a `.jar` file in the `target/` folder.

---

## 13. Quick File Overview

| File | Purpose |
|------|---------|
| `Main.java` | Example of how to use the library |
| `Event.java` | Data class for events |
| `Participant.java` | Data class for participants |
| `Registration.java` | Links participant to event |
| `RegistrationService.java` | Contains all business logic |
| `InMemoryEventRepository.java` | Stores events in memory |
| `InMemoryParticipantRepository.java` | Stores participants in memory |
| `InMemoryRegistrationRepository.java` | Stores registrations in memory |

---

## 14. Questions You Might Get

**Q: Why not use a real database?**  
A: The assignment specified in-memory storage. It keeps things simple for learning.

**Q: Why these three layers?**  
A: It's a standard pattern. Models hold data, Repositories store it, Services handle business rules.

**Q: Can this be used in production?**  
A: Not as-is (data is lost), but the architecture is production-ready. Just swap the repositories for database versions.

**Q: Why UUIDs instead of auto-incrementing IDs?**  
A: UUIDs are unique across systems and don't require a database sequence.

**Q: What about thread safety?**  
A: Not implemented because the assignment doesn't require it. Could use ConcurrentHashMap if needed.

---

## 15. Conclusion

Eventastic Backend shows how to build a **clean, organized Java library** that:
- Follows standard software architecture patterns
- Uses design patterns (Repository, Service)
- Is easy to understand and modify
- Can grow into a larger system
- Demonstrates good software engineering practices

The code is **simple enough for students to understand** but **professional enough to be useful**.

---

**Questions? Let's discuss! 💬**
