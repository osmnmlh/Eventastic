package com.eventastic.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Event {
<<<<<<< HEAD
    private UUID eventId;
    private String name;
    private String description;
    private LocalDateTime eventDate;
    private LocalDateTime registrationStart;
    private LocalDateTime registrationEnd;
    private int maxParticipants;
    private List<RegistrationPhase> phases;

    public Event() {
        this.phases = new ArrayList<>();
    }

    public Event(UUID eventId, String name, String description, LocalDateTime eventDate,
                 LocalDateTime registrationStart, LocalDateTime registrationEnd, int maxParticipants) {
        this.eventId = eventId;
        this.name = name;
        this.description = description;
        this.eventDate = eventDate;
        this.registrationStart = registrationStart;
        this.registrationEnd = registrationEnd;
        this.maxParticipants = maxParticipants;
        this.phases = new ArrayList<>();
=======

    private UUID eventId;
    private String name;
    private String description;
    private String location;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private int maxParticipants;
    private LocalDateTime registrationStart;
    private LocalDateTime registrationEnd;
    private boolean allowOnSitePayment;

    private List<RegistrationPhase> phases = new ArrayList<>();
    private List<AddOnOption> addOnOptions = new ArrayList<>();

    public Event(UUID eventId, String name, String description, String location,
            LocalDateTime startDateTime, LocalDateTime endDateTime,
            int maxParticipants, LocalDateTime registrationStart,
            LocalDateTime registrationEnd, boolean allowOnSitePayment) {
        this.eventId = eventId;
        this.name = name;
        this.description = description;
        this.location = location;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.maxParticipants = maxParticipants;
        this.registrationStart = registrationStart;
        this.registrationEnd = registrationEnd;
        this.allowOnSitePayment = allowOnSitePayment;
>>>>>>> e25fc9ab6595f9805deac69870c54ed661f03466
    }

    public UUID getEventId() {
        return eventId;
    }

<<<<<<< HEAD
    public void setEventId(UUID eventId) {
        this.eventId = eventId;
    }

=======
>>>>>>> e25fc9ab6595f9805deac69870c54ed661f03466
    public String getName() {
        return name;
    }

<<<<<<< HEAD
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDateTime eventDate) {
        this.eventDate = eventDate;
    }

    public LocalDateTime getRegistrationStart() {
        return registrationStart;
    }

    public void setRegistrationStart(LocalDateTime registrationStart) {
        this.registrationStart = registrationStart;
    }

    public LocalDateTime getRegistrationEnd() {
        return registrationEnd;
    }

    public void setRegistrationEnd(LocalDateTime registrationEnd) {
        this.registrationEnd = registrationEnd;
    }

=======
>>>>>>> e25fc9ab6595f9805deac69870c54ed661f03466
    public int getMaxParticipants() {
        return maxParticipants;
    }

<<<<<<< HEAD
    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
=======
    public LocalDateTime getRegistrationStart() {
        return registrationStart;
    }

    public LocalDateTime getRegistrationEnd() {
        return registrationEnd;
>>>>>>> e25fc9ab6595f9805deac69870c54ed661f03466
    }

    public List<RegistrationPhase> getPhases() {
        return phases;
    }

<<<<<<< HEAD
    public void setPhases(List<RegistrationPhase> phases) {
        this.phases = phases;
    }

    public void addPhase(RegistrationPhase phase) {
        this.phases.add(phase);
    }
}
=======
    public void addRegistrationPhase(RegistrationPhase phase) {
        phases.add(phase);
    }
}
>>>>>>> e25fc9ab6595f9805deac69870c54ed661f03466
