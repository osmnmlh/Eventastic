package com.eventastic.repository;

import com.eventastic.model.Registration;
import java.util.*;

public class InMemoryRegistrationRepository implements RegistrationRepository {
    private final Map<UUID, Registration> storage = new HashMap<>();

    @Override
    public Registration save(Registration registration) {
        // If registration has no ID, create one
        if (registration.getId() == null) {
            registration.setId(UUID.randomUUID());
        }
        // Store the registration in memory
        storage.put(registration.getId(), registration);
        return registration;
    }

    @Override
    public Optional<Registration> findById(UUID registrationId) {
        // Get registration by ID
        Registration registration = storage.get(registrationId);
        if (registration != null) {
            return Optional.of(registration);
        }
        return Optional.empty();
    }

    @Override
    public List<Registration> findByEventId(UUID eventId) {
        // Find all registrations for a specific event
        List<Registration> result = new ArrayList<>();
        for (Registration reg : storage.values()) {
            if (reg.getEvent().getEventId().equals(eventId)) {
                result.add(reg);
            }
        }
        return result;
    }

    @Override
    public List<Registration> findByParticipantId(UUID participantId) {
        // Find all registrations for a specific participant
        List<Registration> result = new ArrayList<>();
        for (Registration reg : storage.values()) {
            if (reg.getParticipant().getId().equals(participantId)) {
                result.add(reg);
            }
        }
        return result;
    }

    @Override
    public Registration findByEventIdAndParticipantId(UUID eventId, UUID participantId) {
        // Find a registration by both event and participant IDs
        for (Registration reg : storage.values()) {
            UUID regEventId = reg.getEvent().getEventId();
            UUID regParticipantId = reg.getParticipant().getId();
            
            if (regEventId.equals(eventId) && regParticipantId.equals(participantId)) {
                return reg;
            }
        }
        return null;
    }

    @Override
    public long countByEvent(UUID eventId) {
        // Count how many registrations exist for an event
        long count = 0;
        for (Registration reg : storage.values()) {
            if (reg.getEvent().getEventId().equals(eventId)) {
                count++;
            }
        }
        return count;
    }

    @Override
    public void delete(UUID registrationId) {
        // Remove registration from storage
        storage.remove(registrationId);
    }
}