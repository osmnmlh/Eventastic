package com.eventastic.repository;

import com.eventastic.model.Registration;
import java.util.*;
import java.util.stream.Collectors;

public class InMemoryRegistrationRepository implements RegistrationRepository {
    private final Map<UUID, Registration> storage = new HashMap<>();

    @Override
    public Registration save(Registration registration) {
        if (registration.getId() == null) {
            registration.setId(UUID.randomUUID());
        }
        storage.put(registration.getId(), registration);
        return registration;
    }

    @Override
    public Optional<Registration> findById(UUID registrationId) {
        return Optional.ofNullable(storage.get(registrationId));
    }

    @Override
    public List<Registration> findByEventId(UUID eventId) {
        return storage.values().stream()
                .filter(r -> r.getEvent().getEventId().equals(eventId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Registration> findByParticipantId(UUID participantId) {
        return storage.values().stream()
                .filter(r -> r.getParticipant().getId().equals(participantId))
                .collect(Collectors.toList());
    }

    @Override
    public Registration findByEventIdAndParticipantId(UUID eventId, UUID participantId) {
        return storage.values().stream()
                .filter(r -> r.getEvent().getEventId().equals(eventId) &&
                        r.getParticipant().getId().equals(participantId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public long countByEvent(UUID eventId) {
        return storage.values().stream()
                .filter(r -> r.getEvent().getEventId().equals(eventId))
                .count();
    }

    @Override
    public void delete(UUID registrationId) {
        storage.remove(registrationId);
    }
}