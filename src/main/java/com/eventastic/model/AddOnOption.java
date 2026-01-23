package com.eventastic.model;

import java.util.UUID;

public class AddOnOption {
    private UUID id;
    private String name;
    private String description;
    private double price;

    public AddOnOption(UUID optionId, String name, String description, double price) {
        this.optionId = optionId;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
