package com.embededinsurance.policy.controller;

import com.embededinsurance.policy.dto.Policy;
import com.embededinsurance.policy.service.PolicyProducer;
import com.embededinsurance.policy.service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/policies")
public class PolicyController {

    @Autowired
    private PolicyService policyService;

    @Autowired
    private PolicyProducer policyProducer;

    // Create a new policy
    @PostMapping
    public ResponseEntity<Policy> createPolicy(@RequestBody Policy policy) {
        return ResponseEntity.ok(policyService.createPolicy(policy));
    }

    // Retrieve a policy by ID
    @GetMapping("/{id}")
    public ResponseEntity<Policy> getPolicy(@PathVariable Long id) {
        return ResponseEntity.ok(policyService.getPolicy(id));
    }

    @GetMapping
    public List<Policy> getAllPolicies() {
        return policyService.getAllPolicies();
    }

    @PutMapping("/{id}")
    public Policy updatePolicy(@PathVariable Long id, @RequestBody Policy policy) {
        return policyService.updatePolicy(id, policy);
    }

    @DeleteMapping("/{id}")
    public void deletePolicy(@PathVariable Long id) {
        policyService.deletePolicy(id);
    }

    @GetMapping("/reference/{policyReference}")
    public Policy getPolicyByReference(@PathVariable String policyReference){
        return policyService.getPolicyByReference(policyReference);
    }

    @PostMapping("/process")
    public String processPolicy(@RequestBody String policyInfo) {
        // Business logic for processing policy
        policyProducer.sendPolicyUpdate("Policy processed : " + policyInfo);
        return "Policy processed successfully ! ";
    }



}
