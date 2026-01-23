package com.eventastic.repository;

import com.eventastic.model.Registration;

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
