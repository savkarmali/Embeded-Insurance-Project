package com.embededinsurance.payment.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "payment")
@Getter
@Setter
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long policyId;
    private Long customerId;
    private double amount;
    private String paymentStatus;

    // Getters and Setters
}
