package com.eventastic.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Event {

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
    }

    public UUID getEventId() {
        return eventId;
    }

    public String getName() {
        return name;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public LocalDateTime getRegistrationStart() {
        return registrationStart;
    }

    public LocalDateTime getRegistrationEnd() {
        return registrationEnd;
    }

    public List<RegistrationPhase> getPhases() {
        return phases;
    }

    public void addRegistrationPhase(RegistrationPhase phase) {
        phases.add(phase);
    }
}
