package com.eventastic.repository;

import com.eventastic.model.Event;
<<<<<<< HEAD

import java.util.Optional;
import java.util.UUID;

public interface EventRepository {
    Event save(Event event);
    Optional<Event> findById(UUID eventId);
    void delete(UUID eventId);
}
=======
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
>>>>>>> e25fc9ab6595f9805deac69870c54ed661f03466
