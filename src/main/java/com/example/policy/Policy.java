package com.example.policy;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
public class Policy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "policy_id")
    private Integer id;

    @Column(unique = true, nullable = false)
    private String policyNumber;
    
    private String policyName;
    
    private Integer automobileID;

    @Column(name = "customer_id")
    private Integer customerId;

    private BigDecimal premiumAmount;
    private BigDecimal coverageAmount;

    private LocalDate startDate;
    private LocalDate endDate;

    private String status; // ACTIVE, EXPIRED, CANCELLED
}

