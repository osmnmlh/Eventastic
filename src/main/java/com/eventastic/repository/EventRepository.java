package com.eventastic.repository;

import com.eventastic.model.Event;
import java.util.*;

public class EventRepository {

    private final Map<UUID, Event> events = new HashMap<>();

    public void save(Event event) {
        events.put(event.getEventId(), event);
    }

    public Optional<Event> findById(UUID id) {
        return Optional.ofNullable(events.get(id));
    }
}
