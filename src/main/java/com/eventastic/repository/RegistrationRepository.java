package com.eventastic.repository;

import com.eventastic.model.Registration;
import java.util.*;

public class RegistrationRepository {

    private final Map<UUID, Registration> registrations = new HashMap<>();

    public void save(Registration registration) {
        registrations.put(registration.getRegistrationId(), registration);
    }

    public long countByEvent(UUID eventId) {
        return registrations.values().stream()
                .filter(r -> r.getEvent().getEventId().equals(eventId))
                .count();
    }
}
