package com.example.policy;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.policy.dto.CreatePolicyResponse;
import com.example.policy.dto.CreatePolicyRquest;
import com.example.policy.dto.PolicyWithAutomobileResponse;
import com.example.policy.dto.UpdatePolicyRequest;
import com.example.policy.util.JwtUtil;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("policy")
public class PolicyController {

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    PolicyService policyService;

    @GetMapping
    public ResponseEntity<List<Policy>> getAllPolicy() {
        List<Policy> response = policyService.getAllPolicy();
        return ResponseEntity.ok(response);
    }

    // Inside Policy Controller
    @GetMapping("batch/list")
    public List<Policy> getPoliciesByIds(@RequestParam List<Integer> ids) {
        return policyService.getPoliciesByIds(ids); 
    }

    @GetMapping("{id}")
    public ResponseEntity<Policy> getPolicyById(@PathVariable Integer id) {
        Policy response = policyService.getPolicyById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("customer/{id}")
    public ResponseEntity<List<Policy>> getPolicyByCustomerId(@Valid @PathVariable("id") Integer customerId) {
        List<Policy> response = policyService.getPolicyByCustomerId(customerId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}/automobile")
    public ResponseEntity<PolicyWithAutomobileResponse> getPolicyWithAutomobileDetailsByPolicyId(
            @Valid @PathVariable("id") Integer policyId) {
        PolicyWithAutomobileResponse response = policyService.getPolicyWithAutomobileDetailsByPolicyId(policyId);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CreatePolicyResponse> createPolicy(@Valid @RequestBody CreatePolicyRquest requestBody,
            HttpServletRequest request) {

        // String authHeader = request.getHeader("Authorization");
        // String token = authHeader.substring(7);
        // Claims claims = jwtUtil.extractAllClaims(token);
        // Integer customerId = claims.get("customerId", Integer.class);

        // System.out.println("------ checking input data create policy ---------");
        // System.out.print("customerId => ");
        // System.out.println(customerId);
        // System.out.println("request body : ");
        // System.out.println(requestBody);
        // System.out.println("------ checking input data create policy end ---------");

        CreatePolicyResponse response = policyService.ceratePolicy(requestBody);

        // return
        // return null;
        // return ResponseEntity.ok(new CreatePolicyResponse(1, "success"));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
        // return ResponseEntity.ok(requestBody);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<CreatePolicyResponse> updatePolicy(@PathVariable Integer id,
            @RequestBody UpdatePolicyRequest request) {

        CreatePolicyResponse response = policyService.updatePolicy(id, request);

        return ResponseEntity.ok(response);
    }
}
