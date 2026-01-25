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

        // Validate input
        if (event == null || participant == null || type == null) {
            throw new IllegalArgumentException("Event, participant, and type cannot be null");
        }

        // 1. Validate Event Dates
        if (now.isBefore(event.getRegistrationStart()) ||
                now.isAfter(event.getRegistrationEnd())) {
            throw new IllegalStateException("Registration closed");
        }

        // 2. Validate Capacity
        if (registrationRepository.countByEvent(event.getEventId()) >= event.getMaxParticipants()) {
            throw new IllegalStateException("Event full");
        }

        // 3. Find Active Phase
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

        // 4. Get Base Price
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

        // 5. Process Add-Ons and Create RegistrationAddOn objects
        List<RegistrationAddOn> registrationAddOns = new ArrayList<>();
        
        if (addOns != null) {
            for (AddOnOption option : addOns) {
                total += option.getPrice();
                // Create a record for this add-on (assuming quantity 1)
                registrationAddOns.add(new RegistrationAddOn(
                    UUID.randomUUID(), 
                    option, 
                    1
                ));
            }
        }

        // 6. Save Participant
        participantRepository.save(participant);

        // 7. Create Registration
        Registration registration = new Registration(
                UUID.randomUUID(), 
                event, 
                participant, 
                type, 
                total, 
                registrationAddOns
        );

        // 8. Save Registration
        registrationRepository.save(registration);
        return registration;
    }

    /**
     * Get all registrations for a specific event.
     * @param eventId the ID of the event
     * @return list of registrations for the event
     */
    public List<Registration> getEventRegistrations(UUID eventId) {
        return registrationRepository.findByEventId(eventId);
    }

    /**
     * Get all registrations for a specific participant.
     * @param participantId the ID of the participant
     * @return list of registrations for the participant
     */
    public List<Registration> getParticipantRegistrations(UUID participantId) {
        return registrationRepository.findByParticipantId(participantId);
    }

    /**
     * Get a specific registration by ID.
     * @param registrationId the ID of the registration
     * @return the registration if found, null otherwise
     */
    public Registration getRegistration(UUID registrationId) {
        return registrationRepository.findById(registrationId).orElse(null);
    }

    /**
     * Get the number of registrations for an event.
     * @param eventId the ID of the event
     * @return the count of registrations
     */
    public long getEventRegistrationCount(UUID eventId) {
        return registrationRepository.countByEvent(eventId);
    }

    /**
     * Get available spots for an event.
     * @param event the event
     * @return the number of available registration spots
     */
    public long getAvailableSpots(Event event) {
        long registeredCount = registrationRepository.countByEvent(event.getEventId());
        return event.getMaxParticipants() - registeredCount;
    }

    /**
     * Check if a participant is already registered for an event.
     * @param eventId the ID of the event
     * @param participantId the ID of the participant
     * @return true if participant is registered, false otherwise
     */
    public boolean isParticipantRegistered(UUID eventId, UUID participantId) {
        return registrationRepository.findByEventIdAndParticipantId(eventId, participantId) != null;
    }
}