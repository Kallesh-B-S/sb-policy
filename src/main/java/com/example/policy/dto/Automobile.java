package com.example.policy.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class Automobile {
    
    private Integer id;

    private Integer customerId;

    private String make;

    private String made;

    private String year;

    private String licensePlateNumber;

    private String vehicleIdentificationNumber;

    @Embedded
    private PreviousInsurance previousInsurance;
}
