package com.embededinsurance.policy.service;

import com.embededinsurance.policy.dto.Policy;
import com.embededinsurance.policy.repository.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PolicyServiceImpl implements PolicyService{

    @Autowired
    private PolicyRepository policyRepository;

    @Override
    public Policy createPolicy(Policy policy) {
        return policyRepository.save(policy);
    }

    @Override
    public Policy getPolicy(Long id) {
        return policyRepository.findById(id).orElseThrow(() -> new RuntimeException("Policy Not Found"));
    }

    @Override
    public List<Policy> getAllPolicies() {
        return policyRepository.findAll();
    }

    @Override
    public Policy updatePolicy(Long id, Policy updatePolicy) {
        Policy policy = getPolicy(id);
        policy.setPolicyNumber(updatePolicy.getPolicyNumber());
        policy.setPolicyType(updatePolicy.getPolicyType());
        policy.setEffectiveDate(updatePolicy.getEffectiveDate());
        policy.setExpirationDate(updatePolicy.getExpirationDate());
        policy.setPremium(updatePolicy.getPremium());
        return policyRepository.save(policy);
    }

    @Override
    public void deletePolicy(Long id) {
        policyRepository.deleteById(id);
    }
}
