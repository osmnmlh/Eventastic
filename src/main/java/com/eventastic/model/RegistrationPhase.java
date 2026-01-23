package com.eventastic.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RegistrationPhase {
    private UUID id;
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private List<RegistrationTypePrice> prices;

    public RegistrationPhase() {
        this.prices = new ArrayList<>();
    }

    public RegistrationPhase(UUID id, String name, LocalDateTime startDate, LocalDateTime endDate) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.prices = new ArrayList<>();
    }

    public boolean isActive(LocalDateTime dateTime) {
        return !dateTime.isBefore(startDate) && !dateTime.isAfter(endDate);
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

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public List<RegistrationTypePrice> getPrices() {
        return prices;
    }

    public void setPrices(List<RegistrationTypePrice> prices) {
        this.prices = prices;
    }

    public void addPrice(RegistrationTypePrice price) {
        this.prices.add(price);
    }
}
