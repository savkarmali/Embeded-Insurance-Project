package com.embededinsurance.payment.service;

import com.embededinsurance.payment.dto.Payment;

import java.util.List;

public interface PaymentService {

    Payment createPayment(Payment payment);

    Payment getPayment(Long id);

    List<Payment> getAllPayments();
}
