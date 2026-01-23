package com.eventastic.repository;

import com.eventastic.model.Event;

import java.util.Optional;
import java.util.UUID;

public interface EventRepository {
    Event save(Event event);
    Optional<Event> findById(UUID eventId);
    void delete(UUID eventId);
}
