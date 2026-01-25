# Eventastic Backend (Java) – Event Registration Library

## Project info
Course: INF13204L – Software Development Methodologies  
Assignment: Part 2 – Implementation  
Authors:
- Osman Melih Gümüş (ex68082)
- Teodora Kancheva (ex69739)
- Trayana Atanasova (ex68986)
Date: January 2026

---

## What is this project?
In this project we built a small **Java backend library** for event registrations.
There is no UI here. The idea is that another program can use this library to:
- create events and registration periods
- register participants
- calculate price based on participant type (student / regular / professional)
- add optional add-ons and include them in the total

---

## What we implemented (and what we did not)

Implemented:
- event creation with capacity
- registration phases (time window + prices)
- participant registration with validations
- total cost calculation (base price + add-ons)
- in-memory repositories (no database)

Not implemented (not required in this assignment):
- authentication / login
- real payment processing
- check-in logic

We still have some classes like `Payment` and `CheckIn`, but they are only data structures.

---

## Decisions (short report)
This section is a quick summary of the main decisions we made while implementing Part 2.

- **Language**: Java (we used Java 17) because it is stable, common, and fits the course.
- **Build tool**: Maven, so anyone can build/package the project in a standard way.
- **Library style**: We did not build a UI. The goal is a backend library that another app can call.
- **No database**: We store data in memory using basic structures (HashMap / ArrayList) as required.
- **Project structure**: We separated code into `model`, `repository`, and `service` to keep it readable.
- **Public API**: The main “operations” are callable methods (for example `RegistrationService.register(...)` and repository `save/find` methods).
- **Dates and IDs**: We used `LocalDateTime` for registration windows and `UUID` for unique IDs.

---

## Project management (Git / GitHub)
- We used **regular commits** to track progress.
- We worked with **branches** and merged changes back to `main`.
- We created GitHub **Issues** for the main tasks (model, repositories, service logic, main demo, README). You can check them here:
   https://github.com/osmnmlh/Eventastic/issues

---

## How to run

Prerequisites:
- Java 17+
- Maven 3.9+

Build:
```bash
mvn clean compile
```

Run the example `Main` class:
```bash
mvn exec:java -Dexec.mainClass=com.eventastic.Main
```

Package as a JAR:
```bash
mvn clean package
```

---

## Quick usage example

Create repositories and the service:
```java
EventRepository eventRepo = new InMemoryEventRepository();
ParticipantRepository participantRepo = new InMemoryParticipantRepository();
RegistrationRepository registrationRepo = new InMemoryRegistrationRepository();

RegistrationService service = new RegistrationService(
   eventRepo, participantRepo, registrationRepo
);
```

Create an event + one pricing phase:
```java
Event event = new Event(
   UUID.randomUUID(),
   "Tech Conference 2026",
   "A conference about programming",
   LocalDateTime.of(2026, 3, 15, 9, 0),
   LocalDateTime.of(2026, 2, 1, 10, 0),
   LocalDateTime.of(2026, 3, 10, 18, 0),
   100
);

RegistrationPhase phase = new RegistrationPhase(
   UUID.randomUUID(),
   "Early Bird",
   LocalDateTime.of(2026, 2, 1, 10, 0),
   LocalDateTime.of(2026, 2, 15, 18, 0)
);

phase.addPrice(new RegistrationTypePrice(
   UUID.randomUUID(),
   RegistrationType.STUDENT,
   25.0
));

event.addPhase(phase);
eventRepo.save(event);
```

Register a participant:
```java
Participant person = new Participant(
   UUID.randomUUID(),
   "John",
   "Doe",
   "john@example.com",
   "+351912345678"
);

Registration reg = service.register(
   event,
   person,
   RegistrationType.STUDENT,
   List.of()
);

System.out.println("Total cost: " + reg.getTotalAmount());
```

---

## How we organized the code
We kept it simple with three layers:
- **model**: plain classes like `Event`, `Participant`, `Registration`
- **repository**: in-memory storage (`InMemoryEventRepository`, etc.)
- **service**: business rules (`RegistrationService`)

Project structure:
```
Eventastic-Backend/
├── pom.xml
├── README.md
└── src/main/java/com/eventastic/
   ├── Main.java
   ├── model/
   ├── repository/
   └── service/
```

---

## What happens during registration?
When we call `service.register(...)`, we do these steps:
1. check inputs are not null
2. check if registration is open (based on dates)
3. check event capacity
4. find the active phase for the current date
5. get the base price for the participant type
6. add add-on prices if selected
7. save participant + registration in repositories

If something is wrong (for example: registration closed, no phase, no price), we throw an error message.

---

## What we learned
- organizing code into layers makes it easier to understand
- using interfaces for repositories makes the service independent from storage
- working with `UUID`, `LocalDateTime`, and collections in Java
- validating inputs and handling errors clearly

---

## Possible improvements (if we had more time)
- add JUnit tests
- replace in-memory storage with a database (JPA/Hibernate)
- add cancellation/refund flow
- add logs for debugging
