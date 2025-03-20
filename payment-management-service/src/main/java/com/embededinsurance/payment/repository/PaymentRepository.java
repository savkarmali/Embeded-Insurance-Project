package com.embededinsurance.payment.repository;

import com.embededinsurance.payment.dto.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
