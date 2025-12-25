package com.example.policy.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull; // <-- Standard Jakarta import
import jakarta.validation.constraints.Positive;

import lombok.Data;

@Data
public class CreatePolicyRquest {
    // @NotBlank(message = "Policy number is required")
    // private String policyNumber;

    @NotBlank(message = "Policy name is required")
    private String policyName;

    @NotNull(message = "Automobile ID is required")
    private Integer automobileID;

    @NotNull(message = "Customer ID is required")
    private Integer customerId;

    @NotNull(message = "Premium amount is required")
    @Positive(message = "Premium amount must be greater than zero")
    private BigDecimal premiumAmount;

    @NotBlank(message = "Premium Frequency is required")
    private String premiumFrequency;

    @NotNull(message = "Coverage amount is required")
    @Positive(message = "Coverage amount must be greater than zero")
    private BigDecimal coverageAmount;

    @NotNull(message = "Start date is required")
    @FutureOrPresent(message = "Start date cannot be in the past")
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    private LocalDate endDate;

    @NotNull(message = "Renewal date is required")
    private LocalDate renewalDate;

    // @NotBlank(message = "Status is required")
    // private String status; // ACTIVE, EXPIRED, CANCELLED
}
