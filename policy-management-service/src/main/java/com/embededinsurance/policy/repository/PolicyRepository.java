package com.embededinsurance.policy.repository;

import com.embededinsurance.policy.dto.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Long > {

       Optional<Policy> findByPolicyNumber(String policyReference);
}
