package com.eventastic.service;

import com.eventastic.model.*;
import com.eventastic.model.enums.RegistrationType;
import com.eventastic.repository.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RegistrationService {

    private final EventRepository eventRepository;
    private final ParticipantRepository participantRepository;
    private final RegistrationRepository registrationRepository;

    public RegistrationService(EventRepository eventRepository,
            ParticipantRepository participantRepository,
            RegistrationRepository registrationRepository) {
        this.eventRepository = eventRepository;
        this.participantRepository = participantRepository;
        this.registrationRepository = registrationRepository;
    }

    public Registration register(Event event, Participant participant,
            RegistrationType type, List<AddOnOption> addOns) {

        LocalDateTime now = LocalDateTime.now();

        // Quick null check so we don't crash later
        if (event == null || participant == null || type == null) {
            throw new IllegalArgumentException("Event, participant, and type cannot be null");
        }

        // 1) Is registration currently open?
        if (now.isBefore(event.getRegistrationStart()) ||
                now.isAfter(event.getRegistrationEnd())) {
            throw new IllegalStateException("Registration closed");
        }

        // 2) Capacity check (no overbooking)
        if (registrationRepository.countByEvent(event.getEventId()) >= event.getMaxParticipants()) {
            throw new IllegalStateException("Event full");
        }

        // 3) Find which phase is active right now (early bird / regular / etc.)
        RegistrationPhase activePhase = null;
        for (RegistrationPhase phase : event.getPhases()) {
            if (phase.isActive(now)) {
                activePhase = phase;
                break;
            }
        }
        
        if (activePhase == null) {
            throw new IllegalStateException("No active phase");
        }

        // 4) Find the base price for the selected registration type
        double basePrice = 0;
        for (RegistrationTypePrice price : activePhase.getPrices()) {
            if (price.getRegistrationType() == type) {
                basePrice = price.getAmount();
                break;
            }
        }
        
        if (basePrice == 0) {
            throw new IllegalStateException("No price for registration type");
        }

        double total = basePrice;

        // 5) Add-ons: increase total and also keep a list in the registration
        List<RegistrationAddOn> registrationAddOns = new ArrayList<>();
        
        if (addOns != null) {
            for (AddOnOption option : addOns) {
                total += option.getPrice();
                // We keep quantity as 1 here (simple version)
                registrationAddOns.add(new RegistrationAddOn(
                    UUID.randomUUID(), 
                    option, 
                    1
                ));
            }
        }

        // 6) Save participant (in our case: in-memory repo)
        participantRepository.save(participant);

        // 7) Create the registration object
        Registration registration = new Registration(
                UUID.randomUUID(), 
                event, 
                participant, 
                type, 
                total, 
                registrationAddOns
        );

        // 8) Save it and return
        registrationRepository.save(registration);
        return registration;
    }

    // Returns all registrations for one event
    public List<Registration> getEventRegistrations(UUID eventId) {
        return registrationRepository.findByEventId(eventId);
    }

    // Returns all registrations of one participant
    public List<Registration> getParticipantRegistrations(UUID participantId) {
        return registrationRepository.findByParticipantId(participantId);
    }

    // Finds one registration by id (returns null if not found)
    public Registration getRegistration(UUID registrationId) {
        return registrationRepository.findById(registrationId).orElse(null);
    }

    // How many people registered for this event?
    public long getEventRegistrationCount(UUID eventId) {
        return registrationRepository.countByEvent(eventId);
    }

    // Remaining capacity = max - current registrations
    public long getAvailableSpots(Event event) {
        long registeredCount = registrationRepository.countByEvent(event.getEventId());
        return event.getMaxParticipants() - registeredCount;
    }

    // Simple helper: is this participant already registered for the event?
    public boolean isParticipantRegistered(UUID eventId, UUID participantId) {
        return registrationRepository.findByEventIdAndParticipantId(eventId, participantId) != null;
    }
}