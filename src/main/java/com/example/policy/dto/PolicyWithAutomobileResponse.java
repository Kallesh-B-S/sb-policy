package com.example.policy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import com.example.policy.Policy;

@Data
public class PolicyWithAutomobileResponse {

    private Policy policy;
    private Automobile automobile;
    
}
