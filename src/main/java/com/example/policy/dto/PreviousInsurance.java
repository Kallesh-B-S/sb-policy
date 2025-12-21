package com.example.policy.dto;

import jakarta.persistence.Embeddable;
import lombok.Data;
import java.time.LocalDate;

@Data
@Embeddable
public class PreviousInsurance {
    private String company;
    private Double lastClaimAmount;
    private String currency;
    private LocalDate policyExpired;
}