package com.eventastic.repository;

import com.eventastic.model.Event;
import java.util.*;

public class InMemoryEventRepository implements EventRepository {
    // In-memory storage
    private final Map<UUID, Event> storage = new HashMap<>();

    @Override
    public Event save(Event event) {
        if (event.getEventId() == null) {
            event.setEventId(UUID.randomUUID());
        }
        storage.put(event.getEventId(), event);
        return event;
    }

    @Override
    public Optional<Event> findById(UUID eventId) {
        return Optional.ofNullable(storage.get(eventId));
    }

    @Override
    public void delete(UUID eventId) {
        storage.remove(eventId);
    }
}