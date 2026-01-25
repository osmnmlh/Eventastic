package com.eventastic.repository;

import com.eventastic.model.Participant;
import java.util.*;

public class InMemoryParticipantRepository implements ParticipantRepository {
    private final Map<UUID, Participant> storage = new HashMap<>();

    @Override
    public Participant save(Participant participant) {
        if (participant.getId() == null) {
            participant.setId(UUID.randomUUID());
        }
        storage.put(participant.getId(), participant);
        return participant;
    }

    @Override
    public Optional<Participant> findById(UUID participantId) {
        return Optional.ofNullable(storage.get(participantId));
    }

    @Override
    public Optional<Participant> findByEmail(String email) {
        return storage.values().stream()
                .filter(p -> p.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    @Override
    public void delete(UUID participantId) {
        storage.remove(participantId);
    }
}