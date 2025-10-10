package com.embededinsurance.claims.service;

import com.embededinsurance.claims.client.CustomerFeignClient;
import com.embededinsurance.claims.client.CustomerServiceRestClient;
import com.embededinsurance.claims.client.PolicyFeignClient;
import com.embededinsurance.claims.client.PolicyServiceRestClient;
import com.embededinsurance.claims.dto.Claim;
import com.embededinsurance.claims.dto.ClaimStatus;
import com.embededinsurance.claims.dto.Customer;
import com.embededinsurance.claims.dto.Policy;
import com.embededinsurance.claims.exception.CustomerNotFoundException;
import com.embededinsurance.claims.exception.PolicyNotFoundException;
import com.embededinsurance.claims.repository.ClaimsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ClaimsServiceImpl implements ClaimsService {

//    @Autowired
//    private PolicyFeignClient policyFeignClient;
//
//    @Autowired
//    private CustomerFeignClient customerFeignClient;

    @Autowired
    private PolicyServiceRestClient policyServiceRestClient;

    @Autowired
    private CustomerServiceRestClient customerServiceRestClient;

    @Autowired
    private ClaimsRepository claimsRepository;

    @Override
    public Claim getClaim(Long id) {
        return claimsRepository.findById(id).orElseThrow(() -> new RuntimeException("Claim not found"));
    }

    @Override
    public List<Claim> getAllClaims() {
        return claimsRepository.findAll();
    }

    @Override
    public Claim validateAndCreateClaim(Claim claim) {
        // Fetch and validate policy details
        Policy policy = fetchAndValidatePolicy(claim.getPolicyReference());
        log.info("Fetched Policy: {}", policy.getPolicyNumber());

        // Fetch and validate customer details
        Customer customer = fetchAndValidateCustomer(claim.getCustomerReference());
        log.info("Fetched Customer: {}", customer.getName());

        // Additional validation logic
        if (!policy.getCustomerReference().equals(claim.getCustomerReference())) {
            throw new RuntimeException("Customer does not match the policy");
        }

        // Save and return the claim
        return claimsRepository.save(claim);
    }

    private Policy fetchAndValidatePolicy(String policyReference) {
        try {
            Policy policy = policyServiceRestClient.fetchPolicyByReference(policyReference);
            if (policy == null) {
                throw new PolicyNotFoundException("Policy not found with ID: " + policyReference);
            }
            return policy;
        } catch (PolicyNotFoundException e) {
            log.error("Error while fetching policy: {}", e.getMessage());
            throw e;
        }
    }

    private Customer fetchAndValidateCustomer(String customerReference) {
        try {
            Customer customer = customerServiceRestClient.fetchCustomerByReference(customerReference);
            if (customer == null) {
                throw new CustomerNotFoundException("Customer not found with ID: " + customerReference);
            }
            return customer;
        } catch (CustomerNotFoundException e) {
            log.error("Error while fetching customer: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public Claim updateClaim(Long id, Claim updatedClaim) {
        Claim claim = getClaim(id);
        claim.setPolicyReference(updatedClaim.getPolicyReference());
        claim.setCustomerReference(updatedClaim.getCustomerReference());
        claim.setClaimStatus(updatedClaim.getClaimStatus());
        claim.setClaimNumber(updatedClaim.getClaimNumber());
        claim.setDateFiled(updatedClaim.getDateFiled());
        return claimsRepository.save(claim);
    }

    @Override
    public void deleteClaim(Long id) {
        claimsRepository.deleteById(id);
    }
}
