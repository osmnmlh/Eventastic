package com.eventastic.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class Payment {

    private UUID paymentId;
    private double amount;
    private LocalDate transferDate;
    private LocalDateTime createdAt;
    private String note;
    private boolean confirmed;

    public Payment(UUID paymentId, double amount, LocalDate transferDate,
            String note, boolean confirmed) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.transferDate = transferDate;
        this.createdAt = LocalDateTime.now();
        this.note = note;
        this.confirmed = confirmed;
    }

    public UUID getPaymentId() {
        return paymentId;
    }

    public double getAmount() {
        return amount;
    }

    public boolean isConfirmed() {
        return confirmed;
    }
}
