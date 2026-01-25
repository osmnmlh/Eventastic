package com.eventastic.model;

import com.eventastic.model.enums.RegistrationStatus;
import com.eventastic.model.enums.RegistrationType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Registration {
    private UUID id;
    private Event event;
    private Participant participant;
    private RegistrationType registrationType;
    private RegistrationStatus status;
    private double totalAmount;
    private LocalDateTime registrationDate;
    
    // NEW FIELD: Stores the list of selected add-ons
    private List<RegistrationAddOn> selectedAddOns;

    public Registration() {}

    // UPDATED CONSTRUCTOR: Now accepts List<RegistrationAddOn>
    public Registration(UUID id, Event event, Participant participant, 
                        RegistrationType registrationType, double totalAmount,
                        List<RegistrationAddOn> selectedAddOns) {
        this.id = id;
        this.event = event;
        this.participant = participant;
        this.registrationType = registrationType;
        this.totalAmount = totalAmount;
        this.selectedAddOns = selectedAddOns; // Store the list
        this.status = RegistrationStatus.PENDING;
        this.registrationDate = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    public RegistrationType getRegistrationType() {
        return registrationType;
    }

    public void setRegistrationType(RegistrationType registrationType) {
        this.registrationType = registrationType;
    }

    public RegistrationStatus getStatus() {
        return status;
    }

    public void setStatus(RegistrationStatus status) {
        this.status = status;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public List<RegistrationAddOn> getSelectedAddOns() {
        return selectedAddOns;
    }

    public void setSelectedAddOns(List<RegistrationAddOn> selectedAddOns) {
        this.selectedAddOns = selectedAddOns;
    }
}