package com.eventastic.service;

import com.eventastic.model.*;
import com.eventastic.model.enums.RegistrationType;
import com.eventastic.repository.*;

import java.time.LocalDateTime;
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

        if (now.isBefore(event.getRegistrationStart()) ||
                now.isAfter(event.getRegistrationEnd())) {
            throw new IllegalStateException("Registration closed");
        }

        if (registrationRepository.countByEvent(event.getEventId()) >= event.getMaxParticipants()) {
            throw new IllegalStateException("Event full");
        }

        RegistrationPhase activePhase = event.getPhases().stream()
                .filter(p -> p.isActive(now))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No active phase"));

        double basePrice = activePhase.getPrices().stream()
                .filter(p -> p.getRegistrationType() == type)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No price"))
                .getAmount();

        double total = basePrice;
        for (AddOnOption option : addOns) {
            total += option.getPrice();
        }

        participantRepository.save(participant);

        Registration registration = new Registration(
                UUID.randomUUID(), event, participant, type, total);

        registrationRepository.save(registration);
        return registration;
    }
}
