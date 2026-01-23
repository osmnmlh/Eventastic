package com.eventastic.repository;

import com.eventastic.model.Participant;
<<<<<<< HEAD

import java.util.Optional;
import java.util.UUID;

public interface ParticipantRepository {
    Participant save(Participant participant);
    Optional<Participant> findById(UUID participantId);
    Optional<Participant> findByEmail(String email);
    void delete(UUID participantId);
}
=======
import java.util.ArrayList;
import java.util.List;

public class ParticipantRepository {

    private final List<Participant> participants = new ArrayList<>();

    public void save(Participant participant) {
        participants.add(participant);
    }

    public List<Participant> findAll() {
        return participants;
    }
}
>>>>>>> e25fc9ab6595f9805deac69870c54ed661f03466
