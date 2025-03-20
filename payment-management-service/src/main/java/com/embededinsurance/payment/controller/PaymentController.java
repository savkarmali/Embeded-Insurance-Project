package com.embededinsurance.payment.controller;

import com.embededinsurance.payment.dto.Payment;
import com.embededinsurance.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public Payment createPolicy(@RequestBody Payment payment) {
        return paymentService.createPayment(payment);
    }

    @GetMapping("/{id}")
    public Payment getPayment(@PathVariable Long id) {
        return paymentService.getPayment(id);
    }

    @GetMapping
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }
}
