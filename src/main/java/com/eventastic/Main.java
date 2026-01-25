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
                EventRepository eventRepo = new InMemoryEventRepository();
                ParticipantRepository participantRepo = new InMemoryParticipantRepository();
                RegistrationRepository registrationRepo = new InMemoryRegistrationRepository();


                RegistrationService service = new RegistrationService(eventRepo, participantRepo, registrationRepo);

                Event event = new Event(
                                UUID.randomUUID(),
                                "Eventastic Conference",
                                "A major event for networking and learning",
                                LocalDateTime.now().plusDays(5),
                                LocalDateTime.now().minusDays(1),
                                LocalDateTime.now().plusDays(3),
                                100);

                RegistrationPhase phase = new RegistrationPhase(
                                UUID.randomUUID(),
                                "Early",
                                LocalDateTime.now().minusDays(1),
                                LocalDateTime.now().plusDays(2));

                phase.addPrice(new RegistrationTypePrice(
                                UUID.randomUUID(), RegistrationType.STUDENT, 50.0));

                event.addPhase(phase);
                eventRepo.save(event);

                Participant participant = new Participant(
                                UUID.randomUUID(), "Alice", "Smith", "alice@mail.com", "+351912345678");

                Registration reg = service.register(
                                event, participant, RegistrationType.STUDENT, List.of());

                System.out.println("Registration successful");
                System.out.println("Total: " + reg.getTotalAmount());
        }
}
