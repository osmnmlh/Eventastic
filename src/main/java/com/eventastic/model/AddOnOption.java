package com.eventastic.model;

import java.util.UUID;

public class AddOnOption {

    private UUID optionId;
    private String name;
    private String description;
    private double price;

    public AddOnOption(UUID optionId, String name, String description, double price) {
        this.optionId = optionId;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}
