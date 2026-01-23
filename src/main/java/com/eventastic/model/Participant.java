package com.eventastic.model;

import java.util.UUID;

public class Participant {
<<<<<<< HEAD
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    public Participant() {}

    public Participant(UUID id, String firstName, String lastName, String email, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
=======

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
>>>>>>> e25fc9ab6595f9805deac69870c54ed661f03466
