package com.embededinsurance.policy.service;

import com.embededinsurance.policy.dto.Policy;

import java.util.List;

public interface PolicyService {


    Policy createPolicy(Policy policy);

    Policy getPolicy(Long id);

    List<Policy> getAllPolicies();

    Policy updatePolicy(Long id, Policy updatePolicy);

    void deletePolicy(Long id);

    Policy getPolicyByReference(String policyReference);
}
