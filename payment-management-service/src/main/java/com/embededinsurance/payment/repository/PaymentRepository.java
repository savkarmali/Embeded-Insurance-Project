package com.embededinsurance.payment.repository;

import com.embededinsurance.payment.dto.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByCustomerReference(String customerReference);

    List<Payment> findByPolicyReference(String policyReference);
}

