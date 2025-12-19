package com.example.policy;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.policy.dto.CreatePolicyResponse;
import com.example.policy.dto.CreatePolicyRquest;
import com.example.policy.dto.UpdatePolicyRequest;
import com.example.policy.exception.DataNotFoundException;

import jakarta.validation.constraints.NotNull;

@Service
public class PolicyService {

    @Autowired
    PolicyDao policyDao;

    public CreatePolicyResponse ceratePolicy(Integer customerId, CreatePolicyRquest requestBody) {

        Policy policy = new Policy();

        policy.setPolicyNumber(UUID.randomUUID().toString().replace("-", ""));
        policy.setPolicyName(requestBody.getPolicyName());
        policy.setPolicyType(requestBody.getPolicyType());
        policy.setCustomer_id(customerId);
        policy.setPremiumAmount(requestBody.getPremiumAmount());
        policy.setCoverageAmount(requestBody.getCoverageAmount());
        policy.setStartDate(requestBody.getStartDate());
        policy.setEndDate(requestBody.getEndDate());
        policy.setStatus("ACTIVE");

        Policy savedPolicy = policyDao.save(policy);

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
}
