package com.example.policy.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull; // <-- Standard Jakarta import
import jakarta.validation.constraints.Positive;

import lombok.Data;

@Data
public class UpdatePolicyRequest {

    @NotBlank(message = "Policy name is required")
    private String policyName;

    @NotBlank(message = "Status is required")
    private String status;
    
}
