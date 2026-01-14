package com.eventastic.model;

import com.eventastic.model.enums.RegistrationType;
import java.util.UUID;

public class RegistrationTypePrice {

    private UUID priceId;
    private RegistrationType registrationType;
    private double amount;

    public RegistrationTypePrice(UUID priceId, RegistrationType registrationType, double amount) {
        this.priceId = priceId;
        this.registrationType = registrationType;
        this.amount = amount;
    }

    public RegistrationType getRegistrationType() {
        return registrationType;
    }

    public double getAmount() {
        return amount;
    }
}
