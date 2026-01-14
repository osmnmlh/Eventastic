package com.eventastic;

import com.eventastic.model.*;
import com.eventastic.model.enums.RegistrationType;
import com.eventastic.repository.*;
import com.eventastic.service.RegistrationService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Main {

        public static void main(String[] args) {

                EventRepository eventRepo = new EventRepository();
                ParticipantRepository participantRepo = new ParticipantRepository();
                RegistrationRepository registrationRepo = new RegistrationRepository();

                RegistrationService service = new RegistrationService(eventRepo, participantRepo, registrationRepo);

                Event event = new Event(
                                UUID.randomUUID(),
                                "Eventastic Conference",
                                "Demo",
                                "Lisbon",
                                LocalDateTime.now().plusDays(5),
                                LocalDateTime.now().plusDays(6),
                                100,
                                LocalDateTime.now().minusDays(1),
                                LocalDateTime.now().plusDays(3),
                                false);

                RegistrationPhase phase = new RegistrationPhase(
                                UUID.randomUUID(),
                                "Early",
                                LocalDateTime.now().minusDays(1),
                                LocalDateTime.now().plusDays(2));

                phase.addPrice(new RegistrationTypePrice(
                                UUID.randomUUID(), RegistrationType.STUDENT, 50.0));

                event.addRegistrationPhase(phase);
                eventRepo.save(event);

                Participant participant = new Participant(
                                UUID.randomUUID(), "Alice", "alice@mail.com");

                Registration reg = service.register(
                                event, participant, RegistrationType.STUDENT, List.of());

                System.out.println("Registration successful");
                System.out.println("Total: " + reg.getTotalAmount());
        }
}
