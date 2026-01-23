package com.eventastic.repository;

import com.eventastic.model.Participant;

import java.util.Optional;
import java.util.UUID;

public interface ParticipantRepository {
    Participant save(Participant participant);
    Optional<Participant> findById(UUID participantId);
    Optional<Participant> findByEmail(String email);
    void delete(UUID participantId);
}
