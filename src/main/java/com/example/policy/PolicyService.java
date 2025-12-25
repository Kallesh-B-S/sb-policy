package com.example.policy;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.policy.dto.Automobile;
import com.example.policy.dto.CreatePolicyResponse;
import com.example.policy.dto.CreatePolicyRquest;
import com.example.policy.dto.PolicyWithAutomobileResponse;
import com.example.policy.dto.UpdatePolicyRequest;
import com.example.policy.exception.DataNotFoundException;

import jakarta.validation.constraints.NotNull;

import org.springframework.web.client.RestTemplate;

import org.springframework.transaction.annotation.Transactional;

@Service
public class PolicyService {

    @Autowired
    PolicyDao policyDao;

    @Autowired
    private RestTemplate restTemplate;

    @Transactional
    public CreatePolicyResponse ceratePolicy(CreatePolicyRquest requestBody) {

        Policy policy = new Policy();

        // policy.setPolicyNumber(UUID.randomUUID().toString().replace("-", ""));
        policy.setPolicyName(requestBody.getPolicyName());
        policy.setAutomobileID(requestBody.getAutomobileID());
        policy.setCustomerId(requestBody.getCustomerId());
        // policy.setCustomerId(customerId);
        policy.setPremiumAmount(requestBody.getPremiumAmount());
        policy.setCoverageAmount(requestBody.getCoverageAmount());
        policy.setStartDate(requestBody.getStartDate());
        policy.setEndDate(requestBody.getEndDate());
        policy.setStatus("ACTIVE");
        policy.setPremiumFrequency(requestBody.getPremiumFrequency());
        policy.setRenewalDate(requestBody.getRenewalDate());

        Policy savedPolicy = policyDao.save(policy);

        String generatedPolicyNumber = "POL-" + savedPolicy.getId();
        savedPolicy.setPolicyNumber(generatedPolicyNumber);

        policyDao.save(savedPolicy);

        return new CreatePolicyResponse(savedPolicy.getId(), "Policy Added Successfully");
    }

    public CreatePolicyResponse updatePolicy(Integer id, UpdatePolicyRequest request) {
        Policy existingPolicy = policyDao.findById(id)
                .orElseThrow(() -> new DataNotFoundException("No Data Found for provided id : " + id));

        existingPolicy.setPolicyName(request.getPolicyName());
        existingPolicy.setStatus(request.getStatus());

        Policy updatedpolicy = policyDao.save(existingPolicy);

        return new CreatePolicyResponse(updatedpolicy.getId(), "Policy Updated Successfully");
    }

    public List<Policy> getAllPolicy() {
        return policyDao.findAll();
    }

    public Policy getPolicyById(int id) {
        return policyDao.findById(id)
                .orElseThrow(() -> new DataNotFoundException("No Data Found for provided id : " + id));
    }

    public List<Policy> getPoliciesByIds(List<Integer> ids) {
        if (ids == null || ids.isEmpty()) {
            return Collections.emptyList();
        }

        List<Policy> entities = policyDao.findAllById(ids);

        return entities.stream().map(entity -> {
            Policy dto = new Policy();
            dto.setId(entity.getId());
            dto.setPolicyNumber(entity.getPolicyNumber());
            dto.setPolicyName(entity.getPolicyName());
            dto.setAutomobileID(entity.getAutomobileID());
            dto.setCustomerId(entity.getCustomerId());
            dto.setPremiumAmount(entity.getPremiumAmount());
            dto.setCoverageAmount(entity.getCoverageAmount());
            dto.setStartDate(entity.getStartDate());
            dto.setEndDate(entity.getEndDate());
            dto.setStatus(entity.getStatus());
            return dto;
        }).collect(Collectors.toList());

        // return policyService.getPoliciesByIds(ids); // Uses repo.findAllById(ids)
    }

    public List<Policy> getPolicyByCustomerId(Integer customerId) {
        return policyDao.findByCustomerId(customerId);
    }

    public PolicyWithAutomobileResponse getPolicyWithAutomobileDetailsByPolicyId(int policyId) {
        Policy policy = policyDao.findById(policyId).orElseThrow(() -> new DataNotFoundException("Invalid policy ID"));

        // call another microservise api using restcliet and get data and send back
        String url = "http://localhost:8766/customer/automobile/" + policy.getAutomobileID();

        Automobile automobile = restTemplate.getForObject(url, Automobile.class);

        System.out.println(automobile);

        PolicyWithAutomobileResponse response = new PolicyWithAutomobileResponse();
        response.setPolicy(policy);
        response.setAutomobile(automobile);

        return response;

        // example response

    }
}
