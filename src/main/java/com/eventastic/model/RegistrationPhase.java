package com.eventastic.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RegistrationPhase {

    private UUID phaseId;
    private String name;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private List<RegistrationTypePrice> prices = new ArrayList<>();

    public RegistrationPhase(UUID phaseId, String name,
            LocalDateTime startDateTime,
            LocalDateTime endDateTime) {
        this.phaseId = phaseId;
        this.name = name;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public boolean isActive(LocalDateTime now) {
        return !now.isBefore(startDateTime) && !now.isAfter(endDateTime);
    }

    public void addPrice(RegistrationTypePrice price) {
        prices.add(price);
    }

    public List<RegistrationTypePrice> getPrices() {
        return prices;
    }
}
