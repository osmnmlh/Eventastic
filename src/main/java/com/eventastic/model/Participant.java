package com.eventastic.model;

import java.util.UUID;

public class Participant {

    private UUID participantId;
    private String fullName;
    private String email;

    public Participant(UUID participantId, String fullName, String email) {
        this.participantId = participantId;
        this.fullName = fullName;
        this.email = email;
    }

    public UUID getParticipantId() {
        return participantId;
    }
}
