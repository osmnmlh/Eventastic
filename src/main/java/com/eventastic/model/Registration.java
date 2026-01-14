package com.eventastic.model;

import com.eventastic.model.enums.RegistrationStatus;
import com.eventastic.model.enums.RegistrationType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Registration {

    private UUID registrationId;
    private Event event;
    private Participant participant;
    private RegistrationType registrationType;
    private RegistrationStatus paymentStatus;
    private double totalAmount;
    private LocalDateTime createdAt;

    private List<RegistrationAddOn> addOns = new ArrayList<>();
    private List<Payment> payments = new ArrayList<>();
    private CheckIn checkIn;

    public Registration(UUID registrationId, Event event,
            Participant participant,
            RegistrationType registrationType,
            double totalAmount) {
        this.registrationId = registrationId;
        this.event = event;
        this.participant = participant;
        this.registrationType = registrationType;
        this.totalAmount = totalAmount;
        this.paymentStatus = RegistrationStatus.PENDING_PAYMENT;
        this.createdAt = LocalDateTime.now();
    }

    public UUID getRegistrationId() {
        return registrationId;
    }

    public Event getEvent() {
        return event;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public RegistrationStatus getPaymentStatus() {
        return paymentStatus;
    }
}
