package com.eventastic.repository;

import com.eventastic.model.Participant;
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
