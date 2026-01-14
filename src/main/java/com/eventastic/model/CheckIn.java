package com.eventastic.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class CheckIn {

    private UUID checkInId;
    private LocalDateTime checkInTime;
    private UUID operatorId;

    public CheckIn(UUID checkInId, UUID operatorId) {
        this.checkInId = checkInId;
        this.operatorId = operatorId;
        this.checkInTime = LocalDateTime.now();
    }

    public UUID getCheckInId() {
        return checkInId;
    }

    public LocalDateTime getCheckInTime() {
        return checkInTime;
    }
}
