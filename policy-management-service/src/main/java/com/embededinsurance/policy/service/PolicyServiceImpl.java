package com.embededinsurance.policy.service;

import com.embededinsurance.policy.client.CustomerServiceRestClient;
import com.embededinsurance.policy.dto.Customer;
import com.embededinsurance.policy.dto.Policy;
import com.embededinsurance.policy.exception.CustomerNotFoundException;
import com.embededinsurance.policy.exception.PolicyNotFoundException;
import com.embededinsurance.policy.repository.PolicyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PolicyServiceImpl implements PolicyService{

    @Autowired
    private PolicyRepository policyRepository;

    @Autowired
    private CustomerServiceRestClient customerRestClient;

    @Override
    public Policy createPolicy(Policy policy) {
        // Fetch customer details using customerReference
        try {
            Customer customer = customerRestClient.fetchCustomerByReference(policy.getCustomerReference());
            if (customer == null) {
                throw new CustomerNotFoundException("Customer with reference " + policy.getCustomerReference() + " not found");
            }
            log.info("Fetched Customer: {} " + customer.getName());
            return policyRepository.save(policy);
        }catch (CustomerNotFoundException e) {
            log.error("Error while fetching customer: {}",e.getMessage());
            throw e;    // Re-throw the exception to handle it in a global exception handler
        }
    }

    @Override
    public Policy getPolicy(Long id) {
        return policyRepository.findById(id)
                .orElseThrow(() -> new PolicyNotFoundException("Policy with id {"+ id +"} Not Found"));
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

    @Override
    public Policy getPolicyByReference(String policyReference) {
        return policyRepository.findByPolicyNumber(policyReference)
                .orElseThrow(() -> new PolicyNotFoundException("Policy with reference '"+ policyReference +"' not found"));
    }
}
