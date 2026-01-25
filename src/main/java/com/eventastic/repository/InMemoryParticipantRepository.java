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
        // Get participant by ID
        Participant participant = storage.get(participantId);
        if (participant != null) {
            return Optional.of(participant);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Participant> findByEmail(String email) {
        // Search through all participants to find one with matching email
        for (Participant p : storage.values()) {
            if (p.getEmail() != null && p.getEmail().equalsIgnoreCase(email)) {
                return Optional.of(p);
            }
        }
        return Optional.empty();
    }

    @Override
    public void delete(UUID participantId) {
        storage.remove(participantId);
    }
}