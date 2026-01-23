package com.eventastic.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Event {
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
    }

    public UUID getEventId() {
        return eventId;
    }

    public void setEventId(UUID eventId) {
        this.eventId = eventId;
    }

    public String getName() {
        return name;
    }

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

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public List<RegistrationPhase> getPhases() {
        return phases;
    }

    public void setPhases(List<RegistrationPhase> phases) {
        this.phases = phases;
    }

    public void addPhase(RegistrationPhase phase) {
        this.phases.add(phase);
    }
}