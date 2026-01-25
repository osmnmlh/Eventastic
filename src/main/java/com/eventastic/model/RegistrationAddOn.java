package com.eventastic.model;

import java.util.UUID;

public class RegistrationAddOn {

    private UUID registrationAddOnId;
    private AddOnOption addOnOption;
    private int quantity;

    public RegistrationAddOn(UUID registrationAddOnId, AddOnOption addOnOption, int quantity) {
        this.registrationAddOnId = registrationAddOnId;
        this.addOnOption = addOnOption;
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return addOnOption.getPrice() * quantity;
    }
}
