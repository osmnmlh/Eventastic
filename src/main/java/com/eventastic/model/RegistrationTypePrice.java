package com.eventastic.model;

import com.eventastic.model.enums.RegistrationType;
<<<<<<< HEAD

import java.util.UUID;

public class RegistrationTypePrice {
    private UUID id;
    private RegistrationType registrationType;
    private double amount;

    public RegistrationTypePrice() {}

    public RegistrationTypePrice(UUID id, RegistrationType registrationType, double amount) {
        this.id = id;
=======
import java.util.UUID;

public class RegistrationTypePrice {

    private UUID priceId;
    private RegistrationType registrationType;
    private double amount;

    public RegistrationTypePrice(UUID priceId, RegistrationType registrationType, double amount) {
        this.priceId = priceId;
>>>>>>> e25fc9ab6595f9805deac69870c54ed661f03466
        this.registrationType = registrationType;
        this.amount = amount;
    }

<<<<<<< HEAD
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

=======
>>>>>>> e25fc9ab6595f9805deac69870c54ed661f03466
    public RegistrationType getRegistrationType() {
        return registrationType;
    }

<<<<<<< HEAD
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
=======
    public double getAmount() {
        return amount;
    }
}
>>>>>>> e25fc9ab6595f9805deac69870c54ed661f03466
