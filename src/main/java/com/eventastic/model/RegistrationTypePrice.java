package com.eventastic.model;

import com.eventastic.model.enums.RegistrationType;

import java.util.UUID;

public class RegistrationTypePrice {
    private UUID id;
    private RegistrationType registrationType;
    private double amount;

    public RegistrationTypePrice() {}

    public RegistrationTypePrice(UUID id, RegistrationType registrationType, double amount) {
        this.id = id;
        this.registrationType = registrationType;
        this.amount = amount;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public RegistrationType getRegistrationType() {
        return registrationType;
    }

    public void setRegistrationType(RegistrationType registrationType) {
        this.registrationType = registrationType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
