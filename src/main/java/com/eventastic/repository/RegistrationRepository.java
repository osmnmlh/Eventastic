package com.eventastic.repository;

import com.eventastic.model.Registration;
<<<<<<< HEAD

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RegistrationRepository {
    Registration save(Registration registration);
    Optional<Registration> findById(UUID registrationId);
    List<Registration> findByEventId(UUID eventId);
    List<Registration> findByParticipantId(UUID participantId);
    long countByEvent(UUID eventId);
    void delete(UUID registrationId);
}
=======
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
>>>>>>> e25fc9ab6595f9805deac69870c54ed661f03466
